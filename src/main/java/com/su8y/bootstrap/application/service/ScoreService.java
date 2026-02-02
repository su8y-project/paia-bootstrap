package com.su8y.bootstrap.application.service;

import com.su8y.bootstrap.application.port.in.ScoreUseCase;
import com.su8y.bootstrap.domain.score.StockRank;
import com.su8y.bootstrap.domain.score.TagRank;
import com.su8y.paia.application.port.in.IssueUseCase;
import com.su8y.paia.application.port.out.SectorRepository;
import com.su8y.paia.core.domain.Sector;
import com.su8y.paia.core.domain.SectorScore;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreService implements ScoreUseCase {
	private final SectorRepository sectorRepository;
	private final IssueUseCase issueUseCase;

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
		Map<String, Double> scoreMap = issueUseCase.getLatestSectorScores().stream()
				.collect(Collectors.toMap(
						SectorScore::sectorId,
						SectorScore::score,
						(existing, replacement) -> existing // 중복 ID 발생 시 기존 값 유지
				));
		List<Sector> allByLevel = sectorRepository.findAllByLevel(2);

		return allByLevel.stream().map(sector -> {
					TagRank rank = new TagRank();
					rank.setSectorCode(sector.code());
					rank.setName(sector.name());

					// 3. Map에서 해당 섹터의 점수를 가져오고, 없으면 0.0 처리
					Double score = scoreMap.getOrDefault(sector.id(), 0.0);
					rank.setFinalScore(score);

					// 4. 연관 이슈 (현재는 빈 리스트이나, 추후 확장성 고려)
					rank.setRelatedIssues(List.of());

					return rank;
				})
				// 5. 점수가 높은 순으로 정렬 (보통 랭킹은 높은 순이니까요)
				.sorted(Comparator.comparing(TagRank::getFinalScore).reversed())
				.toList();
    }
}
