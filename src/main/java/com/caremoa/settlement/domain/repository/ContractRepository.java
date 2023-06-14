package com.caremoa.settlement.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caremoa.settlement.domain.model.Contract8086;

@Repository
public interface ContractRepository extends JpaRepository<Contract8086, Long> {
	List<Contract8086> findByHelperId(Long helperId);
}