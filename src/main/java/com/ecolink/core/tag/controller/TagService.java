package com.ecolink.core.tag.controller;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.tag.constant.TagCategory;
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
		return tagRepository.findByCategory(request.category()).stream().map(GetTagResponse::of).toList();
	}

}
