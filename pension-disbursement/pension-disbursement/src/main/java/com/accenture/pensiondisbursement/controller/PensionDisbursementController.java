package com.accenture.pensiondisbursement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.pensiondisbursement.Cleint.AuthorizationClient;
import com.accenture.pensiondisbursement.Cleint.PensionerDetailCleint;
import com.accenture.pensiondisbursement.exception.TokenException;
import com.accenture.pensiondisbursement.exception.UserNotFound;
import com.accenture.pensiondisbursement.model.PensionerDetail;
import com.accenture.pensiondisbursement.model.ProcessPensionInput;
import com.accenture.pensiondisbursement.model.ProcessPensionOutput;
import com.accenture.pensiondisbursement.service.PensionDisbursementService;
//import com.accenture.pensionerdetail.exception.TokenException;

import feign.FeignException;

@RestController
@RequestMapping("/disburse")
public class PensionDisbursementController {

	@Autowired
	AuthorizationClient client;
	@Autowired
	PensionerDetailCleint pensionerDetailCleint;

	@Autowired
	PensionDisbursementService service;

	@PostMapping("/DisbursePension")
	public int disbursePension(@RequestHeader("Authorization") String token, @RequestBody ProcessPensionInput input) {

		try {
			ResponseEntity<?> r = client.authorization(token);
//			if (r.getStatusCode() == HttpStatus.OK) {
				PensionerDetail pensionerDetail = pensionerDetailCleint.pensionerDetailByAadhar(token,
						input.getAadhar());
				double salary = 0;
				double bankCharge = 0;
				double percentageOfSalaryToAdd = pensionerDetail.getPensionType().equals("self") ? 0.8 : 0.5;
				bankCharge = (pensionerDetail.getBankType().equals("public")) ? 500 : 550;
				salary = (percentageOfSalaryToAdd * pensionerDetail.getSalary()) + pensionerDetail.getAllowance()
						+ bankCharge;
				pensionerDetail.setSalary(salary);
				if (input.getSalary() == pensionerDetail.getSalary()) {
					ProcessPensionOutput p = new ProcessPensionOutput(input.getAadhar(), input.getSalary(),
							"Salary disbursed");
					service.save(p);
					return 10;
				} else {
					return 21;
				}
//			} else {
//				throw new TokenException("INVALID TOKEN");
//			}
		} catch (Exception e) {
			throw new UserNotFound("INVALID TOKEN OR INVALID AADHAR NUMBER: PLEASE CHECK TOKEN OR AADHAR NUMBER");
		}


	
	}
}
