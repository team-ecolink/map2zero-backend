package com.ecolink.core.file.event;

import java.util.List;

import lombok.Getter;

@Getter
public class DeleteFileEvent {

	private final List<String> s3keysForBackup;

	public DeleteFileEvent(List<String> s3keysForBackup) {
		this.s3keysForBackup = s3keysForBackup;
	}

}
