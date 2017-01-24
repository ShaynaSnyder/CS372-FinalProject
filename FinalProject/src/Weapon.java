
public class Weapon {
	private String name;
	boolean isCorrect=false;
	
	public Weapon(String n){
		name = n;}
	
	public String getName(){
		return name;}
	
	public void setCorrect(){
		isCorrect=true;}
	
	public boolean getCorrect(){
		return isCorrect;}
}
