package geneticCracker.logic.languageAnalyzer;

import java.util.TreeMap;

import geneticCracker.logic.ngramer.Ngramer;


public interface LanguageAnalyzer {


TreeMap<String, Integer> getGlobalFrequencyMap(String path, int length);


void setNgramer(Ngramer ngramerImpl);



}
