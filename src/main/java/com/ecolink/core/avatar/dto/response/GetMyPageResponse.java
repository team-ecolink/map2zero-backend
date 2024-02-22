package com.ecolink.core.avatar.dto.response;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.user.constant.UserType;
import com.ecolink.core.user.domain.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GetMyPageResponse(
	@Schema(description = "이메일", example = "example@naver.com")
	String email,
	@Schema(description = "닉네임", example = "김철수")
	String nickname,
	@Schema(description = "프로필 사진 URL")
	ImageFile photo,
	@Schema(description = "유저가 가입한 플랫폼 종류 KAKAO, NAVER", example = "KAKAO")
	UserType type,
	@Schema(description = "매니저 회원 여부", example = "false")
	boolean isManager
) {

	public static GetMyPageResponse of(User user, Avatar avatar, boolean isManager) {
		return GetMyPageResponse.builder()
			.email(user.getEmail())
			.nickname(avatar.getNickname())
			.photo(avatar.getPhoto().getFile())
			.type(user.getUserType())
			.isManager(isManager)
			.build();
	}
}
