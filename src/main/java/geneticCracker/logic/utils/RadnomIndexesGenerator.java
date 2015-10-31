package geneticCracker.logic.utils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class RadnomIndexesGenerator {



	public static List<Integer> generateRandomIndexes(int number, int range){
		Set<Integer> res=new LinkedHashSet<Integer>();
		int generated;
		do{
			generated =	ThreadLocalRandom.current().nextInt(0,range+1);
			res.add(generated);
		}while(res.size()<(number));
		return res.stream().collect(Collectors.toList());



	}
}
