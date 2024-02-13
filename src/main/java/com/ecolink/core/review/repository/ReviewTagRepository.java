package com.ecolink.core.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.review.domain.ReviewTag;

public interface ReviewTagRepository extends JpaRepository<ReviewTag, Long> {
	@Query("select rt "
		+ "from ReviewTag rt "
		+ "where rt.review.writer.id = :writerId")
	List<ReviewTag> findAllByAvatarId(@Param("writerId") Long writerId);
}
