import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.*;
import javax.swing.*;

public class Game extends JComponent implements MouseListener{
	Image iBoard, iSuspects1, iSuspects2, iWeapons, iPawn, iArrows, currentD;
	ArrayList<Image> dice = new ArrayList<Image>();
	int correctS, correctW, correctR, clickX, clickY, mouseX, mouseY;
	int pawnX=445, pawnY=0, pMovesX=0, pMovesY=-1, guessN=1;
	//1:left 2:down 3:right 4:up
	int lastArrow=0, nextArrow=0;
	private JTextArea text, text2;
	boolean isCorrect=false, guess1, guess2;
	JFrame frame;
	Suspect sGuess;
	Weapon wGuess;
	Room rGuess;
	Thread t = new Thread();
    Roll roll = new Roll(dice);
	
	Suspect[] suspects = {new Suspect("Miss Scarlet"), new Suspect("Colonel Mustard"), new Suspect("Mrs. White"), new Suspect("Mr. Green"), new Suspect("Mrs. Peacock"), new Suspect("Professor Plum"), new Suspect("Mr. Boddy")};
	Weapon[] weapons = {new Weapon("Wrench"), new Weapon("Candlestick"), new Weapon("Lead Pipe"), new Weapon("Rope"), new Weapon("Revolver"), new Weapon("Knife")};
	Room[] rooms = {new Room("Study",-15,0,6,3), new Room("Hall",-7,0,6,6), new Room("Lounge",1,0,6,5), new Room("Library",-15,5,5,5), new Room("Dining Room",0,8,7,6), new Room("Billard Room",-15,11,5,5), new Room("Conservatory",-15,18,4,4), new Room("Ball Room",-8,16,8,6), new Room("Kitchen",2,17,5,5)};
	
	public Game(Image iB, Image iS1, Image iS2, Image iW, Image iP, Image iA, Image d1, Image d2, Image d3, Image d4, Image d5, Image d6){
		guess1=false;
		guess2=false;
		dice.add(d1);
		dice.add(d2);
		dice.add(d3);
		dice.add(d4);
		dice.add(d5);
		dice.add(d6);
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
		iWeapons = iW;
		iPawn = iP;
		iArrows = iA;
		addMouseListener(this);
		Random rnd = new Random();
		correctS = rnd.nextInt(7);
		correctW = rnd.nextInt(6);
		correctR = rnd.nextInt(9);
		suspects[correctS].setCorrect();
		weapons[correctW].setCorrect();
		rooms[correctR].setCorrect();
		currentD = dice.get(0);}
	
	class Roll implements Runnable{
		ArrayList<Image> dice = new ArrayList<Image>();
		Random rand = new Random();
		public Roll(ArrayList<Image> imgs){
			dice = imgs;}
		/**
		 * run function sets icons equal to a random image of a dice side
		 */
		public void run(){
			for(int x=0; x<5; x++){
				int temp = rand.nextInt(6);
				currentD = (dice.get(temp));
				repaint();
				try{Thread.sleep(1000);}
				catch (InterruptedException ex){;}}}}
	
	public void promptGuess(Room r){
		rGuess = r;
		text.setText(String.format("Make a guess in the %s.\nSelect a suspect.\nThen select a weapon.", r.getName()));
		text2.setText(String.format("Guess #%d\nRoom: %s\nSuspect:\nWeapon:", guessN, rGuess.getName()));
		if(guess2==false)
			guess1=true;}
	
	public void guessSuspect(Suspect s){
		text2.setText(String.format("Guess #%d\nRoom: %s\nSuspect: %s\nWeapon:", guessN, rGuess.getName(), sGuess.getName()));
		guess2=true;
		guess1=false;}
	
	public void guessWeapon(Weapon w){
		text2.setText(String.format("Guess #%d\nRoom: %s\nSuspect: %s\nWeapon: %s", guessN, rGuess.getName(), sGuess.getName(), wGuess.getName()));
		checkGuess();
		guessN+=1;
		guess2=false;
		if(lastArrow==1 || lastArrow==2)
			nextArrow=lastArrow+2;
		else if(lastArrow==3 || lastArrow==4)
			nextArrow=lastArrow-2;}
	
