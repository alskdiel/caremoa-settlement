package com.caremoa.payment.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.caremoa.payment.domain.dto.SettlementDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@Builder
public class Settlement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;
	
    private SettlementState settlementState;
    private Integer settledAmount;
	private LocalDateTime requestedDateTime;
	private LocalDateTime approvedDateTime;
	private LocalDateTime paidDateTime;
	private String memberId;

	public SettlementDto toDto() {
		SettlementDto settlementDto = new SettlementDto();
		settlementDto.setId(this.id);
		settlementDto.setContract(this.contract.toDto());
		settlementDto.setSettlementState(this.settlementState);
		settlementDto.setSettledAmount(this.settledAmount);
		settlementDto.setRequestedDateTime(this.requestedDateTime);
		settlementDto.setApprovedDateTime(this.approvedDateTime);
		settlementDto.setPaidDateTime(this.paidDateTime);
		settlementDto.setMemberId(this.memberId);
		return settlementDto;
	}
}
