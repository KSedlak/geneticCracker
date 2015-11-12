package geneticCracker.logic.fitnesser;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

	private Key k;

	private Crypter crypter;



	private Logger logger=Logger.getLogger(getClass());
	private FitnesserOnlyFrequentWord() {

	}

	@PostConstruct
	public void loadData(){
		try {
			freqEng=languageSpec.getMostFrequentWords("eng",100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void testCreatureInLife(Creature creature) {
logger.info("Fitness mark");
		k=creature.getDna();
		if(k instanceof TranspositionKey){
			crypter=tCrypter;
		}
		if(k instanceof SubstitutionKey){
			crypter=sCrypter;
		}

		Double mark=markText(creature, crypter);


		creature.setMark(mark);

	}

	private double markText(Creature c, Crypter crypt){

Text dec=crypt.decrypt(c.getText(), c.getDna());
		String mapped;
		String check="";
		double res=0;
	for(String x:freqEng){
	if(dec.getContentOfText().contains(x.toUpperCase())){
		res=res+5;
	mapped=(x.replaceAll(" ", "_").toUpperCase());

		addToMap(mapped,c.getPoints());
	}

	}
		return res;
	}




	private void addToMap(String s, TreeMap<String, Integer> points){
		int val=0;
		if(points.containsKey(s)){
			val=points.get(s);
		}
		points.put(s, val+1);

	}






}
