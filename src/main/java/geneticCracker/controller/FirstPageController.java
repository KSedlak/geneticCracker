package geneticCracker.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import geneticCracker.logic.DNA.SubstitutionKey;
import geneticCracker.logic.DNA.TranspositionKey;
import geneticCracker.logic.Language.Language;
import geneticCracker.logic.Language.LanguageBean;
import geneticCracker.logic.cryptModules.substitution.crypter.SubstitiutionCrypter;
import geneticCracker.logic.cryptModules.transpsitionCrypter.TranspositionCrypter;
import geneticCracker.logic.text.Text;
import geneticCracker.logic.textReader.fileToString;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;


@Component
public class FirstPageController {


		@FXML Tab crypterTab;
		@FXML TextArea plainTextArea;
		@FXML ComboBox<String> cipherChooser;
		@FXML ComboBox<Integer> keyLengthChooser;
		@FXML Button cryptButton;
		@FXML TextArea cryptetTextArea;
		@FXML TextField keyField;
		@Autowired TranspositionCrypter tCrypter;
		@Autowired LanguageBean language;
		@Autowired SubstitiutionCrypter sCrypter;
		@FXML ListView commonWords;
		@FXML ComboBox langCombo;
		@FXML ComboBox ngramLengthCombo;

		@FXML
		private void initialize() {

			plainTextArea.setWrapText(true);
			cryptetTextArea.setWrapText(true);

			cipherChooser.getItems().add("Podstawieniowy");
			cipherChooser.getItems().add("Transpozycyjny");

			keyLengthChooser.getItems().addAll(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30);
			initTextFromScratch();
		}

		private void initTextFromScratch() {
			plainTextArea.setText(fileToString.getFileFromDirectory("text/subject.txt"));


		}

		@FXML public void crypt(ActionEvent event) {


			if(cipherChooser.getSelectionModel().getSelectedIndex()==1){
				int length=keyLengthChooser.getSelectionModel().getSelectedItem();
					TranspositionKey key= new TranspositionKey(length);
					Text t=new Text(plainTextArea.getText());
					tCrypter.crypt(t, key);
					keyField.setText(key.getKeyString());
					cryptetTextArea.setText(t.getContentOfText());
			}

			if(cipherChooser.getSelectionModel().getSelectedIndex()==0){
					Language lang=language.getLanguageFromString(plainTextArea.getText());
					Text t=new Text(plainTextArea.getText(), lang);
					SubstitutionKey key=new SubstitutionKey(lang);
					sCrypter.crypt(t, key);
					keyField.setText(key.getKeyString());
					cryptetTextArea.setText(t.getContentOfText());



			}

		}

		@FXML public void decrypt(ActionEvent event) {


			if(keyField.getText().contains("1")){
				Text t=new Text(cryptetTextArea.getText());
				TranspositionKey key= new TranspositionKey(keyField.getText());
				tCrypter.decrypt(t, key);
				plainTextArea.setText(t.getContentOfText());
				cipherChooser.getSelectionModel().select(1);
				keyLengthChooser.getSelectionModel().select(key.getKey().length-4);
			}

			if(keyField.getText().contains("A")){
				Language lang = language.getEnglish();
				SubstitutionKey key=new SubstitutionKey(keyField.getText().split(","));
				if(keyField.getText().contains("Ż")){
				lang=language.getPolish();
				}

				Text t=new Text(cryptetTextArea.getText(),lang);

				sCrypter.decrypt(t, key);
				plainTextArea.setText(t.getContentOfText());
				cipherChooser.getSelectionModel().select(0);
			}
		}




}
