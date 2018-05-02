package pl.imageManipulator.mainApp;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import pl.imageManipulator.controller.MainAppController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	public static void main(String[] args) throws IOException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/imageManipulator/resources/MainApp.fxml"));
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
	}
}
