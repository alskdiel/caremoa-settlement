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
	private Long memberId;
	private String memberName;
	private Long helperId;
	private String helperName;
	private String helperJobType;
	private String targetName;
	private String careRange;
	private String contractStatus;
	private String timestamp;
	
	@Builder
	public ContractCompleted(Long contractId) {
		super();
		this.contractId = contractId; 
	}
}
