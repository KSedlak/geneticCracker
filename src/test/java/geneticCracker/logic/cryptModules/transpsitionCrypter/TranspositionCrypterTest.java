package geneticCracker.logic.cryptModules.transpsitionCrypter;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.DNA.TranspositionKey;
import geneticCracker.logic.cryptModules.transpsitionCrypter.Impl.TranspositionCrypterImpl;
import geneticCracker.logic.text.Text;
public class TranspositionCrypterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCrypt() {
		//given
		Text t=new Text("ALA MA KOTA");
		Key k = new TranspositionKey("5,4,3,2,1");
		TranspositionCrypter crypter=new TranspositionCrypterImpl();
		//when
		crypter.crypt(t, k);
		//then
		Assert.assertEquals("M ALATOK A    A", t.getContentOfText());
	}

	@Test
	public void testDeCrypt() {
		//given
		Text t=new Text("M ALATOK A    A");
		Key k = new TranspositionKey("5,4,3,2,1");
		TranspositionCrypter crypter=new TranspositionCrypterImpl();
		//when
		crypter.decrypt(t, k);
		//then
		Assert.assertEquals("ALA MA KOTA", t.getContentOfText());
	}

}
