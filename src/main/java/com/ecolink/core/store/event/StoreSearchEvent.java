package com.ecolink.core.store.event;

import lombok.Getter;

@Getter
public class StoreSearchEvent {

	private String keyword;
	private Long avatarId;

	public StoreSearchEvent(String keyword, Long avatarId) {
		this.keyword = keyword;
		this.avatarId = avatarId;
	}

}
