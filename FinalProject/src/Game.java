import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;

public class Game extends JComponent implements MouseListener{
	Image iBoard, iSuspects1, iSuspects2, iPawn, iArrows;
	int correctS, correctW, correctR, clickX, clickY, mouseX, mouseY;
	int pawnX=445, pawnY=0, pMovesX=0, pMovesY=-1, guessN=0;
	private JTextArea text, text2;
	boolean isCorrect=false, guess1, guess2;
	JFrame frame;
	Suspect sGuess;
	Weapon wGuess;
	Room rGuess;
	
	Suspect[] suspects = {new Suspect("Miss Scarlet"), new Suspect("Colonel Mustard"), new Suspect("Mrs. White"), new Suspect("Mr. Green"), new Suspect("Mrs. Peacock"), new Suspect("Professor Plum"), new Suspect("Mr. Boddy")};
	Weapon[] weapons = {new Weapon("Rope"), new Weapon("Revolver"), new Weapon("Wrench"), new Weapon("Knife"), new Weapon("Candlestick"), new Weapon("Lead Pipe")};
	Room[] rooms = {new Room("Study"), new Room("Hall"), new Room("Lounge"), new Room("Library"), new Room("Dining Room"), new Room("Billard Room"), new Room("Conservatory"), new Room("Ball Room"), new Room("Kitchen")};
	
	public Game(Image iB, Image iS1, Image iS2, Image iP, Image iA){
		guess1=false;
		guess2=false;
		text = new JTextArea();
		text2 = new JTextArea();
		text.setBounds(705, 10, 290, 100);
		text2.setBounds(705, 110, 290, 150);
		text.setLineWrap(true);
		text2.setLineWrap(true);
		text.setWrapStyleWord(true);
		text2.setWrapStyleWord(true);
		text.setFont(new Font("Serif",Font.BOLD,18));
		text2.setFont(new Font("Serif",Font.PLAIN, 16));
		add(text);
		add(text2);
		text.setEditable(false);
		iBoard = iB;
		iSuspects1 = iS1;
		iSuspects2 = iS2;
		iPawn = iP;
		iArrows = iA;
		addMouseListener(this);
		Random rnd = new Random();
		correctS = rnd.nextInt(7);
		correctW = rnd.nextInt(6);
		correctR = rnd.nextInt(9);
		suspects[correctS].setCorrect();
		weapons[correctW].setCorrect();
		rooms[correctR].setCorrect();}
	
	public void promptGuess(Room r){
		rGuess = r;
		text.setText(String.format("Make a guess in the %s.", r.getName()));
		guess1=true;}
	
	public void guessSuspect(Suspect s){
		guessN+=1;
		text2.setText(String.format("Guess #%d\nRoom: %s\nSuspect: %s", guessN, rGuess.getName(), sGuess.getName()));
		guess2=true;
		guess1=false;
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(iBoard, 0, 10, this);
		g2.drawImage(iSuspects1, 5, 560, this);
		g2.drawImage(iSuspects2, 405, 560, this);
		g2.drawImage(iPawn, pawnX, pawnY, this);
		g2.drawImage(iArrows, 710, 410, this);
		if(text!=null)
			text.repaint();
		if(text2!=null)
			text2.repaint();}
	
	public void mouseClicked(MouseEvent e){
		clickX = e.getX();
		clickY = e.getY();
		
		if(guess1==false){
			//left, right, and down arrows
			if(clickY>=460 && clickY<=505 && clickX<=830){
				//right arrow
				if(clickX>=793 && pMovesY>=0){
					rightArrow();}
				//down arrow
				else if(clickX>=755 && clickX<=793){
					downArrow();}
				//left arrow
				else if(clickX>712 && pMovesY>=0){
					leftArrow();}
				else
					text.setText("You can't do that.");}
			//up arrow
			else if((clickY>=410 && clickY<=460) && (clickX>=755 && clickX<=793)){
				upArrow();}
			repaint();}
		
		//study
		if(pMovesX<=-10 && pMovesY<=2){
			promptGuess(rooms[0]);}
		//hall
		else if(pMovesX>=-7 && pMovesX<=-2 && pMovesY<=5){
			promptGuess(rooms[1]);}
		//lounge
		else if(pMovesX>=1 && pMovesY<=4){
			promptGuess(rooms[2]);}
		
		if(guess1==true){
			//scarlet
			if(clickX<100 && clickY>560){
				sGuess = suspects[0];}
			guessSuspect(sGuess);}
		repaint();}
	
	public void leftArrow(){
		if(pMovesX>-15){
			pawnX-=26;
			pMovesX-=1;
			text.setText(null);}
		else
			text.setText("You can't do that.");}
	
	public void rightArrow(){
		if(pMovesX<6){
			pawnX+=26;
			pMovesX+=1;
			text.setText(null);}
		else
			text.setText("You can't do that.");}
	
	public void downArrow(){
		if(pMovesY<23){
			pawnY+=21;
			pMovesY+=1;
			text.setText(null);}
		else
			text.setText("You can't do that.");}
	
	public void upArrow(){
		if(pMovesY>0){
			pawnY-=21;
			pMovesY-=1;
			text.setText(null);}
		else
			text.setText("You can't do that.");}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}