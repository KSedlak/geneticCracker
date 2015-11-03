package geneticCracker.logic.generationMaker;

import java.util.List;

import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.fitnesser.FitnessMaker;

public interface generationPasser {
	List<Creature> makeNewGeneration(List<Creature> old,  FitnessMaker fit);

}
