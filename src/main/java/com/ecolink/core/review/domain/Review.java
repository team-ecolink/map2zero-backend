package com.ecolink.core.review.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.like.domain.ReviewLike;
import com.ecolink.core.store.domain.Store;

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
public class Review extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String title;

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

	@OneToMany(mappedBy = "review")
	private List<ReviewPhoto> reviewPhotos = new ArrayList<>();

	@OneToMany(mappedBy = "review")
	private List<ReviewTag> reviewTags = new ArrayList<>();

	@OneToMany(mappedBy = "review")
	private List<ReviewLike> reviewLikes = new ArrayList<>();

}
