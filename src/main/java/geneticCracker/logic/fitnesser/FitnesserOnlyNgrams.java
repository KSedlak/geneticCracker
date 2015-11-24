package geneticCracker.logic.fitnesser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.hamcrest.core.IsNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.DNA.SubstitutionKey;
import geneticCracker.logic.DNA.TranspositionKey;
import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.cryptModules.Crypter;
import geneticCracker.logic.cryptModules.substitution.crypter.SubstitiutionCrypter;
import geneticCracker.logic.cryptModules.transpsitionCrypter.TranspositionCrypter;
import geneticCracker.logic.languageAnalyzer.LanguageAnalyzer;
import geneticCracker.logic.markerDB.markerDB;
import geneticCracker.logic.ngramer.Ngramer;
import geneticCracker.logic.text.Text;

@Component
@DependsOn("markerDB")
public class FitnesserOnlyNgrams implements FitnessMaker {

	@Autowired
	SubstitiutionCrypter sCrypter;

	private Logger logger=Logger.getLogger(getClass());

	@Autowired
	TranspositionCrypter tCrypter;

	@Autowired
	LanguageAnalyzer languageSpec;

	private HashMap<String,Integer> map;

	@Autowired
	private Ngramer nrammer;

	@Autowired
	markerDB mChecker;

	private Crypter crypter;

	private Key k;

	private FitnesserOnlyNgrams() {

	}

	@PostConstruct
	public void loadData(){
		map=languageSpec.getGlobalFrequencyMap("text/learn/eng/txt", 3);


	}

	@Override
	public void testCreatureInLife(Creature creature) {
		//logger.info("Under Test");

		k=creature.getDna();
		if(k instanceof TranspositionKey){
			crypter=tCrypter;
		}
		if(k instanceof SubstitutionKey){
			crypter=sCrypter;
		}

		Double mark=markText(creature, crypter);


		creature.setMark(mark);
		mChecker.addToMap(creature.getDna().getKeyString(), mark);

	}

	private double markText(Creature c, Crypter crypt){
	//	logger.info("mark Text");

if(mChecker.alreadyChecked(c.getDna().getKeyString())!=null){

	return mChecker.alreadyChecked(c.getDna().getKeyString());
}
else{
Text dec=crypt.decrypt(c.getText(), c.getDna());

//logger.info("\nOdszyfrowany Tekst:");
//logger.info(dec.getContentOfText());

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


//		System.out.println(dec.getContentOfText().substring(0, 10)+" "+c.getDna().getKeyString()+" "+res);
	}

	return res;
}
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
