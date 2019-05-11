package com.microservice.gamification.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@Entity
@EqualsAndHashCode
public class ScoreCard {
	
	public static final int DEFAULT_SCORE = 10;

	@Id
	@GeneratedValue
	@Column(name="CARD_ID")
	private final Long cardId;
	
	@Column(name="USER_ID")
	private final Long userId;
	
	@Column(name="ATTEMPT_ID")
	private final Long attemptId;

	@Column(name="SCORE_TS")
	private final long scoreTimestamp;
	
	@Column(name="SCORE")
	private final int score;
	
	//Empty constructor for JSON / JPA
	public ScoreCard() {
		this(null,null,null,0,0);
	}
	
	public ScoreCard(final Long userId, final Long attempId) {
		this(null, userId, attempId, System.currentTimeMillis(),DEFAULT_SCORE);
	}
}
