package com.ecolink.core.store.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.ecolink.core.store.service.SearchHistoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SaveSearchHistoryEventListener {

	private final SearchHistoryService searchHistoryService;

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION, classes = SaveSearchHistoryEvent.class)
	public void saveSearchHistory(SaveSearchHistoryEvent event) {
		searchHistoryService.saveSearchHistory(event.getSearchHistory().getWord(),
			event.getSearchHistory().getAvatar());
	}
}
