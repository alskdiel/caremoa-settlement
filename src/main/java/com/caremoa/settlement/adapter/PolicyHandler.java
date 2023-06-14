package com.caremoa.settlement.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.caremoa.settlement.domain.dto.Contract8086Dto;
import com.caremoa.settlement.domain.dto.Payment8086Dto;
import com.caremoa.settlement.domain.model.PaymentMethod;
import com.caremoa.settlement.domain.model.PaymentRequestState;
import com.caremoa.settlement.domain.model.PaymentType;
import com.caremoa.settlement.domain.service.ContractService;
import com.caremoa.settlement.domain.service.PaymentService;
import com.caremoa.settlement.domain.service.SettlementService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PolicyHandler {

	private final ContractService contractService; 
	private final PaymentService paymentService;  
	private final SettlementService settlementService; 

    @Bean
    Consumer<Map<String, Object>> basicConsumer() {
		return mapData -> {
			ObjectMapper mapper = new ObjectMapper();
			log.debug(mapData.toString());
			
			switch (mapData.get("eventType").toString()) {
				case "ContractCompleted":
					ContractCompleted contractCompleted = mapper.convertValue(mapData, ContractCompleted.class);
					log.debug("contractCompleted : {}", contractCompleted.toString());
	    			createSettlement(contractCompleted.getContractId());
					break;
				case "PaymentCompleted":
					PaymentCompleted paymentCompleted = mapper.convertValue(mapData, PaymentCompleted.class);
					log.debug("paymentCompleted : {}", paymentCompleted.toString());
					createContract(paymentCompleted.getContract());
	    			createPayment(paymentCompleted.getId(), paymentCompleted.getContract(), paymentCompleted.getPaymentType(),
	    					paymentCompleted.getPaymentMethod(), paymentCompleted.getPaymentRequestState(),
	    					paymentCompleted.getRequestAmount(), paymentCompleted.getApproveNo());
					break;
				default: // 처리가 정의되지 않은 이벤트
					log.debug("Undefined EventType : {}", mapData.get("eventType").toString());
					break;
			}
		};
	}

    
    /**
     * @methodName    : ReflectionScore
     * @date          : 2023.05.19
     * @description   : 점수반영
     * @param paymentId
    */
    private void createSettlement(Long id) {
    	try {
    		log.debug("ReflectionScore {}, {}, {}", id);
    		List<Payment8086Dto> paymentsDto = paymentService.getPaymentsByContractId(id);
    		settlementService.createSettlement(paymentsDto);
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    /**
     * @methodName    : ReflectionScore
     * @date          : 2023.05.19
     * @description   : 점수반영
     * @param paymentId
    */
    private void createContract(Contract8086Dto contractDto) {
    	try {
    		log.debug("ReflectionScore {}, {}, {}", contractDto);
    		contractService.createContract(contractDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * @methodName    : ReflectionScore
     * @date          : 2023.05.19
     * @description   : 점수반영
     * @param paymentId
    */
    private void createPayment(Long id, Contract8086Dto contract, PaymentType paymentType, PaymentMethod paymentMethod, PaymentRequestState paymentRequestState,
    		Integer requestAmount, String approveNo) {
    	try {
    		log.debug("ReflectionScore {}, {}, {}", id, contract, paymentType, paymentMethod, paymentRequestState, requestAmount, approveNo);
    		Payment8086Dto paymentDto = new Payment8086Dto(id, contract, paymentType, paymentMethod, paymentRequestState, requestAmount, approveNo);
    		paymentService.createPayment(paymentDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
}
