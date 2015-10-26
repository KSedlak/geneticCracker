package geneticCracker.logic.cryptModules.transpsitionCrypter.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.DNA.TranspositionKey;
import geneticCracker.logic.cryptModules.transpsitionCrypter.TranspositionCrypter;
import geneticCracker.logic.text.Text;

@Component
public class TranspositionCrypterImpl implements TranspositionCrypter {

	private List<String>  cryptingTable;
	private 	TranspositionKey key;

	@Override
	public Text crypt(Text t, Key k) {

		String result="";
		key=(TranspositionKey) k;
		initCryptTable(key, t);
		for(String s:cryptingTable){
			for(Integer n:key.getKey()){
				result=result+s.charAt(n-1);
			}
		}

		t.setContentOfText(result);

		return t;
	}

	private void initCryptTable(TranspositionKey k, Text t){
		cryptingTable=new ArrayList<String>();


		Integer[] keyV=k.getKey();
		String subs="";

		int idx=0;
		do{
			subs=t.getContentOfText().substring(idx, idx+keyV.length);
			cryptingTable.add(subs);
			idx=idx+keyV.length;
		}while((idx+keyV.length)<t.getContentOfText().length());

		String lastLine=t.getContentOfText().substring(idx,t.getContentOfText().length());

		while(lastLine.length()<keyV.length){
			lastLine=lastLine+" ";
		}
		cryptingTable.add(lastLine);

	}

	@Override
	public Text decrypt(Text t, Key k) {

		String result="";
		key=(TranspositionKey) k;
		initCryptTable(key, t);

		char[] temp =new char[key.getKey().length];
		int idx=0;
		String tempString;
		for(String s:cryptingTable){
			idx=0;
				for(Integer n:key.getKey()){
					temp[n-1]=s.charAt(idx);
					idx++;
				}
			tempString="";
			for(char ch:temp){
				tempString=tempString+ch;
			}
			result=result+tempString;
	}


		t.setContentOfText(result.trim());

		return t;
	}

	public List<String> getCryptingTable() {
		return cryptingTable;
	}

	public void setCryptingTable(List<String> cryptingTable) {
		this.cryptingTable = cryptingTable;
	}

	public TranspositionKey getKey() {
		return key;
	}

	public void setKey(TranspositionKey key) {
		this.key = key;
	}

}
