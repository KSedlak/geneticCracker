package geneticCracker.logic.markerDB;

import java.util.HashMap;


import org.springframework.stereotype.Service;

@Service("markerDB")
public class MarkerDBImpl implements markerDB{


	HashMap<String, Double> map;


	private MarkerDBImpl(){
		map=new HashMap<String, Double>();
	}

	@Override
	public Double alreadyChecked(String t) {
		if(map.containsKey(t)){
			return map.get(t);
		}

		return null;
	}

	@Override
	public void addToMap(String s, Double d){

		map.put(s, d);


	}



}
