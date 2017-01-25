/**
 * This class models Suspect objects
 * @author ShaynaSnyder
 *
 */
public class Suspect {
	private String name;
	boolean isCorrect=false;
	
	/**
	 * Function creates a new Suspect object using a name
	 * @param String name
	 */
	public Suspect(String n){
		name = n;}
	
	/**
	 * Function gets the Suspect object's name
	 * @return String name
	 */
	public String getName(){
		return name;}
	
	/**
	 * Function sets whether or not the Suspect object is correct
	 */
	public void setCorrect(){
		isCorrect=true;}
	
	/**
	 * Function returns whether or not the Suspect object is a part of the correct guess
	 * @return boolean isCorrect
	 */
	public boolean getCorrect(){
		return isCorrect;}
}
