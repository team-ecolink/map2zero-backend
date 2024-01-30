package com.ecolink.core.store.domain;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SearchHistory extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String word;

	@NotNull
	@JoinColumn(name = "avatar_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Avatar avatar;

	public SearchHistory(String word, Avatar avatar) {
		this.word = word;
		this.avatar = avatar;
	}
}
