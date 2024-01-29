package com.ecolink.core.review.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecolink.core.review.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


	@Query("select distinct r "
		+ "from Review r "
		+ "join fetch r.writer "
		+ "join fetch r.store s "
		+ "where s.id = :storeId")
	Page<Review> findAllByStore(@Param("storeId") Long storeId, Pageable pageable);

	@Query("select distinct r from Review r "
		+ "join fetch r.writer w "
		+ "join fetch r.store s "
		+ "where s.id = :storeId "
		+ "order by case when w.id = :avatarId then 1 "
		+ "else 2 "
		+ "end ")
	Page<Review> findAllByStoreAndAvatar(@Param("storeId") Long storeId, @Param("avatarId") Long avatarId, Pageable pageable);
}
