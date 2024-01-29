package com.ecolink.core.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.store.domain.SearchHistory;
import com.ecolink.core.store.dto.response.SearchHistoryDto;
import com.ecolink.core.store.repository.SearchHistoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SearchHistoryService {

	private final SearchHistoryRepository searchHistoryRepository;
	private final AvatarService avatarService;

	@Transactional
	public void saveSearchHistory(String keyword, Long avatarId) {
		if (avatarId == null)
			return;
		if (searchHistoryRepository.countByAvatarId(avatarId) == 10)
			searchHistoryRepository.delete(searchHistoryRepository.findTopByAvatarIdOrderByCreatedDate(avatarId));
		searchHistoryRepository.save(new SearchHistory(keyword, avatarService.getById(avatarId)));
	}

	public List<SearchHistoryDto> getSearchHistoryList(Long avatarId) {
		avatarService.getById(avatarId);
		return SearchHistoryDto.of(searchHistoryRepository.findByAvatarIdOrderByCreatedDateDesc(avatarId));
	}
}
