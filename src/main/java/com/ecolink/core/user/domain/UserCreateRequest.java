package com.ecolink.core.user.domain;

import com.ecolink.core.user.constant.Password;
import com.ecolink.core.user.constant.UserType;

public interface UserCreateRequest {

	String email();

	String name();

	UserType userType();

	Role role();

	Password password();

}
