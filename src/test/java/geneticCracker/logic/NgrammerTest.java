package geneticCracker.logic;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

import geneticCracker.logic.ngramer.Ngramer;
import geneticCracker.logic.ngramer.Impl.NgramerImpl;

public class NgrammerTest {

	@Test
	public void test() {
	//given
		String test="ALA MA KOTA I SIEDZI NA DRZEWIE";
		Ngramer ng=new NgramerImpl();

		//when
		LinkedHashMap<String, Integer> tes=new LinkedHashMap<String, Integer>();
		ng.ngramText(test, 3,tes);
		//Then
		for(String x:tes.keySet()){
			System.out.println(x+" "+tes.get(x));
		}
	}

}
