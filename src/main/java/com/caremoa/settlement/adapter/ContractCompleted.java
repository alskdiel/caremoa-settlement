package com.caremoa.settlement.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ContractCompleted extends AbstractEvent{
	private Long contractId;

	@Builder
	public ContractCompleted(Long contractId) {
		super();
		this.contractId = contractId; 
	}
}
