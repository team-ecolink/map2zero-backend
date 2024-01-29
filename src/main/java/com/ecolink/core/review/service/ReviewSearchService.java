package com.ecolink.core.review.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.like.service.ReviewLikeService;
import com.ecolink.core.review.domain.Review;
import com.ecolink.core.review.dto.response.ReviewResponse;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewSearchService {

	private final StoreService storeService;
	private final ReviewService reviewService;
	private final AvatarService avatarService;
	private final ReviewLikeService reviewLikeService;

	public List<ReviewResponse> getByStore(Long storeId, Long avatarId, Pageable pageable) {
		Store store = storeService.getById(storeId);

		if(avatarId != null) {
			Avatar avatar = avatarService.getById(avatarId);
			return reviewService.getByStoreAndAvatar(store, avatar, pageable)
				.map(review -> ReviewResponse.of(review, reviewLikeService.isLiked(review, avatar), isWriter(review, avatar)))
				.getContent();
		}
		return reviewService.getByStore(store, pageable)
			.map(review -> ReviewResponse.of(review, false, false))
			.getContent();

	}

	private Boolean isWriter(Review review, Avatar avatar) {
		return review.getWriter().getId().equals(avatar.getId());
	}
}
