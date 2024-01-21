package com.ecolink.core.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.like.domain.ReviewLike;
import com.ecolink.core.review.domain.Review;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
	Optional<ReviewLike> findByAvatarAndReview(Avatar avatar, Review review);
}
