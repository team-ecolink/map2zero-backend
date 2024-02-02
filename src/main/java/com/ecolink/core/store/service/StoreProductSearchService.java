package com.ecolink.core.store.service;

import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.response.GetStoreProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreProductSearchService {

    private final StoreService storeService;
    private final StoreProductService storeProductService;

    public Page<GetStoreProductResponse> getProductList(Long storeId, Pageable pageable) {
        Store store = storeService.getById(storeId);
        return storeProductService.getByStore(store.getId(), pageable)
                .map(GetStoreProductResponse::of);
    }
}
