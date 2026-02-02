package com.su8y.bootstrap.application.service;

import com.su8y.bootstrap.application.port.in.IssueUseCase;
import com.su8y.bootstrap.application.port.out.IssueAnalysisPort;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueService implements IssueUseCase {
	private final IssueAnalysisPort issueAnalysisPort;


    @Override
	public List<DailyIssueSummary> getDailyIssueSummary() {
		var summary = issueAnalysisPort.analyzeIssue("미국 주식 동향에 대해서 설명해줘");
		return List.of(new DailyIssueSummary(LocalDate.now(), summary));
    }
}
