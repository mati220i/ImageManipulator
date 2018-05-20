package pl.imageManipulator.mainApp;
	
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import pl.imageManipulator.controller.MainAppController;
import pl.imageManipulator.resources.languages.Utf8ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


/**
 * Main application class for image processing 
 * @author Mateusz Œliwa <mateuszsliwa7@wp.pl>
 *
 */
public class Main extends Application {
	private Stage primaryStage;
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		Locale locale = new Locale("pl", "PL");
		//Locale locale = new Locale("en", "EN");
		setStage(locale);
	}
	
	public void setStage(Locale locale) throws IOException {
		ResourceBundle resources;
		if(locale.equals(new Locale("pl","PL")))
			resources = Utf8ResourceBundle.getBundle("pl.imageManipulator.resources.languages.Bundle");
		else
			resources = ResourceBundle.getBundle("pl.imageManipulator.resources.languages.Bundle", locale); 
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/imageManipulator/resources/MainApp.fxml"));
		loader.setResources(resources);
		
		AnchorPane pane = loader.load();
		
		Image icon = new Image("/pl/imageManipulator/resources/images/appIcon.png");
		
		Scene scene = new Scene(pane);

		primaryStage.setTitle("Image Manipulator");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(icon);
		primaryStage.show();
		
		MainAppController controller = loader.getController();
		controller.setStage(primaryStage);
		controller.setMain(this);
		controller.setLocale(locale);
		controller.setBundle(resources);
		controller.refresh();
	}

}


