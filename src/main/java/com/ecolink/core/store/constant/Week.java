package com.ecolink.core.store.constant;

import lombok.Getter;

@Getter
public enum Week {

	SUN("일"),
	MON("월"),
	TUE("화"),
	WED("수"),
	THU("목"),
	FRI("금"),
	SAT("토");

	private final String name;

	Week(String name) {
		this.name = name;
	}

}
