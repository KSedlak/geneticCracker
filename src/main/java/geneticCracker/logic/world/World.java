package geneticCracker.logic.world;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import geneticCracker.controller.FirstPageController;
import geneticCracker.logic.DNA.SubstitutionKey;
import geneticCracker.logic.Language.LanguageBean;
import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.creatureMaker.CreatureMaker;
import geneticCracker.logic.creatureMaker.Impl.CreatureMakerImpl;
import geneticCracker.logic.fitnesser.FitnessMaker;
import geneticCracker.logic.fitnesser.FitnesserOnlyFrequentWord;
import geneticCracker.logic.generationMaker.SimpleGenerationPasser;
import geneticCracker.logic.population.Population;
import geneticCracker.logic.text.Text;

@Component
public class World {

	List<Population> populations;

	@Autowired
	CreatureMaker creatureGenerator;

	@Autowired
	LanguageBean langs;

	Population populationSubstitionEnglish;

	@Autowired
	SimpleGenerationPasser generationManager;

	Text textToBreak;

	@Autowired
	FirstPageController controller;

	@Autowired
	FitnesserOnlyFrequentWord fitnesser;

	private int worldGeneration;

	public World() {
		populations = new ArrayList<Population>();
	}

	public Text getTextToBreak() {
		return textToBreak;
	}

	public void setTextToBreak(Text textToBreak) {
		this.textToBreak = textToBreak;
	}

	public void start(int size) {
		worldGeneration = 0;
		populationSubstitionEnglish = new Population();
		textToBreak.setLanguage(langs.getEnglish());
		populationSubstitionEnglish.setCreatures(creatureGenerator.generateCreaturesSubstititution(size, textToBreak));
		populations.add(populationSubstitionEnglish);
		markFirstPopulation();
		for(Creature c:populationSubstitionEnglish.getCreatures()){
			System.out.println(c.getMark());
		}

	}

	private void markFirstPopulation(){
		for(Creature c:populationSubstitionEnglish.getCreatures()){
			fitnesser.testCreatureInLife(c);
		}
	}
	
	public void generate() {

			populationSubstitionEnglish
					.setCreatures(generationManager.makeNewGeneration(populationSubstitionEnglish.getCreatures()));
			System.out.println(worldGeneration);
		worldGeneration=worldGeneration+1;
		
	}
	
	

	public int getWorldGeneration() {
		return worldGeneration;
	}

	public void setWorldGeneration(int worldGeneration) {
		this.worldGeneration = worldGeneration;
	}

	public List<Population> getPopulations() {
		return populations;
	}

	public void setPopulations(List<Population> populations) {
		this.populations = populations;
	}

	public Creature getBestCreatureOnWholeWorld() {

		List<Creature> bestInPopulations = new ArrayList<Creature>();

		for (Population p : populations) {
			bestInPopulations.add(p.getBestCreatureFromPopulation());
		}
		return bestInPopulations.stream().max((c1, c2) -> Double.compare(c1.getMark(), c2.getMark())).get();

	}

}
