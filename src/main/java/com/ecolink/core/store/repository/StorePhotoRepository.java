package com.ecolink.core.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StorePhoto;

public interface StorePhotoRepository extends JpaRepository<StorePhoto, Long> {

	StorePhoto getStorePhotoByGivenOrderAndStore(Integer givenOrder, Store store);
}
