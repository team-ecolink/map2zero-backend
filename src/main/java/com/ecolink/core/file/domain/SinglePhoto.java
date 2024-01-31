package com.ecolink.core.file.domain;

import com.ecolink.core.common.domain.ImageFile;

public interface SinglePhoto {

	ImageFile getFile();

	default String getKey() {
		return getFile().getS3Key();
	}

	default boolean isUploadedImage() {
		return getKey() != null;
	}

}
