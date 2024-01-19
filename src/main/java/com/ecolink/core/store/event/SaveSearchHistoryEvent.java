package com.ecolink.core.store.event;

import org.springframework.context.ApplicationEvent;

import com.ecolink.core.store.domain.SearchHistory;

public class SaveSearchHistoryEvent extends ApplicationEvent {

	private final SearchHistory searchHistory;

	public SaveSearchHistoryEvent(Object source, SearchHistory searchHistory) {
		super(source);
		this.searchHistory = searchHistory;
	}

	public SearchHistory getSearchHistory() {
		return searchHistory;
	}

}
