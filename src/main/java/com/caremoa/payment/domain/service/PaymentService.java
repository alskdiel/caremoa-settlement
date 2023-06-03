package com.caremoa.payment.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caremoa.payment.domain.model.Payment;
import com.caremoa.payment.domain.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentRepository paymentRepository;
	
	@Transactional
	public Payment createPayment(Payment payment) throws Exception {

        return paymentRepository.save(payment);
	}
}
