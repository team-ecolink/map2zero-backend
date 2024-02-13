package com.ecolink.core.product.service;

import org.springframework.stereotype.Service;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.InvalidProductPriceException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductPriceService {

	public void verifyPrice(int price) {
		if (price < 0)
			throw new InvalidProductPriceException(ErrorCode.NEGATIVE_PRICE_NOT_ALLOWED);
	}

}
