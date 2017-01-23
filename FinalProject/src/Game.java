import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;

public class Game extends JComponent{
	int correctS, correctW, correctR, pMovesX, pMovesY;
	private JTextArea text;
	boolean isCorrect=false;
	
	Suspect[] suspects = {new Suspect("Miss Scarlet"), new Suspect("Colonel Mustard"), new Suspect("Mrs. White"), new Suspect("Mr. Green"), new Suspect("Mrs. Peacock"), new Suspect("Professor Plum"), new Suspect("Mr. Boddy")};
	Weapon[] weapons = {new Weapon("Rope"), new Weapon("Revolver"), new Weapon("Wrench"), new Weapon("Knife"), new Weapon("Candlestick"), new Weapon("Lead Pipe")};
	Room[] rooms = {new Room("Study"), new Room("Hall"), new Room("Lounge"), new Room("Library"), new Room("Dining Room"), new Room("Billard Room"), new Room("Conservatory"), new Room("Ball Room"), new Room("Kitchen")};
	
	public Game(){
		
	}
	
	public Suspect getSuspect(int id){
		return suspects[id];
	}
	
	public Weapon getWeapon(int id){
		return weapons[id];
	}
	
	public Room getRoom(int id){
		return rooms[id];
	}
	
	public void setCorrect(int cS, int cW, int cR){
		correctS = cS;
		correctW = cW;
		correctR = cR;
		suspects[cS].setCorrect();
		weapons[cW].setCorrect();
		rooms[cR].setCorrect();
	}
	
	public JTextArea getText(){
		return text;
	}
	
	public void setPawnPosition(int x, int y){
		pMovesX = x;
		pMovesY = y;
	}
	
	public void makeGuess(){
		
	}
}