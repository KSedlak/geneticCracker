package geneticCracker.logic.generationMaker;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.crosser.Impl.SimpleRandomCross;
import geneticCracker.logic.fitnesser.FitnesserOnlyFrequentWord;

@Service
public class SimpleGenerationPasser implements generationPasser{

	@Autowired
	SimpleRandomCross crosser;


	double percentElite=40;
	@Override
	public List<Creature> makeNewGeneration(List<Creature> old) {


			int size=old.size();

			int eliteNumberIndexes=(int) (old.size()*percentElite/100);

			do{
			Creature good=old.get(ThreadLocalRandom.current().nextInt(0,eliteNumberIndexes+1));
			Creature second=old.get(ThreadLocalRandom.current().nextInt(0,old.size()));

			old.addAll(crosser.makeChild(good, second));

			}while(old.size()<(2*size));

			old.sort(new Comparator<Creature>() {
				@Override
				public int compare(Creature o1, Creature o2) {
					return Double.compare(o1.getMark(), o2.getMark());
				}
			});



			return old.stream().limit(size).collect(Collectors.toList());



		}
	}


