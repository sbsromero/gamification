package com.microservice.gamification.service;

import java.util.List;

import com.microservice.gamification.domain.LeaderBoardRow;

public interface LeaderBoardService {

	public List<LeaderBoardRow> getCurrentLeaderBoard();
}
