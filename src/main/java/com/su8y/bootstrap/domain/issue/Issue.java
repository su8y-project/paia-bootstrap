package com.su8y.bootstrap.domain.issue;

import java.util.List;

import lombok.Getter;

@Getter
public class Issue {
    private String issueId;
    private String title;
    private double importanceScore;
    private String summary;
    private List<String> tags;

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImportanceScore(double importanceScore) {
        this.importanceScore = importanceScore;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
