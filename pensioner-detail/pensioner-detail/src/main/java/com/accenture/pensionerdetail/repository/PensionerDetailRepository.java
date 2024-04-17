package com.accenture.pensionerdetail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.pensionerdetail.model.PensionerDetail;

@Repository
public interface PensionerDetailRepository extends JpaRepository<PensionerDetail, String>{

	
}
