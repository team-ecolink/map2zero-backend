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

	private static final List<String> WORDS = List.of("수박", "사과", "딸기", "체리", "포도", "자두", "멜론",
		"레몬", "망고", "홍시", "으름", "금귤", "영귤", "감귤", "앵두", "여주", "금감", "오디", "용과", "다래", "자몽", "살구",
		"리치", "거봉", "석류", "유자", "매실", "참외", "단감", "키위", "라임", "코코넛", "오렌지", "바나나", "복숭아", "복분자",
		"한라봉", "천혜향", "레드향", "파파야", "토마토", "구아바", "만다린", "탄제린", "무화과", "청포도", "두리안", "산딸기",
		"크랜베리", "라즈베리", "블루베리", "파인애플", "아보카도", "골드키위", "망고스틴", "아로니아", "깔라만시", "블랙베리",
		"애플망고");
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
