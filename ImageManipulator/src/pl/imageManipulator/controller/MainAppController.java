package pl.imageManipulator.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.imageManipulator.algorithm.Algorithm;
import pl.imageManipulator.algorithm.Type;

public class MainAppController {

	@FXML
	private ImageView source, edited;
	@FXML
	private CheckBox wand;
	@FXML
	private ComboBox<String> algorithm, wandPower;
	@FXML
	private MenuItem open, save, saveAs, exit, undo, redo, copySource, copyEdited, deleteImages, help, about;
	@FXML
	private RadioMenuItem wandMenu;
	@FXML
	private RadioMenuItem averageFilter, hp3Filter, verticalSobelFilter, grayScale, userFilter, randomFilter;
	@FXML
	private ImageView saveImg, saveAsImg, undoImg, redoImg, copyEditedImg, wandImg, deleteImagesImg, plusImg, minusImg;
	@FXML
	private Button processButton;
	@FXML
	private Label wandPoint;

	private Stage stage;

	private String imagePath;

	private HashMap<Integer, Image> steps = new HashMap<>();
	private Integer currentStep = 0;

	private Algorithm alg;
	private int[] userMask;

	@FXML
	public void initialize() {
		alg = new Algorithm();

		algorithm.getItems().addAll(getAlgorithmList());
		algorithm.getSelectionModel().select(1);

		wandPower.getItems().addAll(getWandPowerList());
		wandPower.getSelectionModel().select(150);

		setMenuItemIcons();

		save.setDisable(true);
		saveAs.setDisable(true);
		undo.setDisable(true);
		redo.setDisable(true);
		copySource.setDisable(true);
		copyEdited.setDisable(true);
		wandMenu.setDisable(true);
		deleteImages.setDisable(true);

		saveImg.setVisible(false);
		saveAsImg.setVisible(false);
		undoImg.setVisible(false);
		redoImg.setVisible(false);
		copyEditedImg.setVisible(false);
		wandImg.setVisible(false);
		deleteImagesImg.setVisible(false);

		plusImg.setVisible(false);
		minusImg.setVisible(false);

		algorithm.setDisable(true);
		wand.setDisable(true);
		wandPoint.setDisable(true);
		wandPower.setDisable(true);
		processButton.setDisable(true);

		averageFilter.setDisable(true);
		hp3Filter.setDisable(true);
		verticalSobelFilter.setDisable(true);
		grayScale.setDisable(true);
		userFilter.setDisable(true);
		randomFilter.setDisable(true);

		edited.setImage(null);
	}

	private void setMenuItemIcons() {
		open.setGraphic(new ImageView("/pl/imageManipulator/resources/images/open.png"));
		save.setGraphic(new ImageView("/pl/imageManipulator/resources/images/save.png"));
		saveAs.setGraphic(new ImageView("/pl/imageManipulator/resources/images/saveAs.png"));
		exit.setGraphic(new ImageView("/pl/imageManipulator/resources/images/exit.png"));
		undo.setGraphic(new ImageView("/pl/imageManipulator/resources/images/undo.png"));
		redo.setGraphic(new ImageView("/pl/imageManipulator/resources/images/redo.png"));
		copyEdited.setGraphic(new ImageView("/pl/imageManipulator/resources/images/copy.png"));
		wandMenu.setGraphic(new ImageView("/pl/imageManipulator/resources/images/wand.png"));
		deleteImages.setGraphic(new ImageView("/pl/imageManipulator/resources/images/delete.png"));
		help.setGraphic(new ImageView("/pl/imageManipulator/resources/images/help.png"));
		about.setGraphic(new ImageView("/pl/imageManipulator/resources/images/about.png"));
	}

	private ObservableList<String> getAlgorithmList() {
		ObservableList<String> list = FXCollections.observableArrayList();

		list.add("Uœredniaj¹cy");
		list.add("HP3");
		list.add("Poziomy Sobela");
		list.add("Odcienie Szaroœci");
		list.add("U¿ytkownika");
		list.add("Losowy");

		return list;
	}

	private ObservableList<String> getWandPowerList() {
		ObservableList<String> list = FXCollections.observableArrayList();

		for (int i = 0; i <= 255; i++)
			list.add(String.valueOf(i));

		return list;
	}

