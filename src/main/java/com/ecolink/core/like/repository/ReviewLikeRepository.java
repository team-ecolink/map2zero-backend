package com.ecolink.core.like.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecolink.core.like.domain.ReviewLike;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

	@Query("select l from ReviewLike l "
		+ "where l.review.id in :reviewIds "
		+ "and l.avatar.id = :avatarId")
	List<ReviewLike> findAllByReviewList(@Param("reviewIds") List<Long> reviewIds,
		@Param("avatarId") Long avatarId);

	@Query("select (count(rl) > 0) from ReviewLike rl "
		+ "where rl.avatar.id = :avatarId "
		+ "and rl.review.id = :reviewId")
	boolean existsByAvatarAndReview(@Param("avatarId") Long avatarId, @Param("reviewId") Long reviewId);

	Optional<ReviewLike> findReviewLikeByAvatarIdAndReviewId(Long avatarId, Long reviewId);

}
