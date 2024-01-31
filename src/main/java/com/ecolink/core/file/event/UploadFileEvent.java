package com.ecolink.core.file.event;

import java.util.List;

import lombok.Getter;

@Getter
public class UploadFileEvent {

	private final List<String> addedS3keys;

	public UploadFileEvent(List<String> addedS3keys) {
		this.addedS3keys = addedS3keys;
	}

}
