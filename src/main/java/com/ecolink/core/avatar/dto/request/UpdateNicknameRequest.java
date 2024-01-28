package com.ecolink.core.avatar.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateNicknameRequest {

	@Schema(description = "변경할 닉네임, 2-8자 제한", example = "제로웨이스트1")
	@Size(min = 2, max = 8)
	private final String nickname;

	public UpdateNicknameRequest(String nickname) {
		this.nickname = nickname;
	}
}
