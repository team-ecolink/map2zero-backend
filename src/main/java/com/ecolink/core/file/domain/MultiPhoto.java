package com.ecolink.core.file.domain;

import com.ecolink.core.common.domain.ImageFile;

public interface MultiPhoto {

	ImageFile getFile();

	default String getKey() {
		return getFile().getS3Key();
	}

}
