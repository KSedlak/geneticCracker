package geneticCracker.logic.ngramer;

import java.util.LinkedHashMap;
import java.util.TreeMap;


public interface Ngramer {

LinkedHashMap<String, Integer> ngramText(String s, int length, LinkedHashMap<String, Integer> map);

}
