package com.caremoa.payment.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caremoa.payment.domain.model.Contract;
import com.caremoa.payment.domain.repository.ContractRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @packageName    : com.caremoa.member.domain.service
* @fileName       : SettlementService.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : CareMoa Settlement Service
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
/**
* @packageName    : com.caremoa.member.domain.service
* @fileName       : SettlementService.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
/**
* @packageName    : com.caremoa.member.domain.service
* @fileName       : SettlementService.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {
	private final ContractRepository contractRepository;
	
	@Transactional
	public Contract createContract(Contract contract) throws Exception {

        return contractRepository.save(contract);
        /*
		return repository.findById(id) //
				.map(oldData -> {
					oldData.setUserScore(newData.getUserScore() + oldData.getUserScore());
					return repository.save(oldData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Settlement id=[%d] Not Found", id));
				});
				*/
	}
}
