package com.ecolink.core.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.review.domain.Review;
import com.ecolink.core.review.repository.ReviewRepository;
import com.ecolink.core.store.domain.Store;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;

	public Page<Review> getByStore(Store store, Pageable pageable) {
		return reviewRepository.findAllByStore_Id(store.getId(), pageable);
	}

	public Boolean getByAvatar(Review review, Avatar avatar) {
		return reviewRepository.existsByIdAndWriter_Id(review.getId(), avatar.getId());
	}
}
