package com.ecolink.core.file.domain;

public interface SinglePhotoContainer<T extends SinglePhoto> {

	T getPhoto();

	void changePhoto(T singlePhoto);

	default String getKey() {
		if (getPhoto() == null)
			throw new IllegalStateException("SinglePhoto는 null일 수 없습니다.");
		return getPhoto().getKey();
	}

	Object getId();

}
