package com.accenture.processpension.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.processpension.Model.PensionerDetail;
import com.accenture.processpension.Model.PensionerInput;
import com.accenture.processpension.Model.ProcessPensionInput;
import com.accenture.processpension.client.AuthorizationClient;
import com.accenture.processpension.client.PensionerDetailCleint;
import com.accenture.processpension.client.PensionerDisbursementCleint;
import com.accenture.processpension.exception.TokenException;
import com.accenture.processpension.exception.WrongUserException;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/processPension")
public class ProcessPensionController {

	@Autowired
	AuthorizationClient authClient;
	@Autowired
	PensionerDetailCleint pensionerDetailCleint;
	@Autowired
	PensionerDisbursementCleint pensionerDisbursementCleint;

	@GetMapping("/PensionDetail")
//	@CircuitBreaker(name = "pensionDetailBreaker", fallbackMethod = "fallBack")
//	@Retry(name = "pensioner-detail", fallbackMethod = "fallBack")
//	@RateLimiter(name = "userRateLimiter", fallbackMethod = "fallBack")
//	@HystrixCommand(fallbackMethod = "fallBack")
	public PensionerDetail processPension(@RequestHeader("Authorization") String token,
			@RequestBody PensionerInput input) {
		ResponseEntity<?> r = null;
		try {
			r = authClient.authorization(token);
		} catch (Exception e) {
			throw new TokenException("TOKEN IS NOT CORRECT");
		}

		if (r.getStatusCode() == HttpStatus.OK) {
			PensionerDetail pensionerDetail = pensionerDetailCleint.pensionerDetailByAadhar(token, input.getAadhar());
			if (pensionerDetail.getAadhaarNumber().equals(input.getAadhar())
					&& pensionerDetail.getName().equals(input.getName())
					&& pensionerDetail.getDateOfBirth().equals(input.getDateOfBirth())
					&& pensionerDetail.getPensionType().equals(input.getPensionType())
					&& pensionerDetail.getPanNumber().equals(input.getPan())) {
				double salary = 0;
				double bankCharge = 0;
				double percentageOfSalaryToAdd = pensionerDetail.getPensionType().equals("self") ? 0.8 : 0.5;
				bankCharge = (pensionerDetail.getBankType().equals("public")) ? 500 : 550;
				salary = (percentageOfSalaryToAdd * pensionerDetail.getSalary()) + pensionerDetail.getAllowance()
						+ bankCharge;
				pensionerDetail.setSalary(salary);
				System.out.println(pensionerDetail.getAadhaarNumber());
				return pensionerDetail;
			} else {
				throw new WrongUserException("USER INPUT IS WRONG");
			}
		} else {
			throw new TokenException("TOKEN IS NOT CORRECT");
		}

	}

//	public String fallBack(){
//		return "PENSIONER DETAIL MICROSERVICE IS NOT WORKING ";
//	
//		
//	}
	public PensionerDetail fallBack(@RequestHeader("Authorization") String token,
			@RequestBody PensionerInput input, Exception ex) {
//      logger.info("Fallback is executed because service is down : ", ex.getMessage());

//		ex.printStackTrace();
//
//		User user = User.builder().email("dummy@gmail.com").name("Dummy")
//				.about("This user is created dummy because some service is down").userId("141234").build();
//		return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		return new PensionerDetail();
	}

	@PostMapping("/ProcessPension")
	public int disbursePension(@RequestHeader("Authorization") String token, @RequestBody ProcessPensionInput input) {

		try {
			ResponseEntity<?> r = authClient.authorization(token);
			if (r.getStatusCode() == HttpStatus.OK) {
				return pensionerDisbursementCleint.disbursePension(token, input);
			} else {
				throw new TokenException("TOKEN IS NOT CORRECT");
			}
		} catch (Exception e) {
			throw new WrongUserException("USER INPUT IS WRONG OR TOKEN IS WRONG");
		}

	}

}
