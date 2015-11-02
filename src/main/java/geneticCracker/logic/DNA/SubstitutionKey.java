package geneticCracker.logic.DNA;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import geneticCracker.logic.Language.Language;

public class SubstitutionKey implements Key{
	String[] key;

	Language language;


	public SubstitutionKey(String[] key) {
		super();
		this.key = key;
	}
	public SubstitutionKey(Language l) {
		super();
		this.language=l;
		generateRandom();
	}
	public SubstitutionKey(String[] key,Language l) {
		super();
		this.language=l;
		this.key = key;
	}
	@Override
	public String getKeyString() {
		String buff="";
		for(String i:key){
			buff=buff+i+",";
		}
		return buff.substring(0, buff.length()-1);

	}

	public SubstitutionKey() {
		super();
		generateRandom();
	}


	@Override
	public Key generateRandom() {
		key=getRandomVariation(language.getAlphabet().getAlphabet());
		return this;
	}


	public Language getLanguage() {
		return language;
	}


	public void setLanguage(Language language) {
		this.language = language;
	}


	public String[] getKey() {
		return key;
	}


	public void setKey(String[] key) {
		this.key = key;
	}

	public void setKey(Object[] key) {
		this.key = (String[]) key;
	}
	public String[] getRandomVariation(String [] alphabet){
		String[] mixed = new String[alphabet.length];
		Set<Integer> result = new LinkedHashSet<Integer>();
		do{
		int generated =	ThreadLocalRandom.current().nextInt(0,alphabet.length);
		result.add(generated);
		}while(result.size()<(alphabet.length));

		int idx=0;
		for(Integer n:result){
			mixed[idx]=alphabet[n];
			idx++;
		}
		return mixed;

	}
	@Override
	public Key getCopy() {
		return new SubstitutionKey(this.getKey(), this.getLanguage());
	}

}
