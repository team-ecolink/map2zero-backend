package com.ecolink.core.user.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.user.domain.User;
import com.ecolink.core.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserScheduleService {

	private final UserRepository userRepository;
	private final WithdrawService withdrawService;

	@Transactional
	@Scheduled(cron = "0 0 5 * * *")
	public void delete() {
		List<User> withdrawnUsers = userRepository.findAllByWithdrawn();
		withdrawnUsers.stream()
			.filter(user -> Duration.between(user.getWithdrawnDate(), LocalDateTime.now()).getSeconds() >= 604800)
			.forEach(withdrawService::delete);
	}
}
