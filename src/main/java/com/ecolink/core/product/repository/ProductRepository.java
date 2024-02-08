package com.ecolink.core.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.tag.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p "
		+ "where p.name = :name")
	Optional<Product> findByName(@Param("name") String name);

}
