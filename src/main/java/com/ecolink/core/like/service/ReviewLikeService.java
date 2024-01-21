package com.ecolink.core.like.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.like.repository.ReviewLikeRepository;
import com.ecolink.core.review.domain.Review;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewLikeService {

	private final ReviewLikeRepository reviewLikeRepository;
	private final AvatarService avatarService;

	public boolean isLiked(Long avatarId, Review review) {
		Avatar avatar = avatarService.getById(avatarId);
		return reviewLikeRepository.findByAvatarAndReview(avatar, review).isPresent();
	}
}
