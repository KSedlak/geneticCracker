package geneticCracker.logic.cryptModules.substitution.crypter.Impl;


import org.apache.log4j.Logger;
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
	private Logger logger=Logger.getLogger(getClass());
	public SusbstitutionCrypterImpl() {
		super();

	}

	@Override
	public Text crypt(Text t, Key k) {

		generateKeyAndInitMapper((SubstitutionKey) k);
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
		mapper.clear();
		return t;
	}


	public void generateKeyAndInitMapper(SubstitutionKey k) {
		SubstitutionKey key= (SubstitutionKey)k;
		Language l=k.getLanguage();
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
		//logger.info("decrypt text by: "+k.getKeyString());
		generateKeyAndInitMapper((SubstitutionKey) k);
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



		return new Text(decryptedContent);

	}

}
