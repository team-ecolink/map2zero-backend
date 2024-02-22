package com.ecolink.core.file.factory;

import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.file.domain.SinglePhoto;

@FunctionalInterface
public interface SinglePhotoFactory<P extends SinglePhoto> {

	P convert(ImageFile file);

}
