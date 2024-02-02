package com.ecolink.core.file.factory;

import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.file.domain.MultiPhoto;
import com.ecolink.core.file.domain.MultiPhotoContainer;

@FunctionalInterface
public interface MultiPhotoFactory<T extends MultiPhotoContainer<P>, P extends MultiPhoto> {

	P convert(ImageFile file, int order, T container);

}
