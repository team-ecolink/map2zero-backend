package com.ecolink.core.store.domain;

import java.math.BigDecimal;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.manager.domain.StoreRegistration;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Store extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	private String description;

	@NotNull
	private boolean refillable;

	private String contact;

	private String youtubeUrl;

	private String instagramUrl;

	private int bookmarkCnt;

	private int reviewCnt;

	private double totalScore;

	@Valid
	@NotNull
	@Embedded
	private Address address;

	@NotNull
	private BigDecimal x;

	@NotNull
	private BigDecimal y;

	@Nullable
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "photo_id")
	private StoreRegistration photo;

}
