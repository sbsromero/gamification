package com.microservice.gamification.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor
public class BadgeCard {

	@Id
	@GeneratedValue
	@Column(name="BAGDE_ID")
	private final Long bagdeId;
	
	private final Long userId;
	private final long bagdeTimestamp;
	private final Badge bagde;
	
	//Empty constructor for JSON/JPA
	public BadgeCard() {
		this(null,null,0,null);
	}
	
	public BadgeCard(final Long userId, final Badge badge) {
		this(null, userId, System.currentTimeMillis(), badge);
	}
}
