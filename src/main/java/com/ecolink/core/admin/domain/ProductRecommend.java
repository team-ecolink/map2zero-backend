package com.ecolink.core.admin.domain;

import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.product.domain.StoreProduct;
import com.ecolink.core.admin.constant.RecommendType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductRecommend extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RecommendType type;

	@NotNull
	@JoinColumn(name = "store_product_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private StoreProduct storeProduct;
}
