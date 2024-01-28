package com.ecolink.core.review.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {


	@Query("select distinct r from Review r "
		+ "join fetch r.writer "
		+ "join fetch r.store s "
		+ "left join r.reviewPhotos "
		+ "left join r.reviewLikes "
		+ "left join fetch r.reviewTags t "
		+ "left join fetch t.tag "
		+ "where s.id=:storeId")
	Page<Review> findAllByStore(@Param("storeId") Long storeId, Pageable pageable);

	Boolean existsByIdAndWriter_Id(Long reviewId, Long writerId);
}
