package com.ecolink.core.auth.model;

import java.util.HashMap;
import java.util.Map;

import com.ecolink.core.user.constant.UserType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuth2Attributes {

	private Map<String, Object> attributes;
	private UserType userType;
	private String attributeKey;
	private String email;
	private String name;
	private boolean hasImage;
	private String profileImage;

	public static OAuth2Attributes of(Map<String, Object> attributes) {
		return OAuth2Attributes.builder()
			.userType((UserType)attributes.get("userType"))
			.attributeKey((String)attributes.get("key"))
			.email((String)attributes.get("email"))
			.name((String)attributes.get("name"))
			.hasImage((Boolean)attributes.get("hasImage"))
			.profileImage((String)attributes.get("profileImage"))
			.build();
	}

	public Map<String, Object> convertToMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("userType", userType);
		map.put("id", attributeKey);
		map.put("key", attributeKey);
		map.put("email", email);
		map.put("name", name);
		map.put("hasImage", hasImage);
		map.put("profileImage", profileImage);
		return map;
	}

	public boolean hasImage() {
		return this.hasImage;
	}

}
