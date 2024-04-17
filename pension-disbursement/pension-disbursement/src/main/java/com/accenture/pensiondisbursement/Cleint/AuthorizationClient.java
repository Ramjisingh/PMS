package com.accenture.pensiondisbursement.Cleint;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name ="authorizationService")
public interface AuthorizationClient{

	   @GetMapping("/security/authorize")
	  public  ResponseEntity<?> authorization(@RequestHeader("Authorization") String token);
	

}