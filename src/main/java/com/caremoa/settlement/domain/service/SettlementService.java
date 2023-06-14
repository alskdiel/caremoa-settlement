package com.caremoa.settlement.domain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.caremoa.settlement.domain.dto.ContractDto;
import com.caremoa.settlement.domain.dto.PaymentDto;
import com.caremoa.settlement.domain.dto.SettlementDto;
import com.caremoa.settlement.domain.model.Contract;
import com.caremoa.settlement.domain.model.PaymentRequestState;
import com.caremoa.settlement.domain.model.PaymentType;
import com.caremoa.settlement.domain.model.Settlement;
import com.caremoa.settlement.domain.model.SettlementState;
import com.caremoa.settlement.domain.repository.ContractRepository;
import com.caremoa.settlement.domain.repository.SettlementRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettlementService {
	
	private final SettlementRepository settlementRepository;
	private final ContractRepository contractRepository;

    public List<SettlementDto> getAllSettlements() {
        List<Settlement> settlements = settlementRepository.findAll();
        return settlements.stream().map(Settlement::toDto).collect(Collectors.toList());
    }

    public SettlementDto getSettlementById(Long id) {
        Settlement settlement = settlementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Settlement not found with id: " + id));
        return settlement.toDto();
    }
    
    public SettlementDto createSettlement(List<PaymentDto> paymentsDto) {
    	SettlementDto settlementDto = new SettlementDto();
    	
    	ContractDto contractDto = paymentsDto.get(0).getContract();
		SettlementState settlementState = SettlementState.CREATED;
		Integer settledAmount = 0;
		
    	for(PaymentDto paymentDto : paymentsDto) {
    		PaymentType pType = paymentDto.getPaymentType();
    		if(paymentDto.getPaymentRequestState() == PaymentRequestState.APPROVED) {
    			if(pType == PaymentType.NORMAL) {
    				settledAmount += paymentDto.getRequestAmount();
    			} else if(pType == PaymentType.CANCEL) {
    				settledAmount += (-1) * paymentDto.getRequestAmount();
    			}
    		}
    	}
    	
    	settlementDto.setContract(contractDto);
    	settlementDto.setSettlementState(settlementState);
    	settlementDto.setSettledAmount(settledAmount);
    	
    	Settlement savedSettlement = settlementRepository.save(settlementDto.toEntity());
    	return savedSettlement.toDto();
    }
    
/*
    public SettlementDto createSettlement(SettlementDto settlementDto) {
    	settlementDto.setSettlementState(SettlementState.REQUESTED);
        settlementDto.setRequestedDateTime(LocalDateTime.now());
        Settlement savedSettlement = settlementRepository.save(settlementDto.toEntity());
        return savedSettlement.toDto();
    }
*/
    public SettlementDto updateSettlement(Long id, SettlementDto settlementDto) {
        Settlement existingSettlement = settlementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Settlement not found with id: " + id));
        existingSettlement.setContract(settlementDto.getContract().toEntity());
        
        SettlementState settlementState = settlementDto.getSettlementState();
        SettlementState nextSettlementState = SettlementState.REQUESTED;
        switch(settlementState) {
        	case REQUESTED :
        		nextSettlementState = SettlementState.APPROVED;
                settlementDto.setApprovedDateTime(LocalDateTime.now());
                existingSettlement.setMemberId(settlementDto.getMemberId());
        		break;
        	case APPROVED :
        		nextSettlementState = SettlementState.PAID;
                settlementDto.setPaidDateTime(LocalDateTime.now());
        		break;
        }
        existingSettlement.setSettlementState(nextSettlementState);
        Settlement updatedSettlement = settlementRepository.save(existingSettlement);
        return updatedSettlement.toDto();
    }

    public void deleteSettlement(Long id) {
        settlementRepository.deleteById(id);
    }

    public List<SettlementDto> getHelperSettlements(Long id) {
        List<Settlement> settlements = new ArrayList<Settlement>();
        List<Contract> contract = contractRepository.findByHelperId(id);
        for(Contract c : contract) {
        	List<Settlement> cSettlements = c.getSettlements();
        	for(Settlement p : cSettlements) {
        		settlements.add(p);
        	}
        }
        return settlements.stream().map(Settlement::toDto).collect(Collectors.toList());
                
    }

}
