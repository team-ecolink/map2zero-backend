package com.ecolink.core.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.core.review.domain.Review;
import com.ecolink.core.store.domain.Store;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findAllByStore(Store store);
}
