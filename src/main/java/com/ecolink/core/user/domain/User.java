package com.ecolink.core.user.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.user.constant.Password;
import com.ecolink.core.user.constant.RoleType;
import com.ecolink.core.user.constant.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String email;

	@NotNull
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Embedded
	private Password password;

	private LocalDateTime lastPwChangedDate;

	@NotNull
	private boolean activated;

	@NotNull
	private boolean locked;

	@NotNull
	private boolean expired;

	@NotNull
	private boolean withdrawn;

	private LocalDateTime withdrawnDate;

	@NotNull
	private boolean adAgreed;

	@NotNull
	private boolean analysisAgreed;

	private LocalDate lastLoginDate;

	private String fcmToken;

	@NotNull
	@JoinColumn(name = "role_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<Avatar> avatars = new ArrayList<>();

	@Builder
	private User(String email, boolean locked, boolean expired, boolean withdrawn, boolean activated, String name,
		UserType userType, Password password, Role role, LocalDate lastLoginDate) {
		this.email = email;
		this.locked = locked;
		this.expired = expired;
		this.withdrawn = withdrawn;
		this.activated = activated;
		this.name = name;
		this.userType = userType;
		this.password = password;
		this.role = role;
		this.lastLoginDate = lastLoginDate;
	}

	public static User createUser(UserCreateRequest request) {
		Assert.hasText(request.email(), "이메일이 입력되지 않았습니다.");
		Assert.hasText(request.name(), "이름이 입력되지 않았습니다");
		Assert.notNull(request.userType(), "유저타입은 null일 수 없습니다.");
		Assert.notNull(request.role(), "유저 권한(Role)은 최소한 1개는 부여되어야 합니다.");

		return User.builder()
			.email(request.email())
			.name(request.name())
			.userType(request.userType())
			.password(request.password())
			.activated(true)
			.locked(false)
			.expired(false)
			.withdrawn(false)
			.role(request.role())
			.lastLoginDate(LocalDate.now())
			.build();
	}

	/**
	 * 영속성 컨텍스트에 Role 이 없을 경우 FetchType.LAZY 옵션 때문에 SELECT 쿼리가 나가게됨
	 */
	public boolean isManager() {
		return RoleType.MANAGER.equals(role.getType()) || RoleType.ADMIN.equals(role.getType());
	}

	public boolean isUserOf(UserType providerType) {
		return providerType.equals(this.userType);
	}

	public void addAvatar(Avatar avatar) {
		this.avatars.add(avatar);
	}

}
