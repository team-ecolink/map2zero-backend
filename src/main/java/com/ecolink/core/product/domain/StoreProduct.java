package com.ecolink.core.product.domain;

import java.util.ArrayList;
import java.util.List;

import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.file.domain.MultiPhotoContainer;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.tag.domain.Product;
import com.ecolink.core.tag.domain.Tag;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreProduct extends BaseTimeEntity implements MultiPhotoContainer<StoreProductPhoto> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private boolean onSale;

	@NotNull
	private boolean refillable;

	@NotNull
	private boolean main;

	@NotNull
	private Integer price;

	@NotNull
	@JoinColumn(name = "store_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Store store;

	@NotNull
	@JoinColumn(name = "product_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	@NotNull
	@JoinColumn(name = "tag_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Tag tag;

	@OneToMany(mappedBy = "storeProduct", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StoreProductPhoto> photos = new ArrayList<>();

	public StoreProduct(int price, Store store, Product product, Tag tag) {
		this.price = price;
		this.store = store;
		this.product = product;
		this.tag = tag;
		this.onSale = true;
		this.refillable = false;
		this.main = false;
	}

	public void updateSaleStatus(boolean onSale) {
		this.onSale = onSale;
	}

}
