package com.ecolink.core.common.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@Configuration
@Profile({"local", "dev"})
public class SwaggerConfig {

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
			.info(
				new Info().title("Map2:0 API 문서")
					.description("""
						### Map2:0 REST API 명세 문서입니다.
						- #### 자물쇠 버튼으로 `Authorization` 헤더 설정이 가능합니다.
						- #### API 사용 관련 문의는 **Slack**의 <a href="https://eco-linkhq.slack.com/archives/C067CSULV8B" target="_blank">소통-be 채널</a>에 남겨주세요.
						- #### API 관련 버그 및 수정 소요, 제안 등이 있다면 <a href="https://github.com/team-ecolink/Ecolink-backend/issues" target="_blank">깃허브 이슈</a>에 등록해주시면 감사하겠습니다.
						""")
					.version("v0.1"))
			.externalDocs(new ExternalDocumentation()
				.description("API 명세 계획(노션)")
				.url("https://long-rooster-05c.notion.site/API-f6b597ed398e42d5bba09dd520a85f35?pvs=4"))
			.components(authComponent());
	}

	private static Components authComponent() {
		return new Components().addSecuritySchemes("session-token",
			new SecurityScheme()
				.type(SecurityScheme.Type.APIKEY)
				.in(SecurityScheme.In.HEADER)
				.name("Authorization"));
	}
}