	@FXML
	public void open() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Wybierz obrazek");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JPG", "*.jpg"),
				new ExtensionFilter("PNG", "*.png"), new ExtensionFilter("JPEG", "*.jpeg"));

		File file = fileChooser.showOpenDialog(stage);

		if (file != null) {
			source.setImage(new Image(file.toURI().toString()));
			imagePath = file.getPath();

			save.setDisable(false);
			saveAs.setDisable(false);
			copySource.setDisable(false);
			wandMenu.setDisable(false);
			deleteImages.setDisable(false);

			saveImg.setVisible(true);
			saveAsImg.setVisible(true);
			wandImg.setVisible(true);
			deleteImagesImg.setVisible(true);

			algorithm.setDisable(false);
			wand.setDisable(false);
			wand.setSelected(false);
			processButton.setDisable(false);

			averageFilter.setDisable(false);
			hp3Filter.setDisable(false);
			verticalSobelFilter.setDisable(false);
			grayScale.setDisable(false);
			userFilter.setDisable(false);
			randomFilter.setDisable(false);

			hp3Filter.setSelected(true);
			algorithm.getSelectionModel().select(1);
		}

	}

	@FXML
	public void save() {
		if (edited.getImage() != null) {
			File file = new File(imagePath);
			if (file != null) {
				try {
					ImageIO.write(SwingFXUtils.fromFXImage(edited.getImage(), null), "png", file);
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Zapis obrazla");
				alert.setHeaderText(null);
				alert.setContentText("Obrazek zosta³ poprawmie zapisany");

				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("B³¹d zapisu");
			alert.setHeaderText(null);
			alert.setContentText("Obrazek do edycji nie zosta³ wygenerowany");

			alert.showAndWait();
		}

	}

	@FXML
	public void saveAs() {
		if (edited.getImage() != null) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Zapisz obrazek");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JPG", "*.jpg"),
					new ExtensionFilter("PNG", "*.png"), new ExtensionFilter("JPEG", "*.jpeg"));

			File file = fileChooser.showSaveDialog(stage);
			if (file != null) {
				try {
					ImageIO.write(SwingFXUtils.fromFXImage(source.getImage(), null), "png", file);
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("B³¹d zapisu");
			alert.setHeaderText(null);
			alert.setContentText("Obrazek do edycji nie zosta³ wygenerowany");

			alert.showAndWait();
		}
	}

	@FXML
	public void exit() {
		Alert alert = new Alert(AlertType.INFORMATION, null, ButtonType.OK, ButtonType.NO);
		alert.setTitle("Wyjœcie");
		alert.setHeaderText(null);
		alert.setContentText("Wyjœæ z aplikacji?");

		alert.showAndWait();

		if (alert.getResult() == ButtonType.OK)
			stage.close();
	}

	@FXML
	public void undo() {
		if (currentStep > 0) {
			Image previousImage = steps.get(currentStep - 1);
			currentStep--;
			edited.setImage(previousImage);

			if (currentStep == 0) {
				undo.setDisable(true);
				undoImg.setVisible(false);
			}

			redo.setDisable(false);
			redoImg.setVisible(true);
		} else {
			undo.setDisable(true);
			undoImg.setVisible(false);
		}

	}

	@FXML
	public void redo() {
		if (currentStep < getLastStepId()) {
			Image nextImage = steps.get(currentStep + 1);
			currentStep++;
			edited.setImage(nextImage);

			if (currentStep == getLastStepId()) {
				redo.setDisable(true);
				redoImg.setVisible(false);
			}

			undo.setDisable(false);
			undoImg.setVisible(true);
		} else {
			redo.setDisable(true);
			redoImg.setVisible(false);
		}
	}

	@FXML
	public void copyEdited() {
		if (edited.getImage() != null) {
			Clipboard clipboard = Clipboard.getSystemClipboard();
			ClipboardContent clipboardContent = new ClipboardContent();

			clipboardContent.putImage(edited.getImage());
			clipboard.setContent(clipboardContent);
		}
	}

	@FXML
	public void copySource() {
		if (source.getImage() != null) {
			Clipboard clipboard = Clipboard.getSystemClipboard();
			ClipboardContent clipboardContent = new ClipboardContent();

			clipboardContent.putImage(source.getImage());
			clipboard.setContent(clipboardContent);
		}
	}

	@FXML
	public void wand() {
		if (!wand.isSelected()) {
			wand.setSelected(false);
			wandMenu.setSelected(false);

			alg.turnOffWand();
			wandPoint.setDisable(true);
			plusImg.setVisible(false);
			minusImg.setVisible(false);
			wandPower.setDisable(true);
		} else {
			wand.setSelected(true);
			wandMenu.setSelected(true);

			alg.turnOnWand();
			wandPoint.setDisable(false);
			plusImg.setVisible(true);
			minusImg.setVisible(true);
			wandPower.setDisable(false);
		}
	}
	
	@FXML
	public void wand2() {
		if (wand.isSelected()) {
			wand.setSelected(false);
			wandMenu.setSelected(false);

			alg.turnOffWand();
			wandPoint.setDisable(true);
			plusImg.setVisible(false);
			minusImg.setVisible(false);
			wandPower.setDisable(true);
		} else {
			wand.setSelected(true);
			wandMenu.setSelected(true);

			alg.turnOnWand();
			wandPoint.setDisable(false);
			plusImg.setVisible(true);
			minusImg.setVisible(true);
			wandPower.setDisable(false);
		}
	}

	@FXML
	public void deleteImages() {
		source.setImage(null);
		edited.setImage(null);

		save.setDisable(true);
		saveImg.setVisible(false);
		saveAs.setDisable(true);
		saveAsImg.setVisible(false);
		averageFilter.setDisable(true);
		hp3Filter.setDisable(true);
		randomFilter.setDisable(true);
		grayScale.setDisable(true);
		userFilter.setDisable(true);
		verticalSobelFilter.setDisable(true);
		undo.setDisable(true);
		undoImg.setVisible(false);
		redo.setDisable(true);
		redoImg.setVisible(false);
		copyEdited.setDisable(true);
		copyEditedImg.setVisible(false);
		copySource.setDisable(true);
		wand.setDisable(true);
		wandPoint.setDisable(true);
		wandImg.setVisible(false);
		wandMenu.setDisable(true);
		wandPower.setDisable(true);
		algorithm.setDisable(true);
		plusImg.setVisible(false);
		minusImg.setVisible(false);
		processButton.setDisable(true);
		deleteImages.setDisable(true);
		deleteImagesImg.setVisible(false);
	}

	@FXML
	public void selectAverage() {
		algorithm.getSelectionModel().select(0);
	}

	@FXML
	public void selectHP3() {
		algorithm.getSelectionModel().select(1);
	}

	@FXML
	public void selectVerticalSobel() {
		algorithm.getSelectionModel().select(2);
	}

	@FXML
	public void selectGrayScale() {
		algorithm.getSelectionModel().select(3);
	}

	@FXML
	public void selectUserFilter() throws IOException {
		algorithm.getSelectionModel().select(4);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/imageManipulator/resources/UserMaskPanel.fxml"));
		AnchorPane pane = loader.load();

		Image icon = new Image("/pl/imageManipulator/resources/images/appIcon.png");

		Stage stage = new Stage();
		stage.setTitle("Image Manipulator");
		stage.setScene(new Scene(pane));
		stage.getIcons().add(icon);
		stage.setAlwaysOnTop(true);
		stage.show();
		stage.setResizable(false);

		UserMaskPanelController controller = loader.getController();
		controller.setStage(stage);
		controller.setMainController(this);
	}

	@FXML
	public void selectRandom() {
		algorithm.getSelectionModel().select(5);
	}

	@FXML
	public void help() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pomoc");
		alert.setHeaderText(null);
		alert.setContentText(
				"Program do przetwarzania obrazów\nAby przetworzyæ obraz najpierw nale¿y go wczytaæ. Nastêpnie nale¿y dobraæ ¿¹dane ustawienia"
						+ " i nacisn¹æ przycisk \"Przetwarzaj\".\nNie wszystkie opcje w programie s¹ od razu dostêpne\nPo w³¹czeniu ró¿d¿ki i naciœniêciu na obrazek"
						+ " automatycznie zostanie przetworzony obrazek");

		alert.showAndWait();
	}

	@FXML
	public void about() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("O programie");
		alert.setHeaderText(null);
		alert.setContentText("Program do przetwarzania obrazów\nMateusz Œliwa 2018");

		alert.showAndWait();
	}

	@FXML
	public void setAlgorithm() throws IOException {
		deselectFilterMenuItem();

		switch (algorithm.getSelectionModel().getSelectedIndex()) {
		case 0:
			averageFilter.setSelected(true);
			break;
		case 1:
			hp3Filter.setSelected(true);
			break;
		case 2:
			verticalSobelFilter.setSelected(true);
			break;
		case 3:
			grayScale.setSelected(true);
			break;
		case 4:
			userFilter.setSelected(true);
			selectUserFilter();
			break;
		case 5:
			randomFilter.setSelected(true);
			break;

		default:
			break;
		}
	}

	private void deselectFilterMenuItem() {
		averageFilter.setSelected(false);
		hp3Filter.setSelected(false);
		verticalSobelFilter.setSelected(false);
		grayScale.setSelected(false);
		userFilter.setSelected(false);
		randomFilter.setSelected(false);
	}

	@FXML
	public void minus() {
		this.alg.decreaseWandPower();
		String power = String.valueOf(alg.getWandPower());
		this.wandPower.setValue(power);
	}

	@FXML
	public void setWandPower() throws Exception {
		int power = Integer.valueOf(wandPower.getSelectionModel().getSelectedItem());
		this.alg.setWandPower(power);
	}

	@FXML
	public void plus() {
		this.alg.increaseWandPower();
		String power = String.valueOf(alg.getWandPower());
		this.wandPower.setValue(power);
	}

	@FXML
	private void getMousePosition() {
		source.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (wand.isSelected()) {
					int x = (int) event.getX();
					int y = (int) event.getY();

					wandPoint.setText("Wybrany punkt: " + x + ", " + y);
					alg.setWandPosition(x, y);
					try {
						process();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("B³¹d");
					alert.setHeaderText(null);
					alert.setContentText("Nie zosta³a w³¹czona ró¿d¿ka, aby pobraæ punkt obrazka, w³¹cz ró¿d¿kê");
					alert.showAndWait();
				}

			}

		});
	}

	class AlgorithmRunning implements Runnable {
		public void run() {
			try {
				BufferedImage img = null;
				img = ImageIO.read(new File(imagePath));

				switch (algorithm.getSelectionModel().getSelectedIndex()) {
				case 0:
					img = alg.filter(img, Type.AVERAGING);
					break;
				case 1:
					img = alg.filter(img, Type.HP3);
					break;
				case 2:
					img = alg.filter(img, Type.VERTICAL_SOBEL);
					break;
				case 3:
					img = alg.shadesOfGray(img);
					break;
				case 4:
					alg.setUserMask(userMask);
					img = alg.filter(img, Type.USER);
					break;
				case 5:
					img = alg.filter(img, Type.RANDOM);
					break;
				default:
					break;
				}

				edited.setImage(SwingFXUtils.toFXImage(img, null));

				steps.put(getNextStepId(), edited.getImage());

				undo.setDisable(false);
				undoImg.setVisible(true);
				redo.setDisable(true);
				redoImg.setVisible(false);

				copyEdited.setDisable(false);
				copyEditedImg.setVisible(true);
				
				// Exit other window in other thread
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						stage.close();
					}
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void process() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/imageManipulator/resources/LoadingPanel.fxml"));
		AnchorPane pane = loader.load();

		Image icon = new Image("/pl/imageManipulator/resources/images/appIcon.png");

		stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		pane.setStyle("-fx-background-color: rgba(255, 255, 255, 0);");
		Scene scene = new Scene(pane);
		scene.setFill(null);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		stage.getIcons().add(icon);
		stage.setAlwaysOnTop(true);
		stage.show();
		stage.setResizable(false);
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(new AlgorithmRunning());
	}

	private Integer getLastStepId() {
		if (steps.isEmpty())
			return 0;
		else {
			Integer lastId = 0;
			for (Map.Entry<Integer, Image> entry : steps.entrySet()) {
				lastId = entry.getKey();
			}
			return lastId;
		}
	}

	private Integer getNextStepId() {
		if (steps.isEmpty())
			return 0;
		else {
			Integer lastId = 0;
			for (Map.Entry<Integer, Image> entry : steps.entrySet()) {
				lastId = entry.getKey();
			}
			currentStep = lastId + 1;
			return lastId + 1;
		}
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setUserMask(int[] userMask) {
		this.userMask = userMask;
	}

}
