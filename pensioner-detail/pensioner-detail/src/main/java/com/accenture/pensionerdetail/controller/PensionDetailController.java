package com.accenture.pensionerdetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.accenture.pensionerdetail.client.MicroserviceClient;
import com.accenture.pensionerdetail.exception.TokenException;
import com.accenture.pensionerdetail.model.PensionerDetail;
import com.accenture.pensionerdetail.service.PensionerDetailService;

import feign.FeignException;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/pensionerDetail")
public class PensionDetailController {

	@Autowired
	private MicroserviceClient client;
	@Autowired
	private PensionerDetailService service;

	@GetMapping("/PensionerDetailByAadhaar")
	public PensionerDetail pensionerDetailByAadhar(@RequestHeader("Authorization") String token,
			@RequestParam(name = "aadhar")  String Aadhar) {
		try {
			ResponseEntity<?> r = client.authorization(token);
			if (r.getStatusCode() == HttpStatus.OK) {
				PensionerDetail p = service.getDetailByAadhar(Aadhar);
				return p;
			} else {
				throw new TokenException("INVALID TOKEN");
			}
		} catch (FeignException e) {
			throw new TokenException("INVALID TOKEN");
		}

	}
}
