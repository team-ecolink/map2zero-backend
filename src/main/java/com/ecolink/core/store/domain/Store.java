package com.ecolink.core.store.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.event.domain.Event;
import com.ecolink.core.manager.domain.StoreRegistration;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	private boolean isRefillable;

	private String contact;

	private String homepageUrl;

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
	@JoinColumn(name = "store_registration_id")
	private StoreRegistration storeRegistration;

	@OneToMany(mappedBy = "store")
	private List<StorePhoto> storePhotos = new ArrayList<>();

	@OneToMany(mappedBy = "store")
	private List<StoreProduct> storeProducts = new ArrayList<>();

	@OneToMany(mappedBy = "store")
	private List<Event> events = new ArrayList<>();

	@OneToMany(mappedBy = "store")
	private List<StoreOperatingHours> storeOperatingHour = new ArrayList<>();

	@OneToMany(mappedBy = "store")
	private List<StoreTag> storeTags = new ArrayList<>();

	public Double calculateScore() {
		if(totalScore == 0) return null;
		return totalScore/reviewCnt;
	}

	public void addBookmarkCount() {
		this.bookmarkCnt++;
	}

	public double getAverageScore() {
		if (reviewCnt == 0) {
			return 0.0;
		} else {
			double averageScore = totalScore / reviewCnt;
			return Math.round(averageScore * 10.0) / 10.0;
		}
	}
}
