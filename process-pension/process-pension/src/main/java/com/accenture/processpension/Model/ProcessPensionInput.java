package com.accenture.processpension.Model;

public class ProcessPensionInput {

	
	private String aadhar;
	
	private double salary;

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

	public ProcessPensionInput(String aadhar, double salary) {
		super();
		this.aadhar = aadhar;
		this.salary = salary;
	}

	public ProcessPensionInput() {
		super();
	}
	
	
}
