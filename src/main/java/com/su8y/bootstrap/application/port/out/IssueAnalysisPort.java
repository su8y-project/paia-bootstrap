package com.su8y.bootstrap.application.port.out;

import java.util.List;

public interface IssueAnalysisPort {
	/**
	 * 뉴스나 텍스트를 분석하여 구조화된 이슈 객체로 반환합니다.
	 */
	List<IssueAnalysis> analyzeIssue(String rawText);

	record IssueAnalysis(
			String issueId,
			String title,
			double importanceScore,
			String summary,
			List<String> tags
	) {
	}
}
