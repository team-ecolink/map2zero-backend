package com.ecolink.core.store.dto;

import java.util.List;

import com.ecolink.core.store.domain.StoreProduct;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDto {
	@Schema(description = "제품 아이디", example = "1")
	Long id;
	@Schema(description = "제품 이름", example = "대나무 칫솔")
	String name;
	@Schema(description = "제품 가격", example = "2000")
	Integer price;

	public ProductDto(Long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public static ProductDto of(StoreProduct storeProduct) {
		return new ProductDto(storeProduct.getId(), storeProduct.getProduct().getName(), storeProduct.getProduct().getPrice());
	}
}
