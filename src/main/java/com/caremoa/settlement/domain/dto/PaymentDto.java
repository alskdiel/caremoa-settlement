package com.caremoa.settlement.domain.dto;

import java.time.LocalDateTime;

import com.caremoa.settlement.domain.model.Payment;
import com.caremoa.settlement.domain.model.PaymentMethod;
import com.caremoa.settlement.domain.model.PaymentRequestState;
import com.caremoa.settlement.domain.model.PaymentType;

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
public class PaymentDto {

    private Long id;
    private ContractDto contract;
    private PaymentType paymentType;
    private PaymentMethod paymentMethod;
    private PaymentRequestState paymentRequestState; 
	private LocalDateTime requestDateTime;
	private Integer requestAmount;
	private LocalDateTime responseDateTime;
	private String approveNo;
	
	public Payment toEntity() {
		Payment payment = new Payment();
		payment.setId(this.id);
		payment.setContract(this.contract.toEntity());
		payment.setPaymentType(this.paymentType);
		payment.setPaymentMethod(this.paymentMethod);
		payment.setPaymentRequestState(this.paymentRequestState);
		payment.setRequestDateTime(this.requestDateTime);
		payment.setRequestAmount(this.requestAmount);
		payment.setResponseDateTime(this.responseDateTime);
		payment.setApproveNo(this.approveNo);
		return payment;
	}
	
}
