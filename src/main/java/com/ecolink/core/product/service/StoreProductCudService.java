package com.ecolink.core.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.ManagerForbiddenException;
import com.ecolink.core.common.util.AuthorityUtil;
import com.ecolink.core.file.constant.FilePath;
import com.ecolink.core.file.service.MultiPhotoService;
import com.ecolink.core.product.domain.StoreProduct;
import com.ecolink.core.product.domain.StoreProductPhoto;
import com.ecolink.core.product.dto.request.PostStoreProductRequest;
import com.ecolink.core.store.service.StoreService;
import com.ecolink.core.tag.constant.TagCategory;
import com.ecolink.core.tag.service.TagService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreProductCudService {

	private final StoreProductService storeProductService;
	private final TagService tagService;
	private final ProductService productService;
	private final StoreService storeService;
	private final ProductPriceService productPriceService;
	private final MultiPhotoService multiPhotoService;

	@Transactional
	public Long registerStoreProduct(Long storeId, PostStoreProductRequest request, List<MultipartFile> images,
		UserPrincipal principal) {
		if (!principal.isManagerOf(storeId) && !AuthorityUtil.hasAdminAuthority(principal))
			throw new ManagerForbiddenException(ErrorCode.NOT_MANAGER_OF_STORE);
		productPriceService.verifyPrice(request.getPrice());
		tagService.validateCategory(request.getTagId(), TagCategory.PRODUCT);

		StoreProduct storeProduct = storeProductService.createStoreProduct(request.getPrice(),
			storeService.getById(storeId), productService.getByName(request.getName(), true),
			tagService.getById(request.getTagId()));

		multiPhotoService.addPhotos(images, storeProduct, StoreProductPhoto::new, FilePath.PRODUCT_PHOTO);

		return storeProduct.getId();
	}

}
