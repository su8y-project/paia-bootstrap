package com.su8y.bootstrap.adapter.in.web;

import com.su8y.bootstrap.adapter.in.web.common.ApiResponse;
import com.su8y.bootstrap.domain.issue.DailyIssues;
import com.su8y.paia.application.port.in.IssueUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

	private final IssueUseCase issueUseCase;

	public IssueController(IssueUseCase issueUseCase) {
		this.issueUseCase = issueUseCase;
	}

	@GetMapping("/summary")
	@PreAuthorize("permitAll()")
	public ResponseEntity<ApiResponse<?>> getDailyIssueSummary() {

		var response = new DailyIssues(LocalDate.now(), issueUseCase.getAllIssues());
		return ResponseEntity.ok(ApiResponse.success(List.of(response)));
	}

	@GetMapping("/create")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> sceduleDailyIssueSummary() {
		issueUseCase.analyzeNews("이슈를 생성해줘");
		return ResponseEntity.ok(issueUseCase.getLatestSectorScores());
	}
}
