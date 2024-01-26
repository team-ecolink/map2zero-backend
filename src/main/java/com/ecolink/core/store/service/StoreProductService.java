package com.ecolink.core.store.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.store.constant.SearchType;
import com.ecolink.core.store.domain.StoreProduct;
import com.ecolink.core.store.dto.StoreProductDto;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.ecolink.core.store.dto.response.StoreSearchDto;
import com.ecolink.core.store.repository.StoreProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreProductService {

	private final StoreProductRepository storeProductRepository;

	public void getTop3StoreProducts(StoreSearchRequest request, List<StoreSearchDto> storeSearchDtos) {

		addStoreProductsToStoreSearchDto(storeSearchDtos);

		for (StoreSearchDto dto : storeSearchDtos) {
			if (SearchType.PRODUCT.equals(request.getType())) {
				processProductType(dto, request.getKeyword());
			}
			if (SearchType.STORE.equals(request.getType())) {
				processStoreType(dto);
			}
		}
	}

	private void addStoreProductsToStoreSearchDto(List<StoreSearchDto> storeSearchDtos) {

		Map<Long, StoreSearchDto> map = storeSearchDtos.stream()
			.collect(Collectors.toMap(StoreSearchDto::getId, s -> s));
		List<StoreProduct> products = storeProductRepository.findByStoreIdIn(map.keySet());

		products.forEach(
			p -> map.get(p.getStore().getId()).addStoreProductDto(new StoreProductDto(p))
		);
	}

	private void processProductType(StoreSearchDto dto, String keyword) {

		List<StoreProductDto> sortedStoreProducts = dto.getProducts().stream()
			.sorted((a, b) -> {
				boolean aFirst = a.getName().contains(keyword);
				boolean bFirst = b.getName().contains(keyword);
				if (aFirst == bFirst) {
					return a.getName().compareTo(b.getName());
				}
				return bFirst ? 1 : -1;
			}).limit(3).toList();

		addTop3SortedStoreProductsToDto(dto, sortedStoreProducts);
	}

	private void processStoreType(StoreSearchDto dto) {
		List<StoreProductDto> sortedStoreProducts = dto.getProducts().stream()
			.sorted(Comparator.comparing(StoreProductDto::getName))
			.limit(3)
			.toList();

		addTop3SortedStoreProductsToDto(dto, sortedStoreProducts);
	}

	private void addTop3SortedStoreProductsToDto(StoreSearchDto dto, List<StoreProductDto> sortedProducts) {
		for (StoreProductDto productDto : sortedProducts) {
			dto.addStoreProductDto(productDto);
		}
	}
}
