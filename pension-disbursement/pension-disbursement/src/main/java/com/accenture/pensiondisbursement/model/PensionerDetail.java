package com.accenture.pensiondisbursement.model;

public class PensionerDetail {
	private String aadhaarNumber;						// Entering the Aadhaar Number  of Pensioner
	private String name;								// Entering the name of Pensioner
	private String dateOfBirth;							// Entering the DOB of Pensioner
	private String panNumber;							// Entering the pan number of Pensioner
	private double salary;								// Entering the Salary of Pensioner
	private double allowance;							// Entering the Allowance of Pensioner
	private String pensionType;							// Entering the Pension Type of Pensioner
	private String bankName;					// Bank Name of the Pensioner Eg. SBI, HDFC etc.
	private String accountNumber;				// Account Number of the Pensioner
	private String bankType;

	public PensionerDetail() {
		super();
	}
	public PensionerDetail(String aadhaarNumber, String name, String dateOfBirth, String panNumber, double salary,
			double allowance, String pensionType, String bankName, String accountNumber, String bankType) {
		super();
		this.aadhaarNumber = aadhaarNumber;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.panNumber = panNumber;
		this.salary = salary;
		this.allowance = allowance;
		this.pensionType = pensionType;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.bankType = bankType;
	}
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getAllowance() {
		return allowance;
	}
	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}
	public String getPensionType() {
		return pensionType;
	}
	public void setPensionType(String pensionType) {
		this.pensionType = pensionType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}


}