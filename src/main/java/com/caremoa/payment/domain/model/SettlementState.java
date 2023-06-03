package com.caremoa.payment.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SettlementState {

    REQUESTED("00", "요청"), APPROVED("10", "승인"), PAID("20", "지급완료");
	
	private final String key;
	private final String title;
}
