package geneticCracker.logic.cryptModules.transpositionKeyGenerator;


import java.util.Set;

import org.junit.Assert;
import org.junit.Test;




public class TranspositionKeyGeneratorTest {

	@Test
	public void test() {
		//given

		//when
	Set<Integer> result=TranspositionKeyGenerator.generateKey(5);
		//then
	Assert.assertEquals(5, result.size());
	Assert.assertTrue(result.contains(1));
	Assert.assertTrue(result.contains(2));
	Assert.assertTrue(result.contains(3));
	Assert.assertTrue(result.contains(4));
	Assert.assertTrue(result.contains(5));
	}

}
