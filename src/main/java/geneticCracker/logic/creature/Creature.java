package geneticCracker.logic.creature;

import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.text.Text;

public class Creature {
	private Text text;
	private Key dna;
	private Double mark;




	public Creature(Text text, Key dna) {
		super();
		this.text = text;
		this.dna = dna;
	}

	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public Key getDna() {
		return dna;
	}
	public void setDna(Key dna) {
		this.dna = dna;
	}
	public Double getMark() {
		return mark;
	}
	public void setMark(Double mark) {
		this.mark = mark;
	}







}
