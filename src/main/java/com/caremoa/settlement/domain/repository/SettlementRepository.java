package com.caremoa.settlement.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caremoa.settlement.domain.model.Settlement8086;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement8086, Long> {
}