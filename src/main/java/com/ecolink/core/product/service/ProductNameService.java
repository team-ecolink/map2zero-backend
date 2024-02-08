package com.ecolink.core.product.service;

import org.springframework.stereotype.Service;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.InvalidProductNameException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductNameService {

	public void verifyName(String productName) {
		if (!productName.matches("[a-zA-Z0-9ㄱ-힣\\s]*"))
			throw new InvalidProductNameException(ErrorCode.INVALID_PRODUCT_NAME_FORMAT);
		if (productName.length() < 2 || productName.length() > 20)
			throw new InvalidProductNameException(ErrorCode.INVALID_PRODUCT_NAME_LENGTH);
	}

}
