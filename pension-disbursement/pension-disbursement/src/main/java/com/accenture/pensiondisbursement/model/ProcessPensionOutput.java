package com.accenture.pensiondisbursement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProcessPensionOutput {

	

	@Id
	private String aadhar;
	
	private double salary;
	
	private String status;

	public ProcessPensionOutput() {
		super();
	}

	public ProcessPensionOutput(String aadhar, double salary, String status) {
		super();
		this.aadhar = aadhar;
		this.salary = salary;
		this.status = status;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
