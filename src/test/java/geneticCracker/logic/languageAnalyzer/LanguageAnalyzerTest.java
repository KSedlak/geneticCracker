package geneticCracker.logic.languageAnalyzer;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import geneticCracker.logic.languageAnalyzer.Impl.LanguageAnalyzerImpl;
import geneticCracker.logic.ngramer.Impl.NgramerImpl;

public class LanguageAnalyzerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
			//Given
		LanguageAnalyzer analyzer=new LanguageAnalyzerImpl();
		analyzer.setNgramer(new NgramerImpl());
			//when
		LinkedHashMap<String, Integer> map=	analyzer.getGlobalFrequencyMap("text/learn/eng/txt", 3);
	List<String> mostCommonNgrams=map.entrySet().stream()
    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
    .limit(50)
    .map(ent->ent.getKey())
    .collect(Collectors.toList());
			//Then
		Assert.assertEquals("he ",mostCommonNgrams.get(0) );
	}

}
