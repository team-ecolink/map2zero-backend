package com.ecolink.core.auth.dto;

import com.ecolink.core.auth.dto.response.AuthenticationResponse;
import com.ecolink.core.auth.token.AuthenticationToken;

import lombok.Getter;

@Getter
public class AuthenticationResult {

	private final AuthenticationToken token;
	private final AuthenticationResponse response;

	public AuthenticationResult(AuthenticationToken token, AuthenticationResponse response) {
		this.token = token;
		this.response = response;
	}
}
