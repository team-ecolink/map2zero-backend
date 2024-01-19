package com.ecolink.core.store.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.ecolink.core.store.domain.SearchHistory;
import com.ecolink.core.store.repository.SearchHistoryRepository;

@Component
public class SaveSearchHistoryEventListener implements ApplicationListener<SaveSearchHistoryEvent> {

	private final SearchHistoryRepository searchHistoryRepository;

	public SaveSearchHistoryEventListener(SearchHistoryRepository searchHistoryRepository) {
		this.searchHistoryRepository = searchHistoryRepository;
	}

	@Override
	public void onApplicationEvent(SaveSearchHistoryEvent event) {
		SearchHistory searchHistory = event.getSearchHistory();
		searchHistoryRepository.save(searchHistory);
	}
}
