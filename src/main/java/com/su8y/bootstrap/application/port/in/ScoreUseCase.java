package com.su8y.bootstrap.application.port.in;

import com.su8y.bootstrap.domain.score.StockRank;
import com.su8y.bootstrap.domain.score.TagRank;

import java.util.List;

public interface ScoreUseCase {
    List<StockRank> getStockScoreRanks();
    List<TagRank> getTagScoreRanks();
}
