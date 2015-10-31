package geneticCracker.controller;



import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import geneticCracker.logic.DNA.SubstitutionKey;
import geneticCracker.logic.DNA.TranspositionKey;
import geneticCracker.logic.Language.Language;
import geneticCracker.logic.Language.LanguageBean;
import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.cryptModules.Crypter;
import geneticCracker.logic.cryptModules.substitution.crypter.SubstitiutionCrypter;
import geneticCracker.logic.cryptModules.transpsitionCrypter.TranspositionCrypter;
import geneticCracker.logic.languageAnalyzer.LanguageAnalyzer;
import geneticCracker.logic.text.Text;
import geneticCracker.logic.textReader.fileToString;
import geneticCracker.logic.world.World;
import geneticCracker.view.table.NgramsTableRow;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;


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


		@FXML ListView<String> commonWords;
		@FXML ComboBox<String> languageChoosen;
		@FXML ComboBox<Integer> ngramLength;
		@FXML Button showButton;
		@Autowired LanguageAnalyzer analyzer;

		private ObservableList<NgramsTableRow> ngramsObservableList =FXCollections.observableArrayList();
		@FXML TableView<NgramsTableRow> ngramsTable;
		@FXML TableColumn<NgramsTableRow,Integer> ngramQtyColumn;
		@FXML TableColumn<NgramsTableRow, String> ngramColumn;


		@FXML TextArea decryptedTEXT;
		@FXML TextArea textToDBreak;
		@FXML Button Start;
		@Autowired
		World world;
		@FXML TextField popSizeField;

		@FXML
		private void initialize() {

			plainTextArea.setWrapText(true);
			cryptetTextArea.setWrapText(true);

			cipherChooser.getItems().add("Podstawieniowy");
			cipherChooser.getItems().add("Transpozycyjny");

			keyLengthChooser.getItems().addAll(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30);
			initTextFromScratch();

			//tab2

			languageChoosen.getItems().add("Polski");
			languageChoosen.getItems().add("Angielski");

			languageChoosen.getSelectionModel().select(1);

			ngramLength.getItems().addAll(2,3,4,5,6,7,8,9,10);

			ngramLength.getSelectionModel().select(1);

			ngramsTable.setItems(ngramsObservableList);

			ngramColumn.setCellValueFactory(cellData -> cellData.getValue().getValue());
			ngramQtyColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
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

			textToDBreak.setText(cryptetTextArea.getText());
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

		@FXML public void analyzeLanguage(ActionEvent event) {
			int amount=50;
			int length=ngramLength.getSelectionModel().getSelectedItem();

			ngramsTable.getItems().clear();


			String lang="";

			if(languageChoosen.getSelectionModel().getSelectedItem().equals("Polski")){
				lang="pl";
			}

			if(languageChoosen.getSelectionModel().getSelectedItem().equals("Angielski")){
				lang="eng";
			}
			commonWords.getItems().clear();
			try {
				commonWords.getItems().addAll(analyzer.getMostFrequentWords(lang));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			lang="text/learn/"+lang+"/txt";//make dir

			LinkedHashMap<String, Integer> map=analyzer.getGlobalFrequencyMap(lang, length);
			for(String s:map.keySet()){
				ngramsObservableList.add(
						new NgramsTableRow(
								new SimpleStringProperty(s.replace(" ","_")),
								new SimpleIntegerProperty(map.get(s))));
			}









		}

		@FXML public void startDecodingByGenetic(ActionEvent event) {
			initWorld();
			world.start(Integer.parseInt(popSizeField.getText()));


			Creature best=world.getBestCreatureOnWholeWorld();
			Text decoded=crypterBasedDna(best).decrypt(best.getText(), best.getDna());
			decryptedTEXT.setText(decoded.getContentOfText());


		}



		private Crypter crypterBasedDna(Creature c){
			if(c.getDna() instanceof SubstitutionKey){
				return sCrypter;
			}
			if(c.getDna() instanceof TranspositionKey){
				return tCrypter;
			}
			return sCrypter;
		}

	private void initWorld(){
		world.setTextToBreak(new Text(textToDBreak.getText()));
	}



}
