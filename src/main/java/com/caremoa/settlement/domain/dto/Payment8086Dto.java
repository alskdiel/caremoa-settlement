package com.caremoa.settlement.domain.dto;

import java.time.LocalDateTime;

import com.caremoa.settlement.domain.model.Payment8086;
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
public class Payment8086Dto {

    private Long id;
    private Contract8086Dto contract;
    private PaymentType paymentType;
    private PaymentMethod paymentMethod;
    private PaymentRequestState paymentRequestState; 
	private Integer requestAmount;
	private String approveNo;
	
	public Payment8086 toEntity() {
		Payment8086 payment = new Payment8086();
		payment.setId(this.id);
		payment.setContract(this.contract.toEntity());
		payment.setPaymentType(this.paymentType);
		payment.setPaymentMethod(this.paymentMethod);
		payment.setPaymentRequestState(this.paymentRequestState);
		payment.setRequestAmount(this.requestAmount);
		payment.setApproveNo(this.approveNo);
		return payment;
	}
	
}
