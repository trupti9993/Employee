package com.employee.app;

import com.employee.view.ViewClass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmployeeApplication extends Application  {

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		Parent root= FXMLLoader.load(ViewClass.class.getResource("EmployeeHome.fxml"));
		Scene Scene = new Scene(root);
		stage.setScene(Scene);
		stage.show();
	}
	

}
