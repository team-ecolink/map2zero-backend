package com.ecolink.core.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.review.domain.ReviewTag;
import com.ecolink.core.review.repository.ReviewTagRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewTagService {

	private final ReviewTagRepository reviewTagRepository;

	@Transactional
	public void saveReviewTag(ReviewTag reviewTag) {
		reviewTagRepository.save(reviewTag);
	}
}
