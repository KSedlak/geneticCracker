package geneticCracker.logic.DNA;

public interface Key {

	public Key generateRandom();
	public Object[] getKey();
	public void setKey(Object[] key);
	public Key getCopy();
}