	public void checkGuess(){
		int correct=0;
		if(rGuess.getCorrect()==true)
			correct+=1;
		if(sGuess.getCorrect()==true)
			correct+=1;
		if(wGuess.getCorrect()==true)
			correct+=1;
		if(correct==3){
			text.setText("Congratulations! You WIN!");}
		else
			text.setText(String.format("Guess #%d\n%d part(s) correct\n%d part(s) incorrect", guessN, correct, 3-correct));}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(iBoard, 0, 10, this);
		g2.drawImage(iSuspects1, 5, 570, this);
		g2.drawImage(iSuspects2, 405, 570, this);
		g2.drawImage(iWeapons, 705, 440, this);
		g2.drawImage(iPawn, pawnX, pawnY, this);
		g2.drawImage(iArrows, 710, 300, this);
		g2.drawImage(currentD, 850, 300, this);
		if(text!=null)
			text.repaint();
		if(text!=null){
			text2.repaint();}}
	
	public void mouseClicked(MouseEvent e){
		clickX = e.getX();
		clickY = e.getY();
		if(clickY>300 && clickY<400 && clickX>850 && clickX<950){
			t = new Thread(roll);
			t.start();
			try {
				Thread.sleep(100);}
			catch (InterruptedException ex) {;}}
		try{
			t.join();}
		catch(InterruptedException ex){;}
	
		if(guess1==false && guess2==false){
			//left, right, and down arrows
			if(clickY>=350 && clickY<=395 && clickX<=830 && clickX>=712){
				if(nextArrow==4)
					text.setText("Please exit the room.");
				//right arrow
				else if(clickX>=793 && pMovesY>=0 && (nextArrow==0 || nextArrow==3)){
					rightArrow();}
				//down arrow
				else if(clickX>=755 && clickX<793 && (nextArrow==0 || nextArrow==2)){
					downArrow();}
				//left arrow
				else if(clickX<755 && pMovesY>=0 && (nextArrow==0 || nextArrow==1)){
					leftArrow();}
				else
					text.setText("Please exit the room.");}
			//up arrow
			else if((clickY>=300 && clickY<=350) && (clickX>=755 && clickX<=793)){
				if(nextArrow==0 || nextArrow==4)
					upArrow();
				else
					text.setText("Please exit the room.");}
			sGuess = null;
			wGuess = null;
			repaint();}
		
		//prompt guess by entering a room
		if(nextArrow==0){
		for(int a=0; a<9; a++){
			if(pMovesX>=rooms[a].getXCor() && pMovesX<=(rooms[a].getXCor()+rooms[a].getWidth()-1) && pMovesY>=rooms[a].getYCor() && pMovesY<=(rooms[a].getYCor()+rooms[a].getHeight()-1))
				promptGuess(rooms[a]);}}
		
		//suspect guess
		if(guess1==true){
			for(int i=0; i<7; i++){
				if(clickX<(105+100*i) && clickX>(5+100*i) && clickY>570)
					sGuess = suspects[i];}
			guessSuspect(sGuess);}
		
		//weapon guess
		else if(guess2==true){
			for(int j=0; j<6; j++){
				if(j<3 && clickY>440 && clickY<590){
					if(clickX>(705+90*j) && clickX<(795+90*j))
						wGuess = weapons[j];}
				else if(j>2 && clickY>590 && clickY<740){
					if(clickX>(435+90*j) && clickX<(525+90*j))
						wGuess = weapons[j];}}
			guessWeapon(wGuess);}
		
		else
			text2.setText(null);
		repaint();}

	public void leftArrow(){
		if(pMovesY>=7 && pMovesY<=13 && pMovesX==-2)
			text.setText("You can't do that.");
		else if(pMovesX>-15){
				pawnX-=26;
				pMovesX-=1;
				lastArrow=1;
				if(nextArrow==1)
					nextArrow=0;
				text.setText(null);}
		else
			text.setText("You can't do that.");}
	
	public void rightArrow(){
		if(pMovesY>=7 && pMovesY<=13 && pMovesX==-8)
			text.setText("You can't do that.");
		else if(pMovesX<6){
			pawnX+=26;
			pMovesX+=1;
			lastArrow=3;
			if(nextArrow==3)
				nextArrow=0;
			text.setText(null);}
		else
			text.setText("You can't do that.");}
	
	public void downArrow(){
		if(pMovesX>=-7 && pMovesX<=-3 && pMovesY==6)
			text.setText("You can't do that.");
		else if(pMovesY<23){
			pawnY+=21;
			pMovesY+=1;
			lastArrow=2;
			if(nextArrow==2)
				nextArrow=0;
			text.setText(null);}
		else
			text.setText("You can't do that.");}
	
	public void upArrow(){
		if(pMovesX>=-7 && pMovesX<=-3 && pMovesY==14)
			text.setText("You can't do that.");
		else if(pMovesY>0){
			pawnY-=21;
			pMovesY-=1;
			lastArrow=4;
			if(nextArrow==4)
				nextArrow=0;
			text.setText(null);}
		else
			text.setText("You can't do that.");}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}