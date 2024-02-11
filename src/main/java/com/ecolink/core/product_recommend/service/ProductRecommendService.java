package com.ecolink.core.product_recommend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.product.service.StoreProductService;
import com.ecolink.core.product_recommend.constant.RecommendType;
import com.ecolink.core.product_recommend.domain.ProductRecommend;
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
		List<ProductRecommend> productRecommends = productRecommendRepository.findAllByType(
			RecommendType.TODAY);
		return productRecommends.stream()
			.map(productRecommend -> ProductInfoDto.of(productRecommend.getStoreProduct(),
				storeProductService.getMainPhoto(productRecommend.getStoreProduct()))).toList();
	}

	public List<ProductInfoDto> getMyProducts() {
		List<ProductRecommend> productRecommends = productRecommendRepository.findAllByType(
			RecommendType.MY_PRODUCT);
		return productRecommends.stream()
			.map(productRecommend -> ProductInfoDto.of(productRecommend.getStoreProduct(),
				storeProductService.getMainPhoto(productRecommend.getStoreProduct()))).toList();
	}
}
