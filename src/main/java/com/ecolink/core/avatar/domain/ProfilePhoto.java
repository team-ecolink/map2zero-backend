package com.ecolink.core.avatar.domain;

import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.file.domain.SinglePhoto;

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
public class ProfilePhoto extends BaseTimeEntity implements SinglePhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@NotNull
	@Embedded
	private ImageFile file;

	public ProfilePhoto(ImageFile file) {
		this.file = file;
	}

}
