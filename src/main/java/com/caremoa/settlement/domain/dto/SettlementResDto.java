package com.caremoa.settlement.domain.dto;

import java.time.LocalDateTime;

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
public class SettlementResDto {

    private Long id;
    private Long contractId;
    private String contractHelperName;
    private Contract8086Dto contract;
    private SettlementState settlementState;
    private Integer settledAmount;
	private LocalDateTime requestedDateTime;
	private LocalDateTime approvedDateTime;
	private LocalDateTime paidDateTime;
	private String memberId;
	

	public SettlementResDto(Contract8086Dto contractDto) {
		this.isetContract(contractDto);
	}
	
	public Contract8086Dto igetContract() {
		return new Contract8086Dto(this.contractId, this.contractHelperName);
	}

	public void isetContract(Contract8086Dto contractDto) {
		this.contractId = contractDto.getId();
		this.contractHelperName = contractDto.getHelperName();
	}

}