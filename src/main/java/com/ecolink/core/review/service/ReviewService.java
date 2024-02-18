package com.ecolink.core.review.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.dto.request.MyPageReviewRequest;
import com.ecolink.core.avatar.dto.response.MyPageReviewResponse;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
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

	public Review getById(Long reviewId) {
		return reviewRepository.findById(reviewId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.REVIEW_NOT_FOUND));
	}

	public Page<Review> getByStore(Long storeId, Pageable pageable) {
		return reviewRepository.findByStore(storeId, pageable);
	}

	public Page<Review> getByStoreAndAvatar(Long storeId, Long avatarId, Pageable pageable) {
		return reviewRepository.findByStoreAndAvatar(storeId, avatarId, pageable);
	}

	public List<MyPageReviewResponse> getByWriter(MyPageReviewRequest request, Long writerId, Long viewerId) {
		return reviewJpaRepository.findByWriter(request, writerId, viewerId);
	}

	public Review getByIdWithStoreAndPhotos(Long reviewId) {
		return reviewRepository.findByIdWithStoreAndPhotos(reviewId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.REVIEW_NOT_FOUND));
	}

	@Transactional
	public void saveReview(Review review) {
		reviewRepository.save(review);
	}

	/**
	 * cascade 옵션으로 ReviewTag, ReviewLikes 전부 함께 삭제 됨
	 * ReviewPhoto 는 파일 저장소에서도 이미지 파일을 지워야만 하므로
	 * 반드시 이 메서드를 호출하기 전에 별도 제거 필요함
	 */
	@Transactional
	public void deleteReview(Review review) {
		reviewRepository.delete(review);
	}
  
}
