package com.ecolink.core.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.review.domain.ReviewTag;

public interface ReviewTagRepository extends JpaRepository<ReviewTag, Long> {
}
