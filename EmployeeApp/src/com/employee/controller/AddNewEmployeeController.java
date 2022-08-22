package com.employee.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.employee.model.Employee;
import com.employee.model.EmployeeDB;
import com.employee.view.ViewClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddNewEmployeeController {

	@FXML
	private Button backBtn;

	@FXML
	private DatePicker dateOfBirth;

	@FXML
	private DatePicker dateOfJoning;

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private Label msgLabel;

	@FXML
	private Button okBtn;

	@FXML
	private GridPane popupGrid;

	@FXML
	private Button saveBtn;

	boolean isRecordSaved = false;

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@FXML
	public void initialize() {
		dateOfBirth.setValue(LocalDate.of(1990, 01, 01));
		LocalDateTime now = LocalDateTime.now();
		dateOfJoning.setValue(LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth()));
		popupGrid.setVisible(false);
		firstName.requestFocus();
	}

	@FXML
	void onButtonAction(ActionEvent event) throws IOException {
		Stage stage = null;
		Parent myNewScene = null;

		if (event.getSource() == saveBtn) {
			isRecordSaved = saveEmployee();
			popupGrid.setVisible(true);

		} else if (event.getSource() == okBtn) {
			if (isRecordSaved) {
				stage = (Stage) okBtn.getScene().getWindow();
				myNewScene = FXMLLoader.load(ViewClass.class.getResource("EmployeeHome.fxml"));
				showScene(stage, myNewScene);
			}
			popupGrid.setVisible(false);
			msgLabel.setText("");
		} else if (event.getSource() == backBtn) {
			stage = (Stage) backBtn.getScene().getWindow();
			myNewScene = FXMLLoader.load(ViewClass.class.getResource("EmployeeHome.fxml"));
			showScene(stage, myNewScene);
		}

	}

	private void showScene(Stage stage, Parent myNewScene) {
		Scene Scene = new Scene(myNewScene);
		stage.setScene(Scene);
		stage.show();
	}

	private boolean saveEmployee() {

		if (firstName.getText().trim().equals("")) {
			msgLabel.setText("Please Enter First Name...!");
			firstName.requestFocus();
			return false;
		}

		if (lastName.getText().trim().equals("")) {
			msgLabel.setText("Please Enter Last Name...!");
			lastName.requestFocus();
			return false;
		}

		EmployeeDB.getInstance().getEmpList().add(new Employee(firstName.getText().trim(), lastName.getText().trim(),
				dtf.format(dateOfBirth.getValue()), dtf.format(dateOfJoning.getValue())));

		msgLabel.setText("Record Saved Sucessfuly...!");

		return true;
	}

}
