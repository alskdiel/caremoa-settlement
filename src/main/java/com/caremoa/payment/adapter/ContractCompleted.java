package com.caremoa.payment.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ContractCompleted extends AbstractEvent{
	private Long contractId;
	private Long memberId;
	private Long helperId;

	@Builder
	public ContractCompleted(Long contractId, Long memberId, Long helperId) {
		super();
		this.contractId = contractId; 
		this.memberId = memberId;
		this.helperId = helperId;
	}
}
