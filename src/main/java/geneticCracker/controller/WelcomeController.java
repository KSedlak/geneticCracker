package geneticCracker.controller;

import java.io.IOException;
import org.springframework.stereotype.Component;

import geneticCracker.App;
import geneticCracker.view.sceneMaker.SceneMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@Component
public class WelcomeController {

	@FXML
	Button enterButton;



	@FXML
	ImageView image;



	@FXML Label label;

	@FXML
	public void enterButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) enterButton.getScene().getWindow();
		stage.setScene(SceneMaker.getSceneFromFXML("firstPage"));

	}

	@FXML
	private void initialize() {
		try {
			image.setImage(new Image(App.class.getResource("images/startup.jpg").openStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
