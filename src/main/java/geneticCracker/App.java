package geneticCracker;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Component;

import com.detectlanguage.DetectLanguage;

import geneticCracker.view.sceneMaker.SceneMaker;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class App extends Application {


	private String title;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(title);
		Scene scene = SceneMaker.getSceneFromFXML("welcomePage");
		primaryStage.setScene(scene);
		primaryStage.show();
		DetectLanguage.apiKey = "3ce3bbc40bf0bace4da3ea536948d00c";
		PropertyConfigurator.configure(getClass().getResource("config/log4j.properties").openStream());
		scene.getStylesheets().add(getClass().getResource("css/standard.css").toExternalForm());
	}

}