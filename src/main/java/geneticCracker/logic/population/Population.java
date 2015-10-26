package geneticCracker.logic.population;

import java.util.List;

import geneticCracker.logic.creature.Creature;

public class Population {

private int generation;
private List<Creature> creatures;




public Population() {
	super();
}
public int getGeneration() {
	return generation;
}
public void setGeneration(int generation) {
	this.generation = generation;
}
public List<Creature> getCreatures() {
	return creatures;
}
public void setCreatures(List<Creature> creatures) {
	this.creatures = creatures;
}





}