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
		return reviewRepository.findAllByStore(store.getId(), pageable);
	}

	public Page<Review> getByStoreAndAvatar(Store store, Avatar avatar, Pageable pageable) {
		return reviewRepository.findAllByStoreAndAvatar(store.getId(), avatar.getId(), pageable);
	}
}
