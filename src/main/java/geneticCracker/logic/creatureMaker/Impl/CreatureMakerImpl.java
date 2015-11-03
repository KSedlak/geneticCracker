package geneticCracker.logic.creatureMaker.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import geneticCracker.logic.DNA.SubstitutionKey;
import geneticCracker.logic.DNA.TranspositionKey;
import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.creatureMaker.CreatureMaker;
import geneticCracker.logic.cryptModules.transpositionKeyGenerator.TranspositionKeyGenerator;
import geneticCracker.logic.text.Text;
@Service
public class CreatureMakerImpl implements CreatureMaker {

	@Autowired
	TranspositionKeyGenerator generateTrans;
	
	@Override
	public List<Creature> generateCreaturesTransposition(int numberOfCreatures, int lengthKey, Text t) {
		List<Creature> result=new ArrayList<Creature>();
		for(int i=0;i<numberOfCreatures;i++){
			TranspositionKey key=new TranspositionKey(lengthKey);
			Creature c=new Creature(t, key);
			result.add(c);
		}
		
		
		return result;
	}

	@Override
	public List<Creature> generateCreaturesTransposition(int numberOfCreatures, Text t, int a, int b) {
		int length;
	
	
		List<Creature> result=new ArrayList<Creature>();
		for(int i=0;i<numberOfCreatures;i++){
			length=ThreadLocalRandom.current().nextInt(a,b+1);
		
			TranspositionKey key=new TranspositionKey(length);
			Creature c=new Creature(t, key);
			result.add(c);
		}
		
		
		return result;

}

	@Override
	public List<Creature> generateCreaturesSubstititution(int numberOfCreatures,Text t) {
		List<Creature> result=new ArrayList<Creature>();
		for(int i=0;i<numberOfCreatures;i++){
		SubstitutionKey key=new SubstitutionKey(t.getLanguage());
			Creature c=new Creature(t, key);
			result.add(c);
		}
	
		
		return result;
	}
	
}
