package com.su8y.bootstrap.application.port.in;

import com.su8y.bootstrap.application.port.out.IssueAnalysisPort;

import java.time.LocalDate;
import java.util.List;

public interface IssueUseCase {
	List<DailyIssueSummary> getDailyIssueSummary();

	record DailyIssueSummary(LocalDate date, List<IssueAnalysisPort.IssueAnalysis> issues) {
	}
}
