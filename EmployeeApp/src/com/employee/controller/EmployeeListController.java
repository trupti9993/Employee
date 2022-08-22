package com.employee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.employee.model.Employee;
import com.employee.model.EmployeeDB;
import com.employee.view.ViewClass;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EmployeeListController {

	@FXML
	private Button previousBtn;

	@FXML
	private Button nextBtn;

	@FXML
	private Button backBtn;

	@FXML
	private TableView<Employee> employeeTableView;

	int maxRecordsPerPage = 8;

	int currentPageNo = 1;

	int defaultPageNo = 1;

	Map<Integer, List<Employee>> empPageMap = new HashMap<>();

	@FXML
	public void initialize() {

		updatePagination();

		EmployeeDB.getInstance().getEmpList().addListener(new ListChangeListener<Employee>() {

			@Override
			public void onChanged(Change<? extends Employee> c) {
				updatePagination();
				updateTableView();
			}

		});

		employeeTableView.getColumns().clear();

		TableColumn<Employee, String> column1 = new TableColumn<>("First Name");
		column1.setMinWidth(105);
		column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn<Employee, String> column2 = new TableColumn<>("Last Name");
		column2.setMinWidth(100);
		
		column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		TableColumn<Employee, String> column3 = new TableColumn<>("Date Of Birth");
		column2.setMinWidth(80);
		
		column3.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

		TableColumn<Employee, String> column4 = new TableColumn<>("Date Of Joining");
		column4.setMinWidth(100);

		column4.setCellValueFactory(new PropertyValueFactory<>("dateOfJoining"));

		employeeTableView.getColumns().add(column1);
		employeeTableView.getColumns().add(column2);
		employeeTableView.getColumns().add(column3);
		employeeTableView.getColumns().add(column4);

		employeeTableView.getItems().addAll(empPageMap.get(defaultPageNo));

	}

	private void updatePagination() {
		int pageNo = 0;
		empPageMap.clear();
		ObservableList<Employee> empList = EmployeeDB.getInstance().getEmpList();

		for (int i = 0; i < empList.size(); i++) {
			if (i % maxRecordsPerPage == 0) {
				pageNo++;
				empPageMap.put(pageNo, new ArrayList<>());
			}
			empPageMap.get(pageNo).add(empList.get(i));
		}

		if (empPageMap.isEmpty()) {
			empPageMap.put(defaultPageNo, new ArrayList<>());
		}

	}

	@FXML
	void onButtonAction(ActionEvent event) throws IOException {
		if (event.getSource() == nextBtn) {
			showNextRecords();
		} else if (event.getSource() == previousBtn) {
			showPreviousRecords();
		} else if (event.getSource() == backBtn) {
			Stage stage = (Stage) backBtn.getScene().getWindow();

			Parent myNewScene = FXMLLoader.load(ViewClass.class.getResource("EmployeeHome.fxml"));

			Scene Scene = new Scene(myNewScene);
			stage.setScene(Scene);
			stage.show();
		}
	}

	private void updateTableView() {
		employeeTableView.getItems().clear();
		employeeTableView.getItems().addAll(empPageMap.get(currentPageNo));
	}

	private void showPreviousRecords() {
		if (currentPageNo > defaultPageNo) {
			currentPageNo--;
		}

		updateTableView();

	}

	private void showNextRecords() {

		if (currentPageNo < empPageMap.size()) {
			currentPageNo++;
		}
		updateTableView();
	}

}
