
public class Room {
	private String name;
	private int xCor, yCor, width, height;
	boolean isCorrect=false;
	
	public Room(String n, int x, int y, int w, int h){
		name = n;
		xCor = x;
		yCor = y;
		width = w;
		height = h;}
	
	public String getName(){
		return name;}
	
	public int getXCor(){
		return xCor;}
	
	public int getYCor(){
		return yCor;}
	
	public int getWidth(){
		return width;}
	
	public int getHeight(){
		return height;}
	
	public void setCorrect(){
		isCorrect=true;}
	
	public boolean getCorrect(){
		return isCorrect;}
}
