package geneticCracker.logic.cryptModules.transpositionKeyGenerator;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;



@Service
public class TranspositionKeyGenerator{

	public  static Set<Integer> generateKey(int length) {
		Set<Integer> result = new LinkedHashSet<Integer>();
		do{
		int generated =	ThreadLocalRandom.current().nextInt(1,length+1);
		result.add(generated);
		}while(result.size()<(length));

		return result;
	}


}
