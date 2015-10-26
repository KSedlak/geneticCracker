package geneticCracker.logic.cryptModules.substitution.crypter.Impl;


import org.springframework.stereotype.Component;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.DNA.SubstitutionKey;
import geneticCracker.logic.Language.Language;
import geneticCracker.logic.cryptModules.substitution.crypter.SubstitiutionCrypter;
import geneticCracker.logic.text.Text;

@Component
public class SusbstitutionCrypterImpl implements SubstitiutionCrypter {

	private String[] usedKey;
	private BiMap<String, String> mapper;

	public SusbstitutionCrypterImpl() {
		super();

	}

	@Override
	public Text crypt(Text t, Key k) {

		generateKeyAndInitMapper(t.getLanguage(),k);
		String cryptedContent = "";
		String tempChar;
		String added;
		for (int i = 0; i < t.getContentOfText().length(); i++){
			tempChar=t.getContentOfText().charAt(i)+"";
			added=mapper.get(tempChar);
				if(added==null){
					added=" ";
				}
			cryptedContent = cryptedContent + added;
		}

		t.setContentOfText(cryptedContent);
		return t;
	}


	public void generateKeyAndInitMapper(Language l, Key k) {
		SubstitutionKey key= (SubstitutionKey)k;
		mapper = HashBiMap.create();
		usedKey = key.getKey();
		int idx = 0;
		for (String s : l.getAlphabet().getAlphabet()) {
			mapper.put(s, usedKey[idx]);
			idx++;
		}
	}

	@Override
	public Text decrypt(Text t, Key k) {
		generateKeyAndInitMapper(t.getLanguage(),k);
		BiMap<String, String> reversed = mapper.inverse();
		String decryptedContent = "";
		String tempChar;
		String added;
		for (int i = 0; i < t.getContentOfText().length(); i++) {
			tempChar=t.getContentOfText().charAt(i)+"";
			added=reversed.get(tempChar);
			if(added==null){
				added=" ";
			}
			decryptedContent = decryptedContent +added;
		}

		t.setContentOfText(decryptedContent);
		return t;

	}

}
