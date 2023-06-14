package com.caremoa.settlement.adapter;

import java.time.LocalDateTime;

import com.caremoa.settlement.domain.dto.Contract8086Dto;
import com.caremoa.settlement.domain.model.PaymentMethod;
import com.caremoa.settlement.domain.model.PaymentRequestState;
import com.caremoa.settlement.domain.model.PaymentType;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PaymentCompleted extends AbstractEvent{
    private Long id;
    private Contract8086Dto contract;
    private PaymentType paymentType;
    private PaymentMethod paymentMethod;
    private PaymentRequestState paymentRequestState; 
	private LocalDateTime requestDateTime;
	private Integer requestAmount;
	private LocalDateTime responseDateTime;
	private String approveNo;
	
	@Builder
	public PaymentCompleted(Long id, Contract8086Dto contract, PaymentType paymentType, PaymentMethod paymentMethod, PaymentRequestState paymentRequestState,
			LocalDateTime requestDateTime, Integer requestAmount, LocalDateTime responseDateTime, String approveNo) {
		super();
		this.id = id;
		this.contract = contract;
		this.paymentType = paymentType;
		this.paymentMethod = paymentMethod;
		this.paymentRequestState = paymentRequestState;
		this.requestDateTime = requestDateTime;
		this.requestAmount = requestAmount;
		this.responseDateTime = responseDateTime;
		this.approveNo = approveNo;
	}
}
