package geneticCracker.logic.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import geneticCracker.controller.FirstPageController;
import geneticCracker.logic.Language.LanguageBean;
import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.creatureMaker.CreatureMaker;
import geneticCracker.logic.fitnesser.FitnessMaker;
import geneticCracker.logic.fitnesser.FitnesserOnlyFrequentWord;
import geneticCracker.logic.fitnesser.FitnesserOnlyNgrams;
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


	

	private FitnessMaker fitnesMaker;
	
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

	public void start(int size, FitnessMaker maker) {
		worldGeneration = 0;
		fitnesMaker=maker;
		populationSubstitionEnglish = new Population();
		textToBreak.setLanguage(langs.getEnglish());
		populationSubstitionEnglish.setCreatures(creatureGenerator.generateCreaturesSubstititution(size, textToBreak));
		populations.add(populationSubstitionEnglish);
		markFirstPopulation();
	
	

	}

	private void markFirstPopulation(){
		Creature c;
		for(int i=0;i<populationSubstitionEnglish.getCreatures().size();i++){
			c=populationSubstitionEnglish.getCreatures().get(i);
		fitnesMaker.testCreatureInLife(c);
		}
		Collections.sort(populationSubstitionEnglish.getCreatures());
		Collections.reverse(populationSubstitionEnglish.getCreatures());
	}
	
	public void generate() {

			populationSubstitionEnglish
					.setCreatures(generationManager.makeNewGeneration(populationSubstitionEnglish.getCreatures(),fitnesMaker));
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
