package geneticCracker.logic.text;



import geneticCracker.logic.Language.Language;
public class Text {



	private String contentOfText;
	private Language language;


	public Text(String contentOfText, Language l) {
		super();
		this.contentOfText = contentOfText;
		language=l;

	}
	public Text(String contentOfText) {
		super();
		this.contentOfText = contentOfText;
	}

	public String getContentOfText() {
		return contentOfText;
	}
	public void setContentOfText(String contentOfText) {
		this.contentOfText = contentOfText;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}





}
