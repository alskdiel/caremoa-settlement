package com.caremoa.settlement.domain.dto;

import com.caremoa.settlement.domain.model.Contract8086;

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
public class Contract8086Dto {

    private Long id;
    private Long memberId;
    private Long helperId;
    private String helperName;

	@Builder
	public Contract8086Dto(Long id, String helperName) {
		super();
		this.id = id; 
		this.helperName = helperName; 
		
	}

	public Contract8086 toEntity() {
		Contract8086 contract = new Contract8086();
		contract.setId(this.id);
		contract.setMemberId(this.memberId);
		contract.setHelperId(this.helperId);
		contract.setHelperName(this.helperName);
		return contract;
	}
}
