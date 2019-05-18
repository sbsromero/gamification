package com.microservice.gamification.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.microservice.gamification.client.MultiplicationResultAttemptDeserializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@JsonDeserialize(using = MultiplicationResultAttemptDeserializer.class)
public final class MultiplicationResultAttempt {

	private final String userAlias;

	private final int multiplicationFactorA;
	private final int multiplicationFactorB;
	private final int resultAttempt;

	private final boolean correct;

	public MultiplicationResultAttempt() {
		userAlias = null;
		multiplicationFactorA = -1;
		multiplicationFactorB = -1;
		resultAttempt = -1;
		correct = false;
	}
}
