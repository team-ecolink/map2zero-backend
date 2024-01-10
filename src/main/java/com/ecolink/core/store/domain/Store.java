package com.ecolink.core.store.domain;

import java.math.BigDecimal;

import org.hibernate.annotations.ColumnDefault;

import com.ecolink.core.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@NotNull
	private String province;

	@NotNull
	private String city;

	@NotNull
	private String streetName;

	@NotNull
	private String detailAddress;

	@NotNull
	private BigDecimal x;

	@NotNull
	private BigDecimal y;


}
