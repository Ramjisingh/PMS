package com.accenture.pensiondisbursement.Cleint;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.pensiondisbursement.model.PensionerDetail;


@FeignClient(name ="pensioner-detail")
public interface PensionerDetailCleint {

		@GetMapping("/pensionerDetail/PensionerDetailByAadhaar")
		public PensionerDetail pensionerDetailByAadhar(@RequestHeader("Authorization") String token, @RequestParam(name = "aadhar") String  Aadhar);
			
}
	
