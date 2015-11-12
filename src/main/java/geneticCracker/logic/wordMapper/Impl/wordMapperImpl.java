package geneticCracker.logic.wordMapper.Impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import geneticCracker.logic.wordMapper.WordMapper;


@Service
public class wordMapperImpl implements WordMapper {



	@Override
	public LinkedHashMap<String, Integer> wordMappingText(String s) {

	LinkedHashMap<String,Integer> res=new LinkedHashMap<String,Integer>();
	String[] tab=s.split(" ");
	for(String word:tab){
		addToMap(word, res);
	}
		return res;
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
