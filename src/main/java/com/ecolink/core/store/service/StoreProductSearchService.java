package com.ecolink.core.store.service;

import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.response.GetStoreProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreProductSearchService {

    private final StoreService storeService;
    private final StoreProductService storeProductService;

    public List<GetStoreProductResponse> getProductList(Long storeId) {
        Store store = storeService.getById(storeId);
        return storeProductService.getByStore(store.getId()).stream()
                .map(GetStoreProductResponse::of).toList();
    }
}
