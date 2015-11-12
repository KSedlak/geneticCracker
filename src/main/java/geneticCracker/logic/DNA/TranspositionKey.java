package geneticCracker.logic.DNA;

import java.util.Arrays;

import geneticCracker.logic.cryptModules.transpositionKeyGenerator.TranspositionKeyGenerator;



public class TranspositionKey implements Key {
	Integer[] key;


	public TranspositionKey(){}

	public TranspositionKey(String s) {
		super();

		String[] tab=s.split(",");
		key=new Integer[tab.length];
			for(int i=0;i<tab.length;i++){
				key[i]=Integer.parseInt(tab[i]);
		}


	}
	public TranspositionKey(Integer[] s) {
		super();
this.key=s;
		}




	public Integer[] getKey() {
		return key;
	}
	@Override
	public String getKeyString() {
		String buff="";
		for(Integer i:key){
			buff=buff+i+",";
		}
		return buff.substring(0, buff.length()-1);

	}
	public void setKey(Integer[] key) {
		this.key = key;
	}

	@Override
	public Key generateRandom(){
	int idx=0;
	int keyL=key.length;


	for(Integer i:TranspositionKeyGenerator.generateKey(keyL)){
		key[idx]=i;
		idx++;
	}
	return this;
	}


	public TranspositionKey(int length) {
		super();
		key=new	Integer[length];
		generateRandom();
	}

	public void setKey(Object[] key) {
		Integer[] stringArray = Arrays.copyOf(key, key.length, Integer[].class);
		this.key=stringArray;

	}

	@Override
	public Key getCopy() {

		return new TranspositionKey(this.key);
	}
}
