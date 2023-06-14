package com.caremoa.settlement.domain.dto;

import java.time.LocalDateTime;

import com.caremoa.settlement.domain.model.Settlement;
import com.caremoa.settlement.domain.model.SettlementState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SettlementDto {

    private Long id;
    private ContractDto contract;
    private SettlementState settlementState;
    private Integer settledAmount;
	private LocalDateTime requestedDateTime;
	private LocalDateTime approvedDateTime;
	private LocalDateTime paidDateTime;
	private String memberId;
	
	public Settlement toEntity() {
		Settlement settlement = new Settlement();
		settlement.setId(this.id);
		settlement.setContract(this.contract.toEntity());
		settlement.setSettlementState(this.settlementState);
		settlement.setSettledAmount(this.settledAmount);
		settlement.setRequestedDateTime(this.requestedDateTime);
		settlement.setApprovedDateTime(this.approvedDateTime);
		settlement.setPaidDateTime(this.paidDateTime);
		settlement.setMemberId(this.memberId);
		return settlement;
	}
	
}
