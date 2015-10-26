package geneticCracker.logic.cryptModules.transpositionKeyGenerator;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.DNA.SubstitutionKey;
import geneticCracker.logic.DNA.TranspositionKey;
import geneticCracker.logic.Language.Language;
import geneticCracker.logic.Language.alphabet.Alphabet;
import geneticCracker.logic.cryptModules.substitution.crypter.SubstitiutionCrypter;
import geneticCracker.logic.cryptModules.substitution.crypter.Impl.SusbstitutionCrypterImpl;
import geneticCracker.logic.cryptModules.transpsitionCrypter.Impl.TranspositionCrypterImpl;
import geneticCracker.logic.text.Text;
public class SubstitionCrypterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCrypt() {
		//given
		String[] engAlphabet={"A","B","C","D","E","F","G","H","I","J","K","L",
				"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		Text t=new Text("ALA MA KOTA",new Language("eng", new Alphabet(engAlphabet)));

		String[] engAlphabetChanged={"B","A","C","D","E","F","G","H","I","J","K","L",
				"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		Key k = new SubstitutionKey(engAlphabetChanged);
        SubstitiutionCrypter crypter =new SusbstitutionCrypterImpl();
		//when
		crypter.crypt(t, k);
		//then
		Assert.assertEquals("BLB MB KOTB", t.getContentOfText());
	}


	@Test
	public void testDeCrypt() {
		//given
		String[] engAlphabet={"A","B","C","D","E","F","G","H","I","J","K","L",
				"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		Text t=new Text("BLB MB KOTB",new Language("eng", new Alphabet(engAlphabet)));

		String[] engAlphabetChanged={"B","A","C","D","E","F","G","H","I","J","K","L",
				"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		Key k = new SubstitutionKey(engAlphabetChanged);
        SubstitiutionCrypter crypter =new SusbstitutionCrypterImpl();
		//when
		crypter.decrypt(t, k);
		//then
		Assert.assertEquals("ALA MA KOTA", t.getContentOfText());
	}


}
