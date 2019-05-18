package com.microservice.gamification.client;

import com.microservice.gamification.client.dto.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptClient {

	public MultiplicationResultAttempt retrieveMultiplicationResultAttemptbyId(Long attemptId);

}
