package com.su8y.bootstrap.domain.issue;

import com.su8y.paia.core.domain.Issue;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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
