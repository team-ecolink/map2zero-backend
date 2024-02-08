package com.ecolink.core.product.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.product.repository.ProductRepository;
import com.ecolink.core.tag.domain.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductNameService productNameService;

	public Product getByName(String name, boolean create) {
		Optional<Product> optionalProduct = productRepository.findByName(name);
		if (optionalProduct.isPresent())
			return optionalProduct.get();
		if (create)
			return createProduct(name);
		throw new EntityNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
	}

	public Product createProduct(String name) {
		productNameService.verifyName(name);
		return productRepository.save(new Product(name));
	}

}
