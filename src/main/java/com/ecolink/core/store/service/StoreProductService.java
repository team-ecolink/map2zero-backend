package com.ecolink.core.store.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.store.constant.SearchType;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StoreProduct;
import com.ecolink.core.store.dto.StoreProductDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreProductService {

	public List<StoreProduct> findTop3StoreProductsOrderByProductName(SearchType type, String keyword, Store store) {

		List<StoreProduct> sortedStoreProducts = store.getStoreProducts().stream()
			.sorted(Comparator.comparing(sp -> sp.getProduct().getName()))
			.toList();

		if (type == SearchType.PRODUCT) {
			StoreProduct firstStoreProduct = sortedStoreProducts.stream()
				.filter(sp -> sp.getProduct().getName().contains(keyword))
				.findFirst()
				.orElse(null);

			List<StoreProduct> storeProducts = sortedStoreProducts.stream()
				.filter(sp -> !sp.equals(firstStoreProduct))
				.limit(2)
				.collect(Collectors.toList());

			if (firstStoreProduct != null) {
				storeProducts.add(0, firstStoreProduct);
			}

			return storeProducts;
		}

		return sortedStoreProducts.stream().limit(3).toList();
	}

	public List<StoreProductDto> getStoreProductDtos(List<StoreProduct> storeProducts) {
		return storeProducts.stream()
			.map(storeProduct -> new StoreProductDto(storeProduct.getId(), storeProduct.getProduct().getName()))
			.toList();
	}
}
