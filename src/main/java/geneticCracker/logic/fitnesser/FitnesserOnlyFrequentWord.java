package geneticCracker.logic.fitnesser;

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
import geneticCracker.logic.text.Text;

@Component
public class FitnesserOnlyFrequentWord implements FitnessMaker {

	@Autowired
	SubstitiutionCrypter sCrypter;

	@Autowired
	TranspositionCrypter tCrypter;


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











		return res;
	}

}
