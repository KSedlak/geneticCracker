package geneticCracker.logic.languageAnalyzer;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;


import geneticCracker.logic.ngramer.Ngramer;


public interface LanguageAnalyzer {


LinkedHashMap<String, Integer> getGlobalFrequencyMap(String path, int length);


void setNgramer(Ngramer ngramerImpl);


List<String> getNmostFrequentNgrams(String path, int lengthNgram, int amount);


List<String> getMostFrequentWords(String lang,int limit) throws IOException;


}
