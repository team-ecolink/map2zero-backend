package com.ecolink.core.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.core.like.domain.ReviewLike;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
	Boolean existsByAvatar_IdAndReview_Id(Long avatarId, Long reviewId);
}
