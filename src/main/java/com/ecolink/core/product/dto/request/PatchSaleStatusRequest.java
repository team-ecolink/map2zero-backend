package com.ecolink.core.product.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PatchSaleStatusRequest {

	@Schema(description = "매장 제품의 ID", example = "1")
	private final Long storeProductId;
	@Schema(description = "변경할 판매 상태", example = "true")
	private final boolean onSale;

	public PatchSaleStatusRequest(Long storeProductId, boolean onSale) {
		this.storeProductId = storeProductId;
		this.onSale = onSale;
	}

}
