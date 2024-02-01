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

	public void processAndLimitTop3Products(StoreSearchRequest request, List<StoreSearchDto> storeSearchDtos) {
		String keyword = request.getKeyword();
		addStoreProductsToDto(storeSearchDtos);

		for (StoreSearchDto dto : storeSearchDtos) {

			if (SearchType.PRODUCT.equals(request.getType())) {
				processProductType(dto, keyword);
			}
			if (SearchType.STORE.equals(request.getType())) {
				processStoreType(dto);
			}

			addTop3StoreProductsToDto(dto);
		}
	}

	private void addStoreProductsToDto(List<StoreSearchDto> storeSearchDtos) {

		Map<Long, StoreSearchDto> map = storeSearchDtos.stream()
			.collect(Collectors.toMap(StoreSearchDto::getId, s -> s));
		List<StoreProduct> products = storeProductRepository.findByStoreIdIn(map.keySet());

		products.forEach(
			p -> map.get(p.getStore().getId()).addStoreProductDto(new StoreProductDto(p))
		);
	}

	private void processProductType(StoreSearchDto dto, String keyword) {
		dto.getProducts().sort((a, b) -> {
			boolean aFirst = a.getName().contains(keyword);
			boolean bFirst = b.getName().contains(keyword);
			if (aFirst == bFirst) {
				return a.getName().compareTo(b.getName());
			}
			return bFirst ? 1 : -1;
		});
	}

	private void processStoreType(StoreSearchDto dto) {
		dto.getProducts().sort(Comparator.comparing(StoreProductDto::getName));
	}

	private void addTop3StoreProductsToDto(StoreSearchDto dto) {
		List<StoreProductDto> storeProductDtos = dto.getProducts().stream()
			.limit(3)
			.toList();

		dto.clearStoreProducts();

		for (StoreProductDto storeProductDto : storeProductDtos) {
			dto.addStoreProductDto(storeProductDto);
		}
	}

	public List<StoreProduct> getByStore(Long storeId) {
		return storeProductRepository.findTop6ByStore_IdAndOnSaleOrderByProduct_Name(storeId, true);
	}
}
