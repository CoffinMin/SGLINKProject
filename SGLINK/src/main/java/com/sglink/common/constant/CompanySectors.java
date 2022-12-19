package com.sglink.common.constant;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanySectors {
	private String code;
	private String displayName;
	
	
	public List<CompanySectors> deliveryCodes() {
		List<CompanySectors> deliveryCodes = new ArrayList<>();
		deliveryCodes.add(new CompanySectors("A", "농업, 임업 및 어업"));
		deliveryCodes.add(new CompanySectors("B", "광업"));
		deliveryCodes.add(new CompanySectors("C", "제조업"));
		deliveryCodes.add(new CompanySectors("D", "전기, 가스, 증기 및 공기 조절 공급업"));
		deliveryCodes.add(new CompanySectors("E", "수도, 하수 및 폐기물 처리, 원료 재생업"));
		deliveryCodes.add(new CompanySectors("F", "건 설 업"));
		deliveryCodes.add(new CompanySectors("G", "도매 및 소매업"));
		deliveryCodes.add(new CompanySectors("H", "운수 및 창고업"));
		deliveryCodes.add(new CompanySectors("I", "숙박 및 음식점업"));
		deliveryCodes.add(new CompanySectors("J", "정보통신업"));
		deliveryCodes.add(new CompanySectors("K", "금융 및 보험업"));
		deliveryCodes.add(new CompanySectors("L", "부동산업"));
		deliveryCodes.add(new CompanySectors("M", "전문, 과학 및 기술 서비스업"));
		deliveryCodes.add(new CompanySectors("N", "사업시설 관리, 사업 지원 및 임대 서비스업"));
		deliveryCodes.add(new CompanySectors("O", "공공 행정, 국방 및 사회보장 행정"));
		deliveryCodes.add(new CompanySectors("P", "교육 서비스업"));
		deliveryCodes.add(new CompanySectors("Q", "보건업 및 사회복지 서비스업"));
		deliveryCodes.add(new CompanySectors("R", "예술, 스포츠 및 여가관련 서비스업"));
		deliveryCodes.add(new CompanySectors("S", "협회 및 단체, 수리 및 기타 개인 서비스업"));
		deliveryCodes.add(new CompanySectors("T", "가구 내 고용활동 및 달리 분류되지 않은 자가 소비 생산활동"));
		deliveryCodes.add(new CompanySectors("U", "국제 및 외국기관"));
		return deliveryCodes;
	}
}
