package com.ecolink.core.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.FileUploadException;
import com.ecolink.core.file.dto.FileInfo;
import com.ecolink.core.file.dto.FileUploadRequest;
import com.ecolink.core.file.event.DeleteFileEvent;
import com.ecolink.core.file.event.UploadFileEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class S3StorageService implements FileStorageService {

	public static final String BACKUP_PATH = "backups/";

	private final String bucketName;
	private final AmazonS3 amazonS3;
	private final ApplicationEventPublisher eventPublisher;

	public S3StorageService(@Value("${cloud.aws.s3.bucket}") String bucketName, AmazonS3 amazonS3,
		ApplicationEventPublisher eventPublisher) {
		this.bucketName = bucketName;
		this.amazonS3 = amazonS3;
		this.eventPublisher = eventPublisher;
	}

	/**
	 * 모든 업로드 메서드는
	 * 트랜잭션 롤백 시 S3에서 추가된 사진 삭제를 위해
	 * UploadFileEvent 를 발행합니다.
	 */
	@Override
	public FileInfo uploadFile(FileUploadRequest request) {
		List<String> uploadedKeyList = publishUploadEvent();
		return uploadWithBackup(request, uploadedKeyList);
	}

	@Override
	public List<FileInfo> uploadFiles(List<FileUploadRequest> requests) {
		List<String> uploadedKeyList = publishUploadEvent();
		return requests.stream().map(request -> uploadWithBackup(request, uploadedKeyList)).toList();
	}

	private FileInfo uploadWithBackup(FileUploadRequest request, List<String> keyContainer) {
		FileInfo fileInfo = upload(request);
		keyContainer.add(fileInfo.key());
		return fileInfo;
	}

	private FileInfo upload(FileUploadRequest request) {
		MultipartFile file = request.file();
		String key = request.key();
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		metadata.setContentType(file.getContentType());

		try (InputStream inputStream = file.getInputStream()) {
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, metadata);
			amazonS3.putObject(putObjectRequest);
			String url = amazonS3.getUrl(bucketName, key).toString();
			return new FileInfo(file, url, key);
		} catch (IOException e) {
			log.warn(e.toString());
			throw new FileUploadException(ErrorCode.FAIL_TO_UPLOAD_FILE);
		}
	}

	private List<String> publishUploadEvent() {
		List<String> uploadedKeyList = new ArrayList<>();
		eventPublisher.publishEvent(new UploadFileEvent(uploadedKeyList));
		return uploadedKeyList;
	}

	/**
	 * 모든 삭제 메서드는
	 * 트랜잭션 커밋시 S3에서 백업 삭제,
	 * 트랜잭션 롤백시 백업 복구 및 백업 삭제를 위해서
	 * DeleteFileEvent 를 발행합니다.
	 */
	@Override
	public void deleteFile(String key) {
		List<String> backupKeys = publishDeleteEvent();
		deleteWithBackup(key, backupKeys);
	}

	@Override
	public void deleteFiles(List<String> keys) {
		List<String> backupKeys = publishDeleteEvent();
		keys.forEach(key -> deleteWithBackup(key, backupKeys));
	}

	@Override
	public void deleteBackups(List<String> backupKeys) {
		backupKeys.forEach(this::delete);
	}

	private void deleteWithBackup(String key, List<String> backupKeys) {
		backupKeys.add(copyForBackup(key));
		delete(key);
	}

	private void delete(String key) {
		amazonS3.deleteObject(bucketName, key);
	}

	private List<String> publishDeleteEvent() {
		List<String> backupKeys = new ArrayList<>();
		eventPublisher.publishEvent(new DeleteFileEvent(backupKeys));
		return backupKeys;
	}

	/**
	 * 백업 키를 받아서 복구한 뒤 삭제하는 메서드
	 */
	@Override
	public void recoverBackups(List<String> backupKeys) {
		backupKeys.forEach(this::recover);
		backupKeys.forEach(this::delete);
	}

	private void recover(String backupKey) {
		String originalKey = backupKey.replaceFirst(BACKUP_PATH, "");
		copy(backupKey, originalKey);
	}

	private String copyForBackup(String key) {
		String backupKey = BACKUP_PATH + key;
		copy(key, backupKey);
		return backupKey;
	}

	private void copy(String sourceKey, String destinationKey) {
		CopyObjectRequest copyObjectRequest = new CopyObjectRequest(bucketName, sourceKey, bucketName, destinationKey);
		amazonS3.copyObject(copyObjectRequest);
	}

}
