package com.caremoa.settlement.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caremoa.settlement.domain.model.Payment8086;

@Repository
public interface PaymentRepository extends JpaRepository<Payment8086, Long> {
}