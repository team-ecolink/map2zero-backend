package com.ecolink.core.like.service;

import java.util.List;

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
		List<ReviewLike> reviewLikes = reviewLikeRepository.findAllByReviewList(
			reviews.map(GetReviewResponse::getId).toList(), avatarId);

		List<Long> reviewIds = reviewLikes.stream().map(reviewLike ->
				reviewLike.getReview().getId()).toList();

		reviews.forEach(getReviewResponse -> {
					if(reviewIds.contains(getReviewResponse.getId()))
						getReviewResponse.setLiked(true);});
		return reviews;
	}

}
