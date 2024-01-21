package com.ecolink.core.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.core.store.domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
