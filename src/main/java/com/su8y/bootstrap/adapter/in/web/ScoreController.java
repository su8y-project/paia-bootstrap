package com.su8y.bootstrap.adapter.in.web;

import com.su8y.bootstrap.adapter.in.web.common.ApiResponse;
import com.su8y.bootstrap.application.port.in.ScoreUseCase;
import com.su8y.bootstrap.domain.score.StockRank;
import com.su8y.bootstrap.domain.score.TagRank;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scores")
@PreAuthorize("permitAll()")
public class ScoreController {

	private final ScoreUseCase scoreUseCase;

	public ScoreController(ScoreUseCase scoreUseCase) {
		this.scoreUseCase = scoreUseCase;
	}

	@GetMapping("/ranks/stocks")
	public ResponseEntity<ApiResponse<List<StockRank>>> getStockScoreRanks() {
		List<StockRank> ranks = scoreUseCase.getStockScoreRanks();
		return ResponseEntity.ok(ApiResponse.success(ranks));
	}

	@GetMapping("/ranks/tags")
	public ResponseEntity<ApiResponse<List<TagRank>>> getTagScoreRanks() {
		List<TagRank> ranks = scoreUseCase.getTagScoreRanks();
		return ResponseEntity.ok(ApiResponse.success(ranks));
	}
}
