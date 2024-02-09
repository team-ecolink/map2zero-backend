package com.ecolink.core.product_recommend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.product.domain.StoreProduct;
import com.ecolink.core.product.service.StoreProductService;
import com.ecolink.core.product_recommend.constant.RecommendType;
import com.ecolink.core.product_recommend.dto.ProductInfoDto;
import com.ecolink.core.product_recommend.repository.ProductRecommendRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductRecommendService {

	private final ProductRecommendRepository productRecommendRepository;
	private final StoreProductService storeProductService;

	public List<ProductInfoDto> getTodayProducts() {
		List<StoreProduct> storeProducts = productRecommendRepository.findStoreProductsWithPhotosByRecommendType(
			RecommendType.TODAY);
		return storeProducts.stream()
			.map(storeProduct -> ProductInfoDto.of(storeProduct,
				storeProductService.getMainPhoto(storeProduct)))
			.toList();
	}
}
