package geneticCracker.logic.fitnesser;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;

import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.DNA.SubstitutionKey;
import geneticCracker.logic.DNA.TranspositionKey;
import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.cryptModules.Crypter;
import geneticCracker.logic.cryptModules.substitution.crypter.SubstitiutionCrypter;
import geneticCracker.logic.cryptModules.transpsitionCrypter.TranspositionCrypter;
import geneticCracker.logic.languageAnalyzer.LanguageAnalyzer;
import geneticCracker.logic.text.Text;

@Component
public class FitnesserOnlyFrequentWord implements FitnessMaker {

	@Autowired
	SubstitiutionCrypter sCrypter;

	@Autowired
	TranspositionCrypter tCrypter;
	
	@Autowired
	LanguageAnalyzer languageSpec;
	
	private List<String> freqEng;
	
	private FitnesserOnlyFrequentWord() {

	}
	
	@PostConstruct
	public void loadData(){
		try {
			freqEng=languageSpec.getMostFrequentWords("eng");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Creature testCreatureInLife(Creature c) {
		Text t=c.getText();
		Key k=c.getDna();
		Crypter crypter = null;
		if(k instanceof TranspositionKey){
			crypter=tCrypter;
		}
		if(k instanceof SubstitutionKey){
			crypter=sCrypter;
		}

		if(crypter!=null){
		crypter.decrypt(t, k);
		}


		c.setMark(markText(t));
		return c;
	}

	private double markText(Text t){
		double res=0;
	for(String x:freqEng){
	if(t.getContentOfText().contains(x)){
		res=res+5;
	}
		
	}









		return res;
	}

}
