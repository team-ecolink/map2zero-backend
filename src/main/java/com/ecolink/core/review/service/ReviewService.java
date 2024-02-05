package com.ecolink.core.review.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.dto.request.MyPageReviewRequest;
import com.ecolink.core.avatar.dto.response.MyPageReviewResponse;
import com.ecolink.core.review.domain.Review;
import com.ecolink.core.review.repository.ReviewJpaRepository;
import com.ecolink.core.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final ReviewJpaRepository reviewJpaRepository;

	public Page<Review> getByStore(Long storeId, Pageable pageable) {
		return reviewRepository.findByStore(storeId, pageable);
	}

	public Page<Review> getByStoreAndAvatar(Long storeId, Long avatarId, Pageable pageable) {
		return reviewRepository.findByStoreAndAvatar(storeId, avatarId, pageable);
	}

	public List<MyPageReviewResponse> getByWriter(MyPageReviewRequest request, Long writerId, Long viewerId) {
		return reviewJpaRepository.findByWriter(request, writerId, viewerId);
	}

	@Transactional
	public void saveReview(Review review) {
		reviewRepository.save(review);
	}
}
