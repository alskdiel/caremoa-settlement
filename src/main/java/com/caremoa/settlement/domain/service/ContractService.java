package com.caremoa.settlement.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caremoa.settlement.domain.dto.ContractDto;
import com.caremoa.settlement.domain.model.Contract;
import com.caremoa.settlement.domain.repository.ContractRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {
	private final ContractRepository contractRepository;
	
	@Transactional
	public ContractDto createContract(ContractDto contractDto) throws Exception {
        Contract savedContract = contractRepository.save(contractDto.toEntity());
        return savedContract.toDto();
	}
	
}
