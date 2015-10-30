package geneticCracker.logic.crossover;

import geneticCracker.logic.creature.Creature;

public interface Crossover {
	
Creature crossover(Creature a, Creature b);
}
