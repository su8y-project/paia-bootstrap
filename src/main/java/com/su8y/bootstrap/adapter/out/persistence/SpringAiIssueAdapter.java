package com.su8y.bootstrap.adapter.out.persistence;

import com.su8y.bootstrap.application.port.out.IssueAnalysisPort;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SpringAiIssueAdapter implements IssueAnalysisPort {

	private final ChatClient chatClient;
	private final BeanOutputConverter<List<IssueAnalysis>> converter;

	public SpringAiIssueAdapter(ChatClient.Builder builder) {
		// 1. DTO 클래스를 기반으로 컨버터 생성
		this.converter = new BeanOutputConverter<>(new ParameterizedTypeReference<List<IssueAnalysis>>() {
		});

		// 2. ChatClient 설정 (시스템 프롬프트에 포맷 지시어 포함)
		this.chatClient = builder
				.defaultSystem("""
						당신은 미국 시장 및 거시 이슈를 구조화하여 추출하는
						금융·경제·정책 전문 뉴스 이슈 분석 AI입니다.
						
						당신의 역할은
						한국 기준 밤 시간(전일 장 마감 이후부터 당일 오전 사이) 동안 발생한
						미국의 주요 이슈를 식별하고,
						중복되지 않는 ‘이슈 단위’로 정리하는 것입니다.
						
						이슈는 반드시 다음 범주를 모두 포함해야 합니다.
						- 경제 (금리, 연준, 물가, 고용, 환율, 채권, 원자재 등)
						- 정치 및 정책 (미국 정부, 의회, 대외정책, 규제, 법안)
						- 기업 현황 (대형 기술주, 주요 기업 실적, M&A, IPO, 구조조정, 신제품)
						
						소셜 이슈, 연예, 문화, 루머성 뉴스는 제외합니다.
						반드시 공식 뉴스, 기업 공시, 정부 발표, 금융 데이터 등
						신뢰 가능한 출처에 기반한 이슈만 포함합니다.
						
						[이슈 추출 규칙]
						
						- 동일한 사건이나 원인에서 파생된 뉴스는 하나의 이슈로 병합합니다.
						- 단순 가격 변동만 있는 경우는 제외하고,
						  원인이 명확한 이벤트성 이슈만 포함합니다.
						- 한국 증시 영향 분석은 수행하지 않습니다.
						
						[출력 형식]
						
						출력은 반드시 이슈 배열 형태로 작성합니다.
						각 이슈는 아래 필드를 반드시 포함해야 합니다.
						
						- title \s
						  : 이슈를 한 줄로 요약한 제목
						
						- summary \s
						  : 핵심 사실 중심의 간결한 설명 (배경 + 현재 상황)
						
						- importanceScore \s
						  : 해당 이슈의 글로벌 화제성·파급력을 0~10 점으로 평가 \s
						    (보도 빈도, 시장 반응, 정책·산업 영향도를 종합 고려)
						
						- tags \s
						  : 관련 섹터, 산업, 자산군, 키워드를 배열로 나열 \s
						    (예: ["반도체", "금리", "AI", "빅테크", "에너지"])
						
						[작성 지침]
						- 객관적 사실 위주로 작성하고 과도한 전망은 배제합니다.
						- 각 summary는 불필요한 수식 없이 정보 밀도를 높게 유지합니다.
						- 이슈 수는 중요도 기준으로 선별하며, 중요하지 않은 단신은 포함하지 않습니다.
						- 각 요청사항이 잘 지켜졌는지 확인합니다.
						""")
				.build();
	}

	@Override
	public List<IssueAnalysis> analyzeIssue(String rawText) {
		return chatClient.prompt()
				.user(u -> u.text("""
								이슈를 생성해줘:
								
								{format}
								""")
						.param("format", converter.getFormat())
				)
				.call()
				.entity(converter);
	}
}
