package com.ecolink.core.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.product.domain.StoreProduct;
import com.ecolink.core.admin.constant.RecommendType;
import com.ecolink.core.admin.domain.ProductRecommend;

public interface ProductRecommendRepository extends JpaRepository<ProductRecommend, Long> {

	@Query("select sp "
		+ "from StoreProduct sp "
		+ "join ProductRecommend pr "
		+ "on pr.storeProduct = sp "
		+ "left join fetch sp.photos "
		+ "join fetch sp.product "
		+ "join fetch sp.store "
		+ "where pr.type = :type ")
	List<StoreProduct> findStoreProductsByType(@Param("type") RecommendType type);
}
