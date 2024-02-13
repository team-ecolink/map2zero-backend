package com.ecolink.core.common.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ecolink.core.auth.handler.JsonLogoutSuccessHandler;
import com.ecolink.core.auth.handler.OAuth2SuccessHandler;
import com.ecolink.core.auth.service.OAuth2UserLoadingService;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private final OAuth2SuccessHandler oauth2SuccessHandler;
	private final OAuth2UserLoadingService oauth2UserLoadingService;
	private final JsonLogoutSuccessHandler logoutSuccessHandler;
	private final String apiPrefix;

	public SecurityConfig(OAuth2SuccessHandler oauth2SuccessHandler, OAuth2UserLoadingService oauth2UserLoadingService,
		JsonLogoutSuccessHandler logoutSuccessHandler, @Value("${api.prefix}") String apiPrefix) {
		this.oauth2SuccessHandler = oauth2SuccessHandler;
		this.oauth2UserLoadingService = oauth2UserLoadingService;
		this.logoutSuccessHandler = logoutSuccessHandler;
		this.apiPrefix = apiPrefix;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(websiteConfigurationSource()))
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.oauth2Login(oauth ->
				oauth.authorizationEndpoint(authorization -> authorization.baseUri(apiPrefix + "/oauth2/authorization"))
					.redirectionEndpoint(redirection -> redirection.baseUri(apiPrefix + "/oauth2/code"))
					.userInfoEndpoint(userInfo -> userInfo.userService(oauth2UserLoadingService))
					.successHandler(oauth2SuccessHandler))
			.logout(logout -> logout.logoutUrl(apiPrefix + "/logout")
				.logoutSuccessHandler(logoutSuccessHandler))
			.headers(headers ->
				headers.addHeaderWriter(
					new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));

		return http.build();
	}

	CorsConfigurationSource websiteConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://eco-link.netlify.app"));
		configuration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "PATCH"));
		configuration.setAllowedHeaders(List.of(AUTHORIZATION_HEADER, "Content-Type"));
		configuration.setExposedHeaders(List.of(AUTHORIZATION_HEADER));
		configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	static HttpSessionIdResolver httpSessionIdResolver() {
		return new HeaderHttpSessionIdResolver(AUTHORIZATION_HEADER);
	}

	@Bean
	static RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
		hierarchy.setHierarchy("""
			ROLE_ADMIN > ROLE_MANAGER
			ROLE_MANAGER > ROLE_USER
			""");
		return hierarchy;
	}

	// Method Security 에 권한 계층 적용시 필요한 핸들러
	@Bean
	static MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy);
		return expressionHandler;
	}

}

