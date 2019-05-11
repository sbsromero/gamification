package com.microservice.gamification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.gamification.domain.BadgeCard;
import com.microservice.gamification.domain.GameStats;
import com.microservice.gamification.domain.ScoreCard;
import com.microservice.gamification.repository.BadgeCardRepository;
import com.microservice.gamification.repository.ScoreCardRepository;

@Service
public class GameServiceImpl implements GameService {

	private BadgeCardRepository badgeCardRepository;
	private ScoreCardRepository scoreCardRepository;

	@Autowired
	public GameServiceImpl(BadgeCardRepository badgeCardRepository, ScoreCardRepository scoreCardRepository) {
		this.badgeCardRepository = badgeCardRepository;
		this.scoreCardRepository = scoreCardRepository;
	}

	@Override
	public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
		if (correct) {
			ScoreCard scoreCard = new ScoreCard(userId, attemptId);
			List<BadgeCard> badgeCards = processForBadges(userId, attemptId);
			return new GameStats(userId, scoreCard.getScore(),
					badgeCards.stream().map(BadgeCard::getBagde).collect(Collectors.toList()));
		}
		return GameStats.emptyStats(userId);
	}

	private List<BadgeCard> processForBadges(Long userId, Long attemptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameStats retrieveStatsForUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
