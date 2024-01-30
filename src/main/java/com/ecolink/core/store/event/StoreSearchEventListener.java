package com.ecolink.core.store.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.ecolink.core.store.service.SearchHistoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StoreSearchEventListener {

	private final SearchHistoryService searchHistoryService;

	@Async
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT, classes = StoreSearchEvent.class)
	public void saveSearchHistory(StoreSearchEvent event) {
		searchHistoryService.saveSearchHistory(event.getKeyword(), event.getAvatarId());
	}
}
