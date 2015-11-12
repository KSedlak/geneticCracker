import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import geneticCracker.logic.utils.RadnomIndexesGenerator;
import junit.framework.Assert;

public class RandomIndexesGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
	//given
	//when
		List<Integer> rg=RadnomIndexesGenerator.generateRandomIndexes(2,4);
	//then
		Assert.assertEquals(false, rg.get(0)==rg.get(1));
	}

}
