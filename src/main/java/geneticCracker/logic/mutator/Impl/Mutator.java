package geneticCracker.logic.mutator.Impl;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.utils.RadnomIndexesGenerator;

@Service
public class Mutator  implements geneticCracker.logic.mutator.Mutator{

	double percent=30;

	double percentBound=10;

	@Override
	public Creature mutate(Creature c) {

		if(ThreadLocalRandom.current().nextInt(0,101)<percentBound){;


		Key dna=c.getDna();

		Object[] table=dna.getKey();
		int numberToMutate=(int)((percent*table.length)/100);
		List<Integer> listIndexes =RadnomIndexesGenerator.generateRandomIndexes(numberToMutate, table.length-1);



			for(int i=0; i<listIndexes.size()-1;i++){

				Object a=table[listIndexes.get(i)];
				Object b=table[listIndexes.get(i+1)];

				table[listIndexes.get(i)]=b;
				table[listIndexes.get(i+1)]=a;

			i++;
			}


		dna.setKey(table);
		c.setDna(dna);
		}

		return c;
	}


}
