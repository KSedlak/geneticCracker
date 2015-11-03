package geneticCracker.logic.crosser;

import java.util.List;


import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.fitnesser.FitnessMaker;

public interface Crosser {


List<Creature> makeChild(Creature a, Creature b,  FitnessMaker fit);





}
