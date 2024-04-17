package com.accenture.pensionerdetail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.accenture.pensionerdetail.exception.UserNotFound;
import com.accenture.pensionerdetail.model.PensionerDetail;
import com.accenture.pensionerdetail.repository.PensionerDetailRepository;

@Service
public class PensionerDetailService {

	@Autowired
	private PensionerDetailRepository repo;
	
	   @Cacheable(value = "Aadhar", key = "#aadhar")
	public PensionerDetail getDetailByAadhar(String aadhar) {
		try {
		    return repo.findById(aadhar).get();
		}catch (Exception e) {
			// TODO: handle exception
			throw new UserNotFound("User with this aadhar card not exist");
			
		}
		
	}
}
