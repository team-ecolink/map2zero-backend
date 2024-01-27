package com.ecolink.core.avatar.domain;

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
		Avatar avatar = Avatar.builder()
			.nickname(request.nickname())
			.user(request.user())
			.photo(request.photo())
			.build();
		request.user().addAvatar(avatar);
		return avatar;
	}

}
