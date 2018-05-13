package pl.imageManipulator.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoadingPanelController {
	
	@FXML
	private ImageView loadingAnimation;
	
	@FXML
	public void initialize() {
		loadingAnimation.setImage(new Image("/pl/imageManipulator/resources/images/loading.gif"));
	}
	
	
}
