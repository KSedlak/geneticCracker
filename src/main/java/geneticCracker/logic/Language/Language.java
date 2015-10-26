package geneticCracker.logic.Language;

import java.util.HashMap;
import java.util.List;

import geneticCracker.logic.Language.alphabet.Alphabet;

public class Language {

	private String name;
	private Alphabet alphabet;
	private String [] uniqueChars;
	private HashMap<String, Integer> fitnessMap;
	private List<String> freqWord;




	public Language(String name, Alphabet alphabet) {
		super();
		this.name = name;
		this.alphabet = alphabet;
	}
	public String[] getUniqueChars() {
		return uniqueChars;
	}
	public void setUniqueChars(String[] uniqueChars) {
		this.uniqueChars = uniqueChars;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Alphabet getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(Alphabet alphabet) {
		this.alphabet = alphabet;
	}
	public HashMap<String, Integer> getFitnessMap() {
		return fitnessMap;
	}
	public void setFitnessMap(HashMap<String, Integer> fitnessMap) {
		this.fitnessMap = fitnessMap;
	}


}
