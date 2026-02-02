package com.su8y.bootstrap.application.service;

import com.su8y.bootstrap.application.port.in.ScoreUseCase;
import com.su8y.bootstrap.domain.score.StockRank;
import com.su8y.bootstrap.domain.score.TagRank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService implements ScoreUseCase {

    @Override
    public List<StockRank> getStockScoreRanks() {
        StockRank rank1 = new StockRank();
        rank1.setTicker("211050");
        rank1.setName("현대오토에버");
        rank1.setFinalScore(100);
        rank1.setIssues(List.of());

        StockRank rank2 = new StockRank();
        rank2.setTicker("068270");
        rank2.setName("셀트리온");
        rank2.setFinalScore(43);
        rank2.setIssues(List.of());

        return List.of(rank1, rank2);
    }

    @Override
    public List<TagRank> getTagScoreRanks() {
        TagRank rank1 = new TagRank();
        rank1.setSectorCode("IT_TECH");
        rank1.setName("IT/기술주");
        rank1.setFinalScore(92.5);
        rank1.setRelatedIssues(List.of());

        TagRank rank2 = new TagRank();
        rank2.setSectorCode("FINANCE");
        rank2.setName("금융/은행");
        rank2.setFinalScore(45.8);
        rank2.setRelatedIssues(List.of());

        return List.of(rank1, rank2);
    }
}
