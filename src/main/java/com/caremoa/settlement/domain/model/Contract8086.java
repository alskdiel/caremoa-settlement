package com.caremoa.settlement.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.caremoa.settlement.domain.dto.Contract8086Dto;

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
public class Contract8086 {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long memberId;
	private Long helperId;
	private String helperName;

	@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment8086> payments = new ArrayList<>();

	@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Settlement8086> settlements = new ArrayList<>();

	public Contract8086(Long id, Long memberId, Long helperId) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.helperId = helperId;
	}

	public Contract8086Dto toDto() {
		Contract8086Dto contractDto = new Contract8086Dto();
		contractDto.setId(this.id);
		contractDto.setMemberId(this.memberId);
		contractDto.setHelperId(this.helperId);
		contractDto.setHelperName(this.helperName);
		return contractDto;
	}
}
