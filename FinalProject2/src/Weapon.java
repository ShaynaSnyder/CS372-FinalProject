/**
 * This class models Weapon objects
 * @author ShaynaSnyder
 *
 */
public class Weapon {
	private String name;
	boolean isCorrect=false;
	
	/**
	 * Function creates a Weapon objects using a name
	 * @param String name
	 */
	public Weapon(String n){
		name = n;}
	
	/**
	 * Function gets the Weapon object's name
	 * @return String name
	 */
	public String getName(){
		return name;}
	
	/**
	 * Function sets whether or not the Weapon object is correct
	 */
	public void setCorrect(){
		isCorrect=true;}
	
	/**
	 * Function returns whether or not the Weapon object is a part of the correct guess
	 * @return boolean isCorrect
	 */
	public boolean getCorrect(){
		return isCorrect;}
}