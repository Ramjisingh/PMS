package com.accenture.pensiondisbursement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.pensiondisbursement.model.ProcessPensionOutput;

@Repository
public interface PensionDisbursementRepository extends JpaRepository<ProcessPensionOutput, String>{

}
