package com.employee.controller;

import java.io.IOException;

import com.employee.view.ViewClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployeeHomeController {

	@FXML
	private Button employeeListBtn;

	@FXML
	private Button newEmployeeBtn;

	@FXML
	void onButtonAction(ActionEvent event) throws IOException {
		Stage stage = null;
		Parent myNewScene = null;

		if (event.getSource() == employeeListBtn) {
			stage = (Stage) employeeListBtn.getScene().getWindow();
			myNewScene= FXMLLoader.load(ViewClass.class.getResource("EmployeeList.fxml"));


		} else if (event.getSource() == newEmployeeBtn) {
			stage = (Stage) newEmployeeBtn.getScene().getWindow();
			
			myNewScene= FXMLLoader.load(ViewClass.class.getResource("AddEmployee.fxml"));

		}
		Scene Scene = new Scene(myNewScene);
		stage.setScene(Scene);
		stage.show();
	}

}
