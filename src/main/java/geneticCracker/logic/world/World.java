package geneticCracker.logic.world;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import geneticCracker.logic.DNA.SubstitutionKey;
import geneticCracker.logic.Language.LanguageBean;
import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.creatureMaker.CreatureMaker;
import geneticCracker.logic.creatureMaker.Impl.CreatureMakerImpl;
import geneticCracker.logic.fitnesser.FitnessMaker;
import geneticCracker.logic.population.Population;
import geneticCracker.logic.text.Text;

@Component
public class World {
	
List<Population> populations;

@Autowired
CreatureMaker creatureGenerator;

@Autowired
LanguageBean langs;

@Autowired
FitnessMaker fitneser;

Text textToBreak;




public World(){
	populations=new ArrayList<Population>();
}

public Text getTextToBreak() {
	return textToBreak;
}

public void setTextToBreak(Text textToBreak) {
	this.textToBreak = textToBreak;
}

public void start(int size){
	
Population populationSubstitionEnglish=new Population();
textToBreak.setLanguage(langs.getEnglish());
populationSubstitionEnglish.setCreatures(creatureGenerator.generateCreaturesSubstititution(size, textToBreak));


for(Creature c:populationSubstitionEnglish.getCreatures()){
	SubstitutionKey k=(SubstitutionKey)c.getDna();
	fitneser.testCreatureInLife(c);
	System.out.println(k.getKeyString()+" "+c.getMark());
}
	
	
}




public List<Population> getPopulations() {
	return populations;
}

public void setPopulations(List<Population> populations) {
	this.populations = populations;
}



}
