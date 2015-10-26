package geneticCracker.logic.cryptModules;

import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.text.Text;

public interface Crypter {
	Text crypt(Text t, Key k);
	Text decrypt(Text t, Key k);
}
