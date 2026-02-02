package com.su8y.bootstrap.domain.score;

import java.util.List;

import lombok.Getter;

@Getter
public class StockRank {
    private String ticker;
    private String name;
    private int finalScore;
    private List<String> issues; // Assuming issues are represented by strings for now


    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public void setIssues(List<String> issues) {
        this.issues = issues;
    }
}
