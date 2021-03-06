package geneticCracker.logic.fitnesser;



import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import geneticCracker.logic.ngramer.Ngramer;
import geneticCracker.logic.text.Text;
import geneticCracker.logic.wordMapper.WordMapper;

@Component
public class FitnesserCombo implements FitnessMaker {

	@Autowired
	SubstitiutionCrypter sCrypter;

	private Logger logger=Logger.getLogger(getClass());

	@Autowired
	TranspositionCrypter tCrypter;

	@Autowired
	WordMapper wordMapper;
	@Autowired
	LanguageAnalyzer languageSpec;

	private HashMap<String,Integer> map;

	@Autowired
	private Ngramer nrammer;

	private Crypter crypter;

	private Key k;
	private List<String> freqEng;
	private FitnesserCombo() {

	}

	@PostConstruct
	public void loadData(){
		map=languageSpec.getGlobalFrequencyMap("text/learn/eng/txt", 3);


			try {
				freqEng=languageSpec.getMostFrequentWords("eng",100);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}

	@Override
	public void testCreatureInLife(Creature creature) {
		logger.info("Under Test");

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
		logger.info("mark Text");
Text dec=crypt.decrypt(c.getText(), c.getDna());
LinkedHashMap<String, Integer> words=wordMapper.wordMappingText(dec.getContentOfText());
logger.info("\nOddeszyfrowany Tekst:");
logger.info(dec.getContentOfText());

LinkedHashMap<String, Integer> decryptedMap=new LinkedHashMap<String,Integer>();
nrammer.ngramText(dec.getContentOfText(), 3, decryptedMap);
		String check="";
		double currentPKT=0;
		String print="";
		double res=0;
		int val=0;
	for(String wordInDecrypted:decryptedMap.keySet()){
		check=wordInDecrypted.toUpperCase();
print=check;
		if(map.containsKey(check)){
			val=map.get(check);
			check=check.replace(" ", "_");
			currentPKT=(decryptedMap.get(wordInDecrypted)*logOfBase(2, val));
			check=check+"->"+currentPKT;
			addToMap(check, c.getPoints());
			res=res+currentPKT;
		}

		for(String word:words.keySet()){
			if(freqEng.contains(word)){
			res=res+(words.get(word)*100);
					};

		}

//		System.out.println(dec.getContentOfText().substring(0, 10)+" "+c.getDna().getKeyString()+" "+res);
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

	public double logOfBase(int base, int num) {
	    return Math.log(num) / Math.log(base);
	}

}

