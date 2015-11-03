package geneticCracker.logic.crosser.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.crosser.Crosser;
import geneticCracker.logic.fitnesser.FitnessMaker;
import geneticCracker.logic.fitnesser.FitnesserOnlyFrequentWord;
import geneticCracker.logic.mutator.Mutator;
import geneticCracker.logic.utils.RadnomIndexesGenerator;

@Service
public class SimpleRandomCross implements Crosser {



	double crossPoints=40;


	@Autowired
	Mutator mutator;

	@Override
	public List<Creature> makeChild(Creature a, Creature b, FitnessMaker fit){
		List<Creature> childs=new ArrayList<Creature>();
		int dnaLength=b.getDna().getKey().length;
		int cr=(int) (crossPoints*dnaLength/100);

			List<Integer> indexes=RadnomIndexesGenerator.
					generateRandomIndexes(ThreadLocalRandom.current().nextInt(1,dnaLength),dnaLength);

	Key dnaA=a.getDna().getCopy();
	Key dnaB=a.getDna().getCopy();

	Object[] Atable=dnaA.getKey();
	Object[] Btable=dnaB.getKey();

	for(int i=0; i<indexes.size();i++){

		Object aTemp=Atable[i];
		Object bTemp=Btable[i];

		Atable[i]=bTemp;
		Btable[i]=aTemp;

	}
	dnaA.setKey(Atable);
	dnaB.setKey(Btable);

		Creature first=new Creature(a.getText(),dnaA);
		Creature second=new Creature(a.getText(),dnaB);

		childs.add(first);
		childs.add(second);

		for(Creature c:childs){
			mutator.mutate(c);
			fit.testCreatureInLife(c);
		}


		return childs;



	}








}
