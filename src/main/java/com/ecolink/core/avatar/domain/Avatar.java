package com.ecolink.core.avatar.domain;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Avatar extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotNull
	@Size(min = 4, max = 12)
	private String nickname;

	@NotNull
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@JoinColumn(name = "photo_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private ProfilePhoto photo;

	@Builder
	private Avatar(String nickname, User user, ProfilePhoto photo) {
		this.nickname = nickname;
		this.user = user;
		this.photo = photo;
	}

	public static Avatar of(AvatarCreateRequest request) {
		Assert.notNull(request.user(), "User는 null일 수 없습니다.");
		Assert.notNull(request.photo(),"Photo는 null일 수 없습니다.");
		validateNickname(request.nickname());

		Avatar avatar = Avatar.builder()
			.nickname(request.nickname())
			.user(request.user())
			.photo(request.photo())
			.build();
		request.user().addAvatar(avatar);
		return avatar;
	}

	public void updateNickname(String requestedNickname) {
		validateNickname(requestedNickname);
		this.nickname = requestedNickname;
	}

	private static void validateNickname(String nickname) {
		Assert.hasText(nickname, "닉네임이 입력되지 않았습니다.");
		if(nickname.length() < 2 || nickname.length() > 8)
			throw new IllegalArgumentException("닉네임의 길이는 최소 2자, 최대 8자입니다.");
		if (!nickname.matches("^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$"))
			throw new IllegalArgumentException("닉네임에는 영문자, 한글, 숫자만 입력 가능합니다.");
	}

}
