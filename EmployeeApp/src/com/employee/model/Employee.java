package com.employee.model;

public class Employee {

	private String firstName = null;
	private String lastName = null;
	private String dateOfBirth = null;
	private String dateOfJoining = null;

	public Employee() {
	}

	public Employee(String firstName, String lastName, String dateOfBirth, String dateOfJoining) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}
	

}
