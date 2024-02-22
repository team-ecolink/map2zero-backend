package com.ecolink.core.file.service;

import java.util.List;

import com.ecolink.core.file.dto.FileInfo;
import com.ecolink.core.file.dto.FileUploadRequest;

public interface FileStorageService {

	FileInfo uploadFile(FileUploadRequest request);

	List<FileInfo> uploadFiles(List<FileUploadRequest> requests);

	void deleteFile(String key);

	void deleteFiles(List<String> keys);

	void recoverBackups(List<String> backupKeys);

	void deleteBackups(List<String> backupKeys);

}
