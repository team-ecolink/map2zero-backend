package com.ecolink.core.tag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.common.error.exception.TagCategoryUnmatchedException;
import com.ecolink.core.tag.constant.TagCategory;
import com.ecolink.core.tag.domain.Tag;
import com.ecolink.core.tag.dto.request.GetTagRequest;
import com.ecolink.core.tag.dto.response.GetTagResponse;
import com.ecolink.core.tag.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TagService {

	private final TagRepository tagRepository;

	public List<GetTagResponse> getTags(GetTagRequest request) {
		if (TagCategory.ALL.equals(request.category()))
			return tagRepository.findAll().stream().map(GetTagResponse::of).toList();
		return getByCategory(request.category()).stream().map(GetTagResponse::of).toList();
	}

	private List<Tag> getByCategory(TagCategory category) {
		return tagRepository.findByCategory(category);
	}

	public void validateCategory(Long tagId, TagCategory category) {
		if (!getById(tagId).isCategoryOf(category))
			throw new TagCategoryUnmatchedException(ErrorCode.TAG_UNMATCHED_CATEGORY);
	}

	public Tag getById(Long tagId) {
		return getAll().stream().filter(t -> t.getId().equals(tagId)).findFirst()
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.TAG_NOT_FOUND));
	}

	// TODO 로컬 캐시(ehcache) 추가
	private List<Tag> getAll() {
		return tagRepository.findAll();
	}

	public Tag findById(Long id) {
		return tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.TAG_NOT_FOUND));
	}

	public void checkTagsAreReviewCategories(List<Tag> tags) {
		tags.forEach(tag -> {
			if (!TagCategory.REVIEW.equals(tag.getCategory()))
				throw new TagCategoryUnmatchedException(ErrorCode.NOT_REVIEW_CATEGORY);
		});
	}

}
