package geneticCracker.logic.textReader;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class fileToStringTest {

	@Test
	public void test() {
		//given
		String file ="BUT I MUST EXPLAIN TO YOU HOW ALL THIS MISTAKEN IDEA OF DENOUNCING PLEASURE AND PRAISING PAIN WAS BORN AND I WILL";

		//when
		String readed = fileToString.getFileFromDirectory("text/subject.txt");
		//then
		Assert.assertTrue(readed.contains(file));
	}

}
