package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentServices;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}
	@FXML
	public void onMenuItemAboutrAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle resourceBundle) {
		
		
	}
	
	private synchronized void loadView(String absoluteName) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene scene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) scene.getRoot()).getContent();
			
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("IOException", "Error Load View", e.getMessage(), AlertType.ERROR);
		} 
	
	}

	private synchronized void loadView2(String absoluteName) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene scene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) scene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			
			DepartmentListController controller = loader.getController();
			controller.setDepartmentService(new DepartmentServices());
			controller.updateTableView();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("IOException", "Error Load View", e.getMessage(), AlertType.ERROR);
		}

	}
}
