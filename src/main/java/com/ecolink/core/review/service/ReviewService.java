package com.ecolink.core.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.review.domain.Review;
import com.ecolink.core.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;

	public Page<Review> getByStore(Long storeId, Pageable pageable) {
		return reviewRepository.findByStore(storeId, pageable);
	}

	public Page<Review> getByStoreAndAvatar(Long storeId, Long avatarId, Pageable pageable) {
		return reviewRepository.findByStoreAndAvatar(storeId, avatarId, pageable);
	}

	@Transactional
	public void saveReview(Review review) {
		reviewRepository.save(review);
	}
}
