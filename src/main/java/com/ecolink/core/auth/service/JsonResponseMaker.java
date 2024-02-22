package com.ecolink.core.auth.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.ecolink.core.common.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JsonResponseMaker {

	private final ObjectMapper mapper;

	public void mapToJson(HttpServletResponse response, Object dto) throws IOException {
		String convertedDto = mapper.writeValueAsString(ApiResponse.ok(dto));

		response.setStatus(HttpStatus.ACCEPTED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.toString());

		PrintWriter writer = response.getWriter();
		writer.write(convertedDto);
	}
}
