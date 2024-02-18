package com.ecolink.core.review.domain;

import java.util.ArrayList;
import java.util.List;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.file.domain.MultiPhotoContainer;
import com.ecolink.core.like.domain.ReviewLike;
import com.ecolink.core.review.dto.request.CreateReviewRequest;
import com.ecolink.core.store.domain.Store;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseTimeEntity implements MultiPhotoContainer<ReviewPhoto> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String text;

	@NotNull
	private int score;

	private int likeCnt;

	@NotNull
	@JoinColumn(name = "writer_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Avatar writer;

	@NotNull
	@JoinColumn(name = "store_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Store store;

	@OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReviewPhoto> photos = new ArrayList<>();

	@OneToMany(mappedBy = "review")
	private List<ReviewTag> reviewTags = new ArrayList<>();

	@OneToMany(mappedBy = "review")
	private List<ReviewLike> reviewLikes = new ArrayList<>();

	public Review(CreateReviewRequest request, Store store, Avatar avatar) {
		this.text = request.getText();
		this.score = request.getScore();
		this.writer = avatar;
		this.store = store;
	}

	public void addReviewLikeCount() {
		this.likeCnt++;
	}

	public void subtractReviewLikeCount() {
		if (this.likeCnt <= 0) {
			throw new IllegalStateException("좋아요 수는 음수가 될 수 없습니다.");
		}
		this.likeCnt--;
	}

}
