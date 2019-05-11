package com.microservice.gamification.service;

import com.microservice.gamification.domain.GameStats;

public interface GameService {

	public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct);

	public GameStats retrieveStatsForUser(Long userId);
}
