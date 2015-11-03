package geneticCracker.logic.creature;

import java.util.TreeMap;

import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.text.Text;

public class Creature implements Comparable<Creature> {
	private Text text;
	private Key dna;
	private Double mark;
	private TreeMap<String,Integer> points;
	private String Originalmsg;



	public Creature(Text text, Key dna) {
		super();
		this.text = text;
		Originalmsg=text.getContentOfText();
		this.dna = dna;
		points=new TreeMap<String,Integer>();
	}

	public TreeMap<String, Integer> getPoints() {
		return points;
	}

	public void setPoints(TreeMap<String, Integer> points) {
		this.points = points;
	}

	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	
	public String getOriginalmsg() {
		return Originalmsg;
	}

	public void setOriginalmsg(String originalmsg) {
		Originalmsg = originalmsg;
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

	@Override
	public int compareTo(Creature o) {
		return Double.compare(this.mark, o.getMark());
	}







}
