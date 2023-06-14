package com.caremoa.settlement.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caremoa.settlement.domain.dto.Payment8086Dto;
import com.caremoa.settlement.domain.model.Payment8086;
import com.caremoa.settlement.domain.repository.ContractRepository;
import com.caremoa.settlement.domain.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentRepository paymentRepository;
	private final ContractRepository contractRepository;
	
	@Transactional
	public Payment8086Dto createPayment(Payment8086Dto paymentDto) throws Exception {
        Payment8086 savedPayment = paymentRepository.save(paymentDto.toEntity());
        return savedPayment.toDto();
	}
	
    public List<Payment8086Dto> getPaymentsByContractId(Long id) {
        List<Payment8086> payments = contractRepository.findById(id).get().getPayments();
        return payments.stream().map(Payment8086::toDto).collect(Collectors.toList());
                
    }
}
