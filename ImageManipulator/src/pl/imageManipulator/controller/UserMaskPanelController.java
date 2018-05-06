package pl.imageManipulator.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class UserMaskPanelController {

	@FXML
	private ComboBox<String> m00, m01, m02, m03, m04,
							 m10, m11, m12, m13, m14,
							 m20, m21, m22, m23, m24,
							 m30, m31, m32, m33, m34,
							 m40, m41, m42, m43, m44;
	
	private Stage stage;
	private MainAppController mainController;
	
	@FXML
	public void initialize() {
		m00.getItems().addAll(getSettingsList());
		m01.getItems().addAll(getSettingsList());
		m02.getItems().addAll(getSettingsList());
		m03.getItems().addAll(getSettingsList());
		m04.getItems().addAll(getSettingsList());
		m10.getItems().addAll(getSettingsList());
		m11.getItems().addAll(getSettingsList());
		m12.getItems().addAll(getSettingsList());
		m13.getItems().addAll(getSettingsList());
		m14.getItems().addAll(getSettingsList());
		m20.getItems().addAll(getSettingsList());
		m21.getItems().addAll(getSettingsList());
		m22.getItems().addAll(getSettingsList());
		m23.getItems().addAll(getSettingsList());
		m24.getItems().addAll(getSettingsList());
		m30.getItems().addAll(getSettingsList());
		m31.getItems().addAll(getSettingsList());
		m32.getItems().addAll(getSettingsList());
		m33.getItems().addAll(getSettingsList());
		m34.getItems().addAll(getSettingsList());
		m40.getItems().addAll(getSettingsList());
		m41.getItems().addAll(getSettingsList());
		m42.getItems().addAll(getSettingsList());
		m43.getItems().addAll(getSettingsList());
		m44.getItems().addAll(getSettingsList());
		
		m00.getSelectionModel().select(15);
		m01.getSelectionModel().select(15);
		m02.getSelectionModel().select(15);
		m03.getSelectionModel().select(15);
		m04.getSelectionModel().select(15);
		m10.getSelectionModel().select(15);
		m11.getSelectionModel().select(15);
		m12.getSelectionModel().select(15);
		m13.getSelectionModel().select(15);
		m14.getSelectionModel().select(15);
		m20.getSelectionModel().select(15);
		m21.getSelectionModel().select(15);
		m22.getSelectionModel().select(15);
		m23.getSelectionModel().select(15);
		m24.getSelectionModel().select(15);
		m30.getSelectionModel().select(15);
		m31.getSelectionModel().select(15);
		m32.getSelectionModel().select(15);
		m33.getSelectionModel().select(15);
		m34.getSelectionModel().select(15);
		m40.getSelectionModel().select(15);
		m41.getSelectionModel().select(15);
		m42.getSelectionModel().select(15);
		m43.getSelectionModel().select(15);
		m44.getSelectionModel().select(15);
	}
	
	private ObservableList<String> getSettingsList() {
		ObservableList<String> list = FXCollections.observableArrayList();
		
		for(int i=-15; i<=15; i++)
			list.add(String.valueOf(i));
		
		return list;
	}
	
	@FXML
	public void confirm() {
		mainController.setUserMask(getMask());
		stage.close();
	}
	
	private int[] getMask() {
		int u00 = Integer.valueOf(m00.getSelectionModel().getSelectedItem());
		int u01 = Integer.valueOf(m01.getSelectionModel().getSelectedItem());
		int u02 = Integer.valueOf(m02.getSelectionModel().getSelectedItem());
		int u03 = Integer.valueOf(m03.getSelectionModel().getSelectedItem());
		int u04 = Integer.valueOf(m04.getSelectionModel().getSelectedItem());
		int u10 = Integer.valueOf(m10.getSelectionModel().getSelectedItem());
		int u11 = Integer.valueOf(m11.getSelectionModel().getSelectedItem());
		int u12 = Integer.valueOf(m12.getSelectionModel().getSelectedItem());
		int u13 = Integer.valueOf(m13.getSelectionModel().getSelectedItem());
		int u14 = Integer.valueOf(m14.getSelectionModel().getSelectedItem());
		int u20 = Integer.valueOf(m20.getSelectionModel().getSelectedItem());
		int u21 = Integer.valueOf(m21.getSelectionModel().getSelectedItem());
		int u22 = Integer.valueOf(m22.getSelectionModel().getSelectedItem());
		int u23 = Integer.valueOf(m23.getSelectionModel().getSelectedItem());
		int u24 = Integer.valueOf(m24.getSelectionModel().getSelectedItem());
		int u30 = Integer.valueOf(m30.getSelectionModel().getSelectedItem());
		int u31 = Integer.valueOf(m31.getSelectionModel().getSelectedItem());
		int u32 = Integer.valueOf(m32.getSelectionModel().getSelectedItem());
		int u33 = Integer.valueOf(m33.getSelectionModel().getSelectedItem());
		int u34 = Integer.valueOf(m34.getSelectionModel().getSelectedItem());
		int u40 = Integer.valueOf(m40.getSelectionModel().getSelectedItem());
		int u41 = Integer.valueOf(m41.getSelectionModel().getSelectedItem());
		int u42 = Integer.valueOf(m42.getSelectionModel().getSelectedItem());
		int u43 = Integer.valueOf(m43.getSelectionModel().getSelectedItem());
		int u44 = Integer.valueOf(m44.getSelectionModel().getSelectedItem());
		
		
		int[] mask = {u00, u01, u02, u03, u04,
					  u10, u11, u12, u13, u14,
					  u20, u21, u22, u23, u24,
					  u30, u31, u32, u33, u34,
					  u40, u41, u42, u43, u44};
		
		return mask;
	} 
	
	@FXML
	public void reset() {
		m00.getSelectionModel().select(15);
		m01.getSelectionModel().select(15);
		m02.getSelectionModel().select(15);
		m03.getSelectionModel().select(15);
		m04.getSelectionModel().select(15);
		m10.getSelectionModel().select(15);
		m11.getSelectionModel().select(15);
		m12.getSelectionModel().select(15);
		m13.getSelectionModel().select(15);
		m14.getSelectionModel().select(15);
		m20.getSelectionModel().select(15);
		m21.getSelectionModel().select(15);
		m22.getSelectionModel().select(15);
		m23.getSelectionModel().select(15);
		m24.getSelectionModel().select(15);
		m30.getSelectionModel().select(15);
		m31.getSelectionModel().select(15);
		m32.getSelectionModel().select(15);
		m33.getSelectionModel().select(15);
		m34.getSelectionModel().select(15);
		m40.getSelectionModel().select(15);
		m41.getSelectionModel().select(15);
		m42.getSelectionModel().select(15);
		m43.getSelectionModel().select(15);
		m44.getSelectionModel().select(15);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setMainController(MainAppController mainController) {
		this.mainController = mainController;
	}

	
}
