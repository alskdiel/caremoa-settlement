package com.caremoa.settlement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.caremoa.settlement.domain.dto.Settlement8086Dto;
import com.caremoa.settlement.domain.service.SettlementService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SettlementController {

    private final SettlementService settlementService;


    @GetMapping("/settlements")
    public List<Settlement8086Dto> getAllSettlements() {
    	log.debug("getAllSettlements()");
        return settlementService.getAllSettlements();
    }

    @GetMapping("/settlements/{id}")
    public Settlement8086Dto getSettlementById(@PathVariable("id") Long id) {
    	log.debug("getSettlementById()");
        return settlementService.getSettlementById(id);
    }
    /*
    @PostMapping("/settlements")
    public SettlementDto createSettlement(@RequestBody SettlementDto settlementDto) {
    	log.debug("createSettlement()");
        return settlementService.createSettlement(settlementDto);
    }
	*/
    @PutMapping("/settlements/{id}")
    public Settlement8086Dto updateSettlement(@PathVariable("id") Long id, @RequestBody Settlement8086Dto settlement8086Dto) {
    	log.debug("updateSettlement()");
        return settlementService.updateSettlement(id, settlement8086Dto);
    }

    @DeleteMapping("/settlements/{id}")
    public void deleteSettlement(@PathVariable("id") Long id) {
    	log.debug("deleteSettlement()");
        settlementService.deleteSettlement(id);
    }
    
    @GetMapping("/helpers/{id}/settlements")
    public List<Settlement8086Dto> getHelperettlements(@PathVariable("id") Long id) {
    	log.debug("getUserSettlements()");
        return settlementService.getHelperSettlements(id);
    }

}
