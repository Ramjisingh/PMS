package com.accenture.processpension.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.processpension.Model.PensionerDetail;



@FeignClient(name ="pensioner-detail") //fallback = MyFeignClientFallback.class)
public interface PensionerDetailCleint {

		@GetMapping("/pensionerDetail/PensionerDetailByAadhaar")
//		@HystrixCommand(fallbackMethod = "fallback")
//	    @CircuitBreaker(name = "pensionerDetailCircuitBreaker", fallbackMethod = "fallback")
		public PensionerDetail pensionerDetailByAadhar(@RequestHeader("Authorization") String token, @RequestParam(name = "aadhar") String  Aadhar);
			
//	    default PensionerDetail fallback(String token, String Aadhar, Throwable throwable) {
//	        // Provide fallback response or behavior
//	        return new PensionerDetail();
//	    }

}
	
