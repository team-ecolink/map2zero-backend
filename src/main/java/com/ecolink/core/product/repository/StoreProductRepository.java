package com.ecolink.core.product.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.product.domain.StoreProduct;

public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {

	@Query("select sp from StoreProduct sp "
		+ "join fetch sp.product "
		+ "join fetch sp.store s "
		+ "where s.id in :ids")
	List<StoreProduct> findByStoreIdIn(@Param("ids") Collection<Long> ids);

}
