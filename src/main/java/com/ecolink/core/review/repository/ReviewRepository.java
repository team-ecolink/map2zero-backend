package com.ecolink.core.review.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecolink.core.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Page<Review> findAllByStore_Id(Long storeId, Pageable pageable);

	Boolean existsByIdAndWriter_Id(Long reviewId, Long writerId);
}
