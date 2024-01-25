package com.ecolink.core.store.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.store.domain.SearchHistory;
import com.ecolink.core.store.repository.SearchHistoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SearchHistoryService {

	private final SearchHistoryRepository searchHistoryRepository;

	@Transactional
	public void saveSearchHistory(String keyword, Avatar avatar) {
		if (avatar != null) {
			searchHistoryRepository.save(new SearchHistory(keyword, avatar));
		}
	}
}
