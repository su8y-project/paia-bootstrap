package com.su8y.bootstrap.domain.score;

import java.util.List;

import lombok.Getter;

@Getter
public class TagRank {
    private String sectorCode;
    private String name;
    private double finalScore;
    private List<String> relatedIssues; // Assuming issues are represented by strings for now


    public void setSectorCode(String sectorCode) {
        this.sectorCode = sectorCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public void setRelatedIssues(List<String> relatedIssues) {
        this.relatedIssues = relatedIssues;
    }
}
