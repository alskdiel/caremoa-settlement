package com.caremoa.settlement.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caremoa.settlement.domain.dto.PaymentDto;
import com.caremoa.settlement.domain.model.Contract;
import com.caremoa.settlement.domain.model.Payment;
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
	public PaymentDto createPayment(PaymentDto paymentDto) throws Exception {
        Payment savedPayment = paymentRepository.save(paymentDto.toEntity());
        return savedPayment.toDto();
	}
	
    public List<PaymentDto> getPaymentsByContractId(Long id) {
        List<Payment> payments = contractRepository.findById(id).get().getPayments();
        return payments.stream().map(Payment::toDto).collect(Collectors.toList());
                
    }
}
