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

    @Query("select r from Review r "
            + "join fetch r.writer "
            + "where r.store.id = :storeId")
    Page<Review> findAllByStore(@Param("storeId") Long storeId, Pageable pageable);

    @Query("select r from Review r "
            + "join fetch r.writer w "
            + "where r.store.id = :storeId "
            + "and w.id = :avatarId")
    Page<Review> findAllByStoreAndAvatar(@Param("storeId") Long storeId,
                                         @Param("avatarId") Long avatarId,
                                         Pageable pageable);
}
