package com.accenture.processpension.Model;

public class PensionerInput {

	
	private String name;
	private String dateOfBirth;
	private String pan;
	private String aadhar;
	private String pensionType;
	public PensionerInput() {
		super();
	}
	public PensionerInput(String name, String dateOfBirth, String pan, String aadhar, String pensionType) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.pan = pan;
		this.aadhar = aadhar;
		this.pensionType = pensionType;
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
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public String getPensionType() {
		return pensionType;
	}
	public void setPensionType(String pensionType) {
		this.pensionType = pensionType;
	}
}
