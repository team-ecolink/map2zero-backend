package com.ecolink.core.store.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.event.domain.Event;
import com.ecolink.core.manager.domain.StoreRegistration;
import com.ecolink.core.product.domain.StoreProduct;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "store", indexes = @Index(name = "idx_store_name", columnList = "name"))
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

	public double averageScore() {
		if (reviewCnt == 0)
			return 0;
		return totalScore / reviewCnt;
	}

	public double roundedAverageScore() {
		return Math.round(averageScore() * 10.0) / 10.0;
	}

	public void addBookmarkCount() {
		this.bookmarkCnt++;
	}

	public void deleteBookmarkCount() {
		if (this.bookmarkCnt <= 0) {
			throw new IllegalStateException("북마크 수는 음수가 될 수 없습니다.");
		}
		this.bookmarkCnt--;
	}

	public void addReviewCount() {
		this.reviewCnt++;
	}

	public void addTotalScore(int score) {
		this.totalScore += score;
	}
}
