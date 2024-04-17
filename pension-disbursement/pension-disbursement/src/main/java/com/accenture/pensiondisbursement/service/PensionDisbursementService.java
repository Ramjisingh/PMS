package com.accenture.pensiondisbursement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.pensiondisbursement.model.ProcessPensionOutput;
import com.accenture.pensiondisbursement.repository.PensionDisbursementRepository;

@Service
public class PensionDisbursementService {

	@Autowired
	PensionDisbursementRepository repo;
	
	
	public ProcessPensionOutput save(ProcessPensionOutput output) {
		return repo.save(output);
	}
}
