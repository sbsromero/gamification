package com.microservice.gamification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.microservice.gamification.domain.Badge;
import com.microservice.gamification.domain.BadgeCard;
import com.microservice.gamification.domain.GameStats;
import com.microservice.gamification.domain.ScoreCard;
import com.microservice.gamification.repository.BadgeCardRepository;
import com.microservice.gamification.repository.ScoreCardRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

	public static final int LUCKY_NUMBER = 42;
	
	private BadgeCardRepository badgeCardRepository;
	private ScoreCardRepository scoreCardRepository;

	public GameServiceImpl(BadgeCardRepository badgeCardRepository, ScoreCardRepository scoreCardRepository) {
		this.badgeCardRepository = badgeCardRepository;
		this.scoreCardRepository = scoreCardRepository;
	}

	@Override
	public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
		if (correct) {
			ScoreCard scoreCard = new ScoreCard(userId, attemptId);
			scoreCardRepository.save(scoreCard);
			log.info("User with id {} scored {} points for attempt id {}", userId, scoreCard.getScore(), attemptId);
			List<BadgeCard> badgeCards = processForBadges(userId, attemptId);
			return new GameStats(userId, scoreCard.getScore(),
					badgeCards.stream().map(BadgeCard::getBagde).collect(Collectors.toList()));
		}
		return GameStats.emptyStats(userId);
	}

	private List<BadgeCard> processForBadges(Long userId, Long attemptId) {
		List<BadgeCard> badgeCards = new ArrayList<>();
		int totalScore = scoreCardRepository.getTotalScoreForUser(userId);
		log.info("New score for user {} is {} ", userId, totalScore);

		List<ScoreCard> scoreCardList = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
		List<BadgeCard> badgeCardList = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);

		checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.BRONZE_MULTIPLICATOR, totalScore, 100, userId)
				.ifPresent(badgeCards::add);
		checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.SILVER_MULTIPLICATOR, totalScore, 500, userId)
				.ifPresent(badgeCards::add);
		checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.GOLD_MULTIPLICATOR, totalScore, 999, userId)
				.ifPresent(badgeCards::add);

		if (scoreCardList.size() == 1 && !containsBadge(badgeCardList, Badge.FIRST_WON)) {
			BadgeCard firstWonBadge = giveBadgeToUser(Badge.FIRST_WON, userId);
			badgeCards.add(firstWonBadge);
		}

		return badgeCards;
	}

	@Override
	public GameStats retrieveStatsForUser(Long userId) {
		int score = scoreCardRepository.getTotalScoreForUser(userId);
		List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
		return new GameStats(userId, score, badgeCards.stream().map(BadgeCard::getBagde).collect(Collectors.toList()));
	}

	private Optional<BadgeCard> checkAndGiveBadgeBasedOnScore(List<BadgeCard> badgeCards, Badge badge,
			int score, int scoreThreshold, Long userId) {
		if(score >= scoreThreshold && !containsBadge(badgeCards, badge)) {
			return Optional.of(giveBadgeToUser(badge, userId));
		}
		return Optional.empty();
	}

	private boolean containsBadge(List<BadgeCard> badgeCards, Badge badge) {
		return badgeCards.stream().anyMatch(b -> b.getBagde().equals(badge));
	}

	private BadgeCard giveBadgeToUser(Badge badge, Long userId) {
		BadgeCard badgeCard = new BadgeCard(userId,badge);
		badgeCardRepository.save(badgeCard);
		log.info("User with id {} won a new badge: {}", userId, badge);
		return badgeCard;
	}


}
