package com.ecolink.core.product.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.product.domain.StoreProduct;
import com.ecolink.core.product.dto.request.GetStoreProductRequest;
import com.ecolink.core.product.dto.response.GetStoreProductResponse;
import com.ecolink.core.product.repository.StoreProductJpaRepository;
import com.ecolink.core.product.repository.StoreProductRepository;
import com.ecolink.core.store.constant.SearchType;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.StoreProductDto;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.ecolink.core.store.dto.response.StoreSearchDto;
import com.ecolink.core.tag.domain.Product;
import com.ecolink.core.tag.domain.Tag;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreProductService {

	private final StoreProductRepository storeProductRepository;
	private final StoreProductJpaRepository storeProductJpaRepository;

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

	public List<GetStoreProductResponse> getByNameAndTag(Long storeId, GetStoreProductRequest request, Boolean onSale) {
		return storeProductJpaRepository.queryByNameAndTag(storeId, request, onSale);
	}

	@Transactional
	public StoreProduct createStoreProduct(int price, Store store, Product product, Tag tag) {
		store.addProductCnt();
		return storeProductRepository.save(new StoreProduct(price, store, product, tag));
	}

	public StoreProduct getByIdWithStore(Long storeProductId) {
		return storeProductRepository.findByIdWithStore(storeProductId).
			orElseThrow(() -> new EntityNotFoundException(ErrorCode.STORE_PRODUCT_NOT_FOUND));
	}

}
