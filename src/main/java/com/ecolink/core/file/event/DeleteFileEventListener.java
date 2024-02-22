package com.ecolink.core.file.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.ecolink.core.file.service.FileStorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeleteFileEventListener {

	private final FileStorageService fileStorageService;

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = DeleteFileEvent.class)
	public void onCommitAfterDeleteFile(DeleteFileEvent event) {
		fileStorageService.deleteBackups(event.getS3keysForBackup());
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK, classes = DeleteFileEvent.class)
	public void onRollbackAfterDeleteFile(DeleteFileEvent event) {
		log.error("작업에 실패해 백업파일을 복구합니다. keys: {}", event.getS3keysForBackup().toString());
		fileStorageService.recoverBackups(event.getS3keysForBackup());
	}

}
