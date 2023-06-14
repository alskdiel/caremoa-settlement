package com.caremoa.settlement.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caremoa.settlement.domain.dto.Contract8086Dto;
import com.caremoa.settlement.domain.model.Contract8086;
import com.caremoa.settlement.domain.repository.ContractRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {
	private final ContractRepository contractRepository;
	
	@Transactional
	public Contract8086Dto createContract(Contract8086Dto contractDto) throws Exception {
        Contract8086 savedContract = contractRepository.save(contractDto.toEntity());
        return savedContract.toDto();
	}
	
}
