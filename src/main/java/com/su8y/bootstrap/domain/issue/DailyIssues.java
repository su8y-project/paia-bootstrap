package com.su8y.bootstrap.domain.issue;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;

@Getter
public class DailyIssues {
    private LocalDate date;
    private List<Issue> issues;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
