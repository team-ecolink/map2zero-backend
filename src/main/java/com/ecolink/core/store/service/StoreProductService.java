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

	public void addTop3ProductsToDto(StoreSearchRequest request, List<StoreSearchDto> searchDtos) {
		String keyword = request.getKeyword();
		Map<Long, StoreSearchDto> map = searchDtos.stream().collect(Collectors.toMap(StoreSearchDto::getId, s -> s));
		List<StoreProduct> products = storeProductRepository.findByStoreIdIn(map.keySet());
		products.forEach(
			p -> map.get(p.getStore().getId()).addStoreProductDto(new StoreProductDto(p))
		);

		for (StoreSearchDto dto : searchDtos) {

			if (SearchType.PRODUCT.equals(request.getType())) {
				dto.getProducts().sort((a, b) -> {
					boolean aFirst = a.getName().contains(keyword);
					boolean bFirst = b.getName().contains(keyword);
					if (aFirst == bFirst) {
						return a.getName().compareTo(b.getName());
					}
					if (bFirst)
						return 1;
					return -1;
				});
			}

			if (SearchType.STORE.equals(request.getType())) {
				dto.getProducts().sort(Comparator.comparing(StoreProductDto::getName));
			}
		}
	}
}
