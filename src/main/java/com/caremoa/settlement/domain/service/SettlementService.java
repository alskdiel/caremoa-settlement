package com.caremoa.settlement.domain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.caremoa.settlement.domain.dto.Contract8086Dto;
import com.caremoa.settlement.domain.dto.Payment8086Dto;
import com.caremoa.settlement.domain.dto.Settlement8086Dto;
import com.caremoa.settlement.domain.model.Contract8086;
import com.caremoa.settlement.domain.model.PaymentRequestState;
import com.caremoa.settlement.domain.model.PaymentType;
import com.caremoa.settlement.domain.model.Settlement8086;
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

    public List<Settlement8086Dto> getAllSettlements() {
        List<Settlement8086> settlements = settlementRepository.findAll();
        return settlements.stream().map(Settlement8086::toDto).collect(Collectors.toList());
    }

    public Settlement8086Dto getSettlementById(Long id) {
        Settlement8086 settlement = settlementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Settlement not found with id: " + id));
        return settlement.toDto();
    }
    
    public Settlement8086Dto createSettlement(List<Payment8086Dto> paymentsDto) {
    	Settlement8086Dto settlementDto = new Settlement8086Dto();
    	
    	Contract8086Dto contractDto = paymentsDto.get(0).getContract();
		SettlementState settlementState = SettlementState.CREATED;
		Integer settledAmount = 0;
		
    	for(Payment8086Dto paymentDto : paymentsDto) {
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
    	
    	Settlement8086 savedSettlement = settlementRepository.save(settlementDto.toEntity());
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
    public Settlement8086Dto updateSettlement(Long id, Settlement8086Dto settlementDto) {
        Settlement8086 existingSettlement = settlementRepository.findById(id)
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
        Settlement8086 updatedSettlement = settlementRepository.save(existingSettlement);
        return updatedSettlement.toDto();
    }

    public void deleteSettlement(Long id) {
        settlementRepository.deleteById(id);
    }

    public List<Settlement8086Dto> getHelperSettlements(Long id) {
        List<Settlement8086> settlements = new ArrayList<Settlement8086>();
        List<Contract8086> contract = contractRepository.findByHelperId(id);
        for(Contract8086 c : contract) {
        	List<Settlement8086> cSettlements = c.getSettlements();
        	for(Settlement8086 p : cSettlements) {
        		settlements.add(p);
        	}
        }
        return settlements.stream().map(Settlement8086::toDto).collect(Collectors.toList());
                
    }

}
