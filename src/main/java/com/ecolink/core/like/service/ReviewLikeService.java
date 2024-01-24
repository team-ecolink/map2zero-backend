package com.ecolink.core.like.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.like.repository.ReviewLikeRepository;
import com.ecolink.core.review.domain.Review;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewLikeService {

	private final ReviewLikeRepository reviewLikeRepository;

	public Boolean isLiked(Review review, Avatar avatar) {
		return reviewLikeRepository.existsByAvatar_IdAndReview_Id(review.getId(), avatar.getId());
	}
}
