package com.microservice.gamification.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class LeaderBoardRow {

	private final Long userId;
	private final Long totalScore;
	
	//Empty constructor for JSON/JPA
	public LeaderBoardRow() {
		this(0L,0L);
	}
}
