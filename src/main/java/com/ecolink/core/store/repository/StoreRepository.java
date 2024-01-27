package com.ecolink.core.store.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecolink.core.store.domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	@Query("select s from Store s "
		+ "left join fetch s.storeTags st "
		+ "left join fetch st.tag t "
		+ "where s.id = :id")
	Optional<Store> findStoreGraphById(@Param("id") Long id);
}
