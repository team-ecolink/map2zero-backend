package com.ecolink.core.user.constant;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.ecolink.core.auth.model.OAuth2Attributes;

public enum UserType {

	LOCAL("local") {
		@Override
		public OAuth2Attributes extract(String attributeKey, Map<String, Object> attributes) {
			throw new AssertionError("잘못된 메서드 호출이 이루어졌습니다.");
		}
	},
	KAKAO("kakao") {
		@Override
		public OAuth2Attributes extract(String attributeKey, Map<String, Object> attributes) {
			Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
			Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

			var builder = OAuth2Attributes.builder()
				.attributes(attributes)
				.userType(this)
				.attributeKey(attributeKey)
				.email((String)kakaoAccount.get("email"))
				.name((String)kakaoProfile.get("nickname"));

			if (kakaoProfile.containsKey("profile_image_url"))
				builder.hasImage(!(Boolean)kakaoProfile.get("is_default_image"))
					.profileImage((String)kakaoProfile.get("profile_image_url"));
			else
				builder.hasImage(false);

			return builder.build();
		}
	},
	NAVER("naver") {
		@Override
		public OAuth2Attributes extract(String attributeKey, Map<String, Object> attributes) {
			return OAuth2Attributes.builder()
				.attributes(attributes)
				.userType(this)
				.attributeKey(attributeKey)
				.build();
		}
	};

	private final String id;

	UserType(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}

	public abstract OAuth2Attributes extract(String attributeKey, Map<String, Object> attributes);

	private static final Map<String, UserType> stringToEnum =
		Stream.of(values()).collect(toMap(Object::toString, e -> e));

	// 지정한 문자열에 해당하는 `UserType`을 (존재한다면) 반환한다.
	public static Optional<UserType> fromString(String id) {
		return Optional.ofNullable(stringToEnum.get(id));
	}

}
