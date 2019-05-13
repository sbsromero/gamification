package com.microservice.gamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.gamification.domain.GameStats;
import com.microservice.gamification.service.GameService;

@RestController
@RequestMapping("/stats")
public class UserStatsController {

	private final GameService gameService;
	
	@Autowired
	public UserStatsController(final GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping
	public GameStats getStatsForUser(@RequestParam("userId") final Long userId) {
		return gameService.retrieveStatsForUser(userId);
	}
}
