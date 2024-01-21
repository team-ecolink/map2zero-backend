package com.ecolink.core.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.like.service.ReviewLikeService;
import com.ecolink.core.review.domain.Review;
import com.ecolink.core.review.dto.ReviewDto;
import com.ecolink.core.review.repository.ReviewRepository;
import com.ecolink.core.store.domain.Store;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final ReviewLikeService reviewLikeService;

	public List<ReviewDto> getAllByStore(Store store, Long avatarId) {
		List<Review> reviewList = reviewRepository.findAllByStore(store);

		if(avatarId != null) {
			return reviewList.stream().map(
				review -> ReviewDto.of(review, reviewLikeService.isLiked(avatarId, review))
			).toList();
		}
		return reviewList.stream().map(review -> ReviewDto.of(review, null)).toList();
	}
}
