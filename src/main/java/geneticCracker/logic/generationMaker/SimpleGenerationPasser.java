package geneticCracker.logic.generationMaker;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.crosser.Impl.SimpleRandomCross;
import geneticCracker.logic.fitnesser.FitnessMaker;
import geneticCracker.logic.fitnesser.FitnesserOnlyFrequentWord;

@Service
public class SimpleGenerationPasser implements generationPasser{

	@Autowired
	SimpleRandomCross crosser;

	private Logger logger=Logger.getLogger(getClass());
	double percentElite=40;
	@Override
	public List<Creature> makeNewGeneration(List<Creature> old,  FitnessMaker fit) {

		logger.info("\nSTART making new generation");
			int size=old.size();

			int eliteNumberIndexes=(int) (old.size()*percentElite/100);

			int aP=0;
			int bP=0;
			do{
				do{
					aP=ThreadLocalRandom.current().nextInt(0,eliteNumberIndexes+1);
					bP=ThreadLocalRandom.current().nextInt(0,old.size());

					}while(aP==bP || old.get(aP).getDna().getKey().length!=old.get(bP).getDna().getKey().length);
				logger.info("\n get child from A:"+aP+" B:"+bP);
			old.addAll(crosser.makeChild(old.get(aP), old.get(bP),fit));

			}while(old.size()<(2*size));

			old.sort(new Comparator<Creature>() {
				@Override
				public int compare(Creature o1, Creature o2) {
					return Double.compare(o1.getMark(), o2.getMark());
				}
			});
			Collections.reverse(old);



			return old.stream().limit(size).collect(Collectors.toList());



		}

	}


