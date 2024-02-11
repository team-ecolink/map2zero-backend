package com.ecolink.core.product_recommend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.product_recommend.constant.RecommendType;
import com.ecolink.core.product_recommend.domain.ProductRecommend;

public interface ProductRecommendRepository extends JpaRepository<ProductRecommend, Long> {

	@Query("select pr "
		+ "from ProductRecommend pr "
		+ "left join fetch pr.storeProduct "
		+ "where pr.type = :type")
	List<ProductRecommend> findAllByType(@Param("type") RecommendType type);
}
