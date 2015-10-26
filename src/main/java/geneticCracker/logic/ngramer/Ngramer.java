package geneticCracker.logic.ngramer;

import java.util.TreeMap;


public interface Ngramer {

TreeMap<String, Integer> ngramText(String s, int length, TreeMap<String, Integer> map);

}
