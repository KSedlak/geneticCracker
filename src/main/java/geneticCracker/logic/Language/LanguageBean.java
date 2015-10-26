package geneticCracker.logic.Language;

import java.util.List;

import org.springframework.stereotype.Component;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;

import geneticCracker.logic.Language.alphabet.Alphabet;

@Component
public class LanguageBean {

	private Language polish;
	private Language english;



	public LanguageBean() {
		super();
		String[] engAlphabet={"A","B","C","D","E","F","G","H","I","J","K","L",
				"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

		english=new Language("english",new Alphabet(engAlphabet));

		String[] plAlphabet={"A","B","C","D","E","F","G","H","I","J","K","L",
				"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

		polish=new Language("polish",new Alphabet(plAlphabet));




	}



	public Language getPolish() {
		return polish;
	}



	public Language getEnglish() {
		return english;
	}


	public Language getLanguageFromString(String s){

		String detected=detect(s);
		if(detected.equals("en")){
		return english;
		}
		if(detected.equals("pl")){
			return polish;
			}
		return english;
	}

	public  String detect(String s){
	List<Result> results = null;
	try {
		results = DetectLanguage.detect(s);
	} catch (APIError e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	Result result = results.get(0);
	return result.language;
	}





}
