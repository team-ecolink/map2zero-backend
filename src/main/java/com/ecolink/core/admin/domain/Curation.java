package com.ecolink.core.admin.domain;

import com.ecolink.core.common.domain.ImageFile;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Curation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String title;

	private String description;

	@NotNull
	private String notionUrl;

	@AttributeOverrides({
		@AttributeOverride(name = "url", column = @Column(name = "photo_url"))
	})
	@Valid
	@NotNull
	@Embedded
	private ImageFile photo;

	@AttributeOverrides({
		@AttributeOverride(name = "url", column = @Column(name = "m_photo_url")),
		@AttributeOverride(name = "s3Key", column = @Column(name = "m_s3_key")),
		@AttributeOverride(name = "byteSize", column = @Column(name = "m_byte_size")),
		@AttributeOverride(name = "width", column = @Column(name = "m_width")),
		@AttributeOverride(name = "height", column = @Column(name = "m_height"))
	})
	@Valid
	@NotNull
	@Embedded
	private ImageFile mobilePhoto;

}
