package com.ecolink.core.common.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ImageFile {

	@NotNull
	private String url;

	@NotNull
	private String s3Key;

	@NotNull
	private Long byteSize;

	private Integer width;

	private Integer height;

}
