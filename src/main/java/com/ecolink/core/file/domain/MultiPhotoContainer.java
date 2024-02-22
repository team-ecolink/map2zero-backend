package com.ecolink.core.file.domain;

import java.util.List;

public interface MultiPhotoContainer<T extends MultiPhoto> {

	List<T> getPhotos();

	default void addPhoto(T multiPhoto) {
		getPhotos().add(multiPhoto);
	}

	default void clearPhotos() {
		getPhotos().clear();
	}

	default List<String> getKeys() {
		return getPhotos().stream().map(MultiPhoto::getKey).toList();
	}

	Long getId();

}
