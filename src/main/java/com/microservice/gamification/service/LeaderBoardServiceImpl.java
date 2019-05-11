package com.microservice.gamification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.gamification.domain.LeaderBoardRow;
import com.microservice.gamification.repository.ScoreCardRepository;

public class LeaderBoardServiceImpl implements LeaderBoardService {

	
	private ScoreCardRepository scoreCardRepository;
	
	@Autowired
	public LeaderBoardServiceImpl(final ScoreCardRepository scoreCardRepository) {
		this.scoreCardRepository = scoreCardRepository;
	}
	
	@Override
	public List<LeaderBoardRow> getCurrentLeaderBoard() {
		return scoreCardRepository.findFirst10();
	}

}
