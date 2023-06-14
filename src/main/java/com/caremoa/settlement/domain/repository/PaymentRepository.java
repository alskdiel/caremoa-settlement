package com.caremoa.settlement.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caremoa.settlement.domain.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}