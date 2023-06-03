
package com.caremoa.payment.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.caremoa.payment.domain.dto.SettlementDto;

@FeignClient(name = "payment", url = "http://localhost:8099/") // Feign Client 설정
public interface SettlementFeignClient {
	/*
	@GetMapping("/payments")
    Page<SettlementDto> getAll(Pageable pageable);
    
    @GetMapping("/payments/{id}")
    SettlementDto getById(@PathVariable("id") Long id);

    @PostMapping("/payments")
    SettlementDto postData(@RequestBody SettlementDto paymentDto);

    @PutMapping("/payments/{id}")
    SettlementDto putData(@RequestBody SettlementDto paymentDto, @PathVariable("id") Long id);

	@PatchMapping("/payments/{id}")
	SettlementDto patchData(@RequestBody SettlementDto paymentDto, @PathVariable("id") Long id);

    @DeleteMapping("/payments/{id}")
    void deleteData(@PathVariable("id") Long id);
    */
    @PostMapping("/external/approve-payment")
    SettlementDto postData(@RequestBody SettlementDto settlementDto);

    @PostMapping("/external/cancel-payment")
    SettlementDto deleteData(@RequestBody SettlementDto settlementDto);
}
