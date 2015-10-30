package geneticCracker.logic.creatureMaker;

import java.util.List;

import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.text.Text;

public interface CreatureMaker {

List<Creature> generateCreaturesTransposition(int numberOfCreatures, int lengthKey, Text t);
List<Creature> generateCreaturesTransposition(int numberOfCreatures, Text t,int a, int b);
List<Creature> generateCreaturesSubstititution(int numberOfCreatures,Text t);
}
