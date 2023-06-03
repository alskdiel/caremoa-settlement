package com.caremoa.payment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.caremoa.payment.domain.dto.SettlementDto;
import com.caremoa.payment.domain.service.SettlementService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SettlementController {

    private final SettlementService settlementService;


    @GetMapping("/settlements")
    public List<SettlementDto> getAllSettlements() {
    	log.debug("getAllSettlements()");
        return settlementService.getAllSettlements();
    }

    @GetMapping("/settlements/{id}")
    public SettlementDto getSettlementById(@PathVariable("id") Long id) {
    	log.debug("getSettlementById()");
        return settlementService.getSettlementById(id);
    }

    @PostMapping("/settlements")
    public SettlementDto createSettlement(@RequestBody SettlementDto settlementDto) {
    	log.debug("createSettlement()");
        return settlementService.createSettlement(settlementDto);
    }

    @PutMapping("/settlements/{id}")
    public SettlementDto updateSettlement(@PathVariable("id") Long id, @RequestBody SettlementDto settlementDto) {
    	log.debug("updateSettlement()");
        return settlementService.updateSettlement(id, settlementDto);
    }

    @DeleteMapping("/settlements/{id}")
    public void deleteSettlement(@PathVariable("id") Long id) {
    	log.debug("deleteSettlement()");
        settlementService.deleteSettlement(id);
    }
    
    @GetMapping("/helpers/{id}/settlements")
    public List<SettlementDto> getHelperettlements(@PathVariable("id") Long id) {
    	log.debug("getUserSettlements()");
        return settlementService.getHelperSettlements(id);
    }

}
