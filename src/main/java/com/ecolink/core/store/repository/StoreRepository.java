package com.ecolink.core.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.store.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

	List<Store> findAllByNameContainingOrderByBookmarkCntDesc(String storeName);
}
