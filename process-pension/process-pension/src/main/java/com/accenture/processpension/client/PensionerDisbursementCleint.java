package com.accenture.processpension.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.accenture.processpension.Model.ProcessPensionInput;


@FeignClient(name ="pension-disbursement")
public interface PensionerDisbursementCleint {

	@PostMapping("/disburse/DisbursePension")
	public int disbursePension(@RequestHeader("Authorization") String token, @RequestBody ProcessPensionInput input);
		
}
	
