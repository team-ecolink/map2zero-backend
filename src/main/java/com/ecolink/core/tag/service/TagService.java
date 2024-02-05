package com.ecolink.core.tag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.common.error.exception.InvalidTagCategoryException;
import com.ecolink.core.tag.constant.TagCategory;
import com.ecolink.core.tag.domain.Tag;
import com.ecolink.core.tag.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TagService {

	private final TagRepository tagRepository;

	public Tag getById(Long id) {
		return tagRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.TAG_NOT_FOUND));
	}

	public void checkTagsAreReviewCategories(List<Tag> tags) {
		tags.forEach(tag -> {
			if (!TagCategory.REVIEW.equals(tag.getCategory()))
				throw new InvalidTagCategoryException(ErrorCode.NOT_REVIEW_CATEGORY);
		});
	}
}
