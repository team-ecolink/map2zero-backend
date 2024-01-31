package com.ecolink.core.like.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.like.domain.ReviewLike;
import com.ecolink.core.like.repository.ReviewLikeRepository;
import com.ecolink.core.review.dto.response.GetReviewResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewLikeService {

	private final ReviewLikeRepository reviewLikeRepository;

	public Page<GetReviewResponse> findReviewLike(Page<GetReviewResponse> reviews, Long avatarId) {
		Set<ReviewLike> reviewLikes = reviewLikeRepository.findAllByReviewList(
			reviews.map(GetReviewResponse::getId).toList(), avatarId).stream().collect(Collectors.toSet());

		Set<Long> reviewIds = reviewLikes.stream().map(reviewLike ->
				reviewLike.getReview().getId()).collect(Collectors.toSet());

		reviews.stream().filter(response -> reviewIds.contains(response.getId()))
						.forEach(response -> response.setLikedTrue(true));
		return reviews;
	}

}
