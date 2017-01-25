/**
 * This class models Room objects
 * @author ShaynaSnyder
 *
 */
public class Room {
	private String name;
	private int xCor, yCor, width, height;
	boolean isCorrect=false;
	
	/**
	 * Function creates a Room object using a name, coordinates, and dimensions
	 * @param String name
	 * @param int x-Coordinate
	 * @param int y-Coordinate
	 * @param int width
	 * @param int height
	 */
	public Room(String n, int x, int y, int w, int h){
		name = n;
		xCor = x;
		yCor = y;
		width = w;
		height = h;}
	
	/**
	 * Function gets Room object's name
	 * @return String name
	 */
	public String getName(){
		return name;}
	
	/**
	 * Function gets Room object's x-coordinate
	 * @return int xCor
	 */
	public int getXCor(){
		return xCor;}
	
	/**
	 * Function gets Room object's y-coordinate
	 * @return int yCor
	 */
	public int getYCor(){
		return yCor;}
	
	/**
	 * Function gets Room object's width
	 * @return int width
	 */
	public int getWidth(){
		return width;}
	
	/**
	 * Function gets Room object's height
	 * @return int height
	 */
	public int getHeight(){
		return height;}
	
	/**
	 * Function sets whether or not the Room object is correct
	 */
	public void setCorrect(){
		isCorrect=true;}
	
	/**
	 * Function returns whether or not the Room object is a part of the correct guess
	 * @return boolean isCorrect
	 */
	public boolean getCorrect(){
		return isCorrect;}
}
