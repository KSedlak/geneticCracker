package geneticCracker.logic.ngramer.Impl;


import java.util.LinkedHashMap;


import org.springframework.stereotype.Component;

import geneticCracker.logic.ngramer.Ngramer;
@Component
public class NgramerImpl  implements Ngramer{

	@Override
	public LinkedHashMap<String, Integer> ngramText(String s, int length,LinkedHashMap<String, Integer> map) {
		String newline = System.getProperty("line.separator");
		String ngramValue="";
		for(int i=0;i<s.length();i++){
			ngramValue=ngramValue+s.charAt(i);

		

			if(ngramValue.length()==length){
				if(!ngramValue.contains(newline)){
				addToMap(ngramValue, map);}
				ngramValue="";
			}

		}

		return map;
	}




	public static String addToMap(String s,LinkedHashMap<String, Integer> map){

		if(map.containsKey(s)){
			int value=map.get(s);
			map.replace(s, value+1);
			return s;
		}
		map.put(s, 1);

		return s;
	}
	

}


