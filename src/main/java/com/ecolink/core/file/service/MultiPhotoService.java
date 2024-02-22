package com.ecolink.core.file.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.file.constant.FilePath;
import com.ecolink.core.file.domain.MultiPhoto;
import com.ecolink.core.file.domain.MultiPhotoContainer;
import com.ecolink.core.file.factory.MultiPhotoFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class MultiPhotoService {

	private final ImageUploadService imageUploadService;

	public <T extends MultiPhotoContainer<P>, P extends MultiPhoto> void changePhotos(
		List<MultipartFile> imagesToChange, T container, MultiPhotoFactory<T, P> multiPhotoFactory, FilePath path) {
		removePhotos(container);
		addPhotos(imagesToChange, container, multiPhotoFactory, path);
	}

	public <T extends MultiPhotoContainer<P>, P extends MultiPhoto> void addPhotos(List<MultipartFile> images,
		T container, MultiPhotoFactory<T, P> multiPhotoFactory, FilePath path) {
		List<ImageFile> imageFiles = imageUploadService.uploadImages(images, path, container.getId());

		for (int i = 0; i < images.size(); i++) {
			ImageFile file = imageFiles.get(i);
			container.addPhoto(multiPhotoFactory.convert(file, i, container));
		}
	}

	public <T extends MultiPhotoContainer<P>, P extends MultiPhoto> void removePhotos(T container) {
		List<String> s3keys = container.getKeys();
		imageUploadService.removeFiles(s3keys);
		container.clearPhotos();
	}

}
