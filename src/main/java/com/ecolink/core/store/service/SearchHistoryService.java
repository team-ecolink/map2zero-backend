package com.ecolink.core.store.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.common.error.exception.ForbiddenException;
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

		int searchHistoryCount = searchHistoryRepository.countByAvatarId(avatarId);
		if (searchHistoryCount >= 10) {
			List<SearchHistory> searchHistories = getSearchHistoryListLimit(searchHistoryCount - 9, avatarId);
			searchHistoryRepository.deleteAll(searchHistories);
		}
		searchHistoryRepository.save(new SearchHistory(keyword, avatarService.getById(avatarId)));
	}

	public List<SearchHistoryDto> getSearchHistoryList(Long avatarId) {
		return searchHistoryRepository.findByAvatarIdOrderByCreatedDateDesc(avatarId)
			.stream()
			.map(SearchHistoryDto::of)
			.toList();
	}

	private List<SearchHistory> getSearchHistoryListLimit(int size, Long avatarId) {
		return searchHistoryRepository.findAllByAvatarIdOrderByCreatedDate(avatarId).stream()
			.limit(size)
			.toList();
	}

	@Transactional
	public void deleteAll(Long avatarId) {
		searchHistoryRepository.deleteAll(searchHistoryRepository.findAllByAvatarId(avatarId));
	}

	@Transactional
	public void deleteSearchHistory(Long searchHistoryId, Long avatarId) {
		SearchHistory searchHistory = getById(searchHistoryId);
		if (!Objects.equals(searchHistory.getAvatar().getId(), avatarId))
			throw new ForbiddenException(ErrorCode.SEARCH_HISTORY_FORBIDDEN);
		searchHistoryRepository.delete(searchHistory);
	}

	public SearchHistory getById(Long searchHistoryId) {
		return searchHistoryRepository.findById(searchHistoryId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.SEARCH_HISTORY_NOT_FOUND));
	}
}
