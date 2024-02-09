package com.ecolink.core.event.domain;

import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.file.domain.MultiPhoto;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class EventPhoto extends BaseTimeEntity implements MultiPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Integer givenOrder;

	@Valid
	@NotNull
	@Embedded
	private ImageFile file;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;

	private EventPhoto(ImageFile file, Integer givenOrder, Event event) {
		this.file = file;
		this.givenOrder = givenOrder;
		this.event = event;
	}

	public static EventPhoto of(ImageFile file, int order, Event event) {
		return new EventPhoto(file, order, event);
	}
}