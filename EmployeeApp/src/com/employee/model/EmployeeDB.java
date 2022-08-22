package com.employee.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeDB {

	private static EmployeeDB empDb;

	private ObservableList<Employee> empList = FXCollections.observableList(new ArrayList<>());

	public static EmployeeDB getInstance() {
		if (empDb == null) {
			empDb = new EmployeeDB();
		}
		return empDb;
	}

	public ObservableList<Employee> getEmpList() {
		return empList;
	}

}
