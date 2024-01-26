package com.ecolink.core.avatar.service;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.repository.AvatarRepository;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.NicknameSelectFailException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NicknameService {

	private static final List<String> WORDS = List.of("세글자");
	private static final Random RANDOM = new Random();

	private final AvatarRepository avatarRepository;

	public String getUniqueRandomNickname() {
		String nickname;
		int count = 0;
		do {
			String pre = WORDS.get(RANDOM.nextInt(WORDS.size()));
			String post = RandomStringUtils.randomAlphanumeric(4);
			nickname = pre + post;
			if (count++ > 10)
				throw new NicknameSelectFailException(ErrorCode.FAIL_TO_FIND_UNIQUE_NICKNAME);
		} while (isNicknameInUse(nickname));
		return nickname;
	}

	public boolean isNicknameInUse(String nickname) {
		return avatarRepository.existsByNickname(nickname);
	}
}
