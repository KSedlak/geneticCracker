package geneticCracker.logic.ngramer.Impl;


import java.util.LinkedHashMap;


import org.springframework.stereotype.Component;

import geneticCracker.logic.ngramer.Ngramer;
@Component
public class NgramerImpl  implements Ngramer{

	@Override
	public LinkedHashMap<String, Integer> ngramText(String s, int length,LinkedHashMap<String, Integer> map) {

		String ngramValue="";
		int lastidx=s.length()-length;
		int idx=0;
		do{
		for(int i=idx;i<idx+length;i++){
			ngramValue=ngramValue+s.charAt(i);
		}
		addToMap(ngramValue, map);
		ngramValue="";
		idx++;
		}while(idx<=lastidx);

		return map;
	}




	public static String addToMap(String s,LinkedHashMap<String, Integer> map){

		if(map.containsKey(s)){
			int value=map.get(s);
			map.replace(s, value+1);
			return s;
		}
		map.put(s.toUpperCase(), 1);

		return s;
	}


}


