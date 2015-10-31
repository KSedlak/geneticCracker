package geneticCracker.logic.generationMaker;

import java.util.List;

import geneticCracker.logic.creature.Creature;

public interface generationPasser {
	List<Creature> makeNewGeneration(List<Creature> old);

}
