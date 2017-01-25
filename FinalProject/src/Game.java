import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * This class models Game objects which extend JComponent and implement MouseListener and ActionListener
 * @author ShaynaSnyder
 *
 */
public class Game extends JComponent implements MouseListener, ActionListener{
	Image iBoard, iSuspects1, iSuspects2, iWeapons, iPawn, iArrows, currentD, title, background;
	ArrayList<Image> dice = new ArrayList<Image>();
	ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
	int correctS, correctW, correctR, clickX, clickY, mouseX, mouseY;
	int pawnX=445, pawnY=0, pMovesX=0, pMovesY=-1, guessN=1, rollN=0, turn=0;
	//1:left 2:down 3:right 4:up
	int lastArrow=0, nextArrow=0, row=9, column=3;
	private JTextArea text, text2, notes, categories;
	boolean isCorrect=false, inRoom=false, started=false, guess1, guess2;
	JFrame frame;
	JPanel j = new JPanel();
	JButton start = new JButton();
	JButton rules = new JButton();
	JPanel[][] panelHolder = new JPanel[row][column];
	Suspect sGuess;
	Weapon wGuess;
	Room rGuess;
	Thread t = new Thread();
    Roll roll = new Roll(dice);
    Color crayon1 = Color.getHSBColor(.37f,.3f,.85f);
    Color crayon2 = Color.getHSBColor(.14f,.65f,.95f);
	
	Suspect[] suspects = {new Suspect("Miss Scarlet"), new Suspect("Colonel Mustard"), new Suspect("Mrs. White"), new Suspect("Mr. Green"), new Suspect("Mrs. Peacock"), new Suspect("Professor Plum"), new Suspect("Mr. Boddy")};
	Weapon[] weapons = {new Weapon("Wrench"), new Weapon("Candlestick"), new Weapon("Lead Pipe"), new Weapon("Rope"), new Weapon("Revolver"), new Weapon("Knife")};
	Room[] rooms = {new Room("Study",-15,0,6,3), new Room("Hall",-7,0,6,6), new Room("Lounge",1,0,6,5), new Room("Library",-15,5,5,5), new Room("Dining Room",0,8,7,6), new Room("Billard Room",-15,11,5,5), new Room("Conservatory",-15,18,4,4), new Room("Ball Room",-8,16,8,6), new Room("Kitchen",2,17,5,5)};
	
	/**
	 * This class creates a Game object using images and a JButton
	 * @param Image dice, board, suspects, weapons, arrows, pawn, title
	 * @param JButton start
	 */
	public Game(Image iB, Image iS1, Image iS2, Image iW, Image iP, Image iA, Image d1, Image d2, Image d3, Image d4, Image d5, Image d6, Image t, Image back, JButton s, JButton r){
		addMouseListener(this);
		dice.add(d1);
		dice.add(d2);
		dice.add(d3);
		dice.add(d4);
		dice.add(d5);
		dice.add(d6);

		Random rnd = new Random();
		correctS = rnd.nextInt(7);
		correctW = rnd.nextInt(6);
		correctR = rnd.nextInt(9);
		suspects[correctS].setCorrect();
		weapons[correctW].setCorrect();
		rooms[correctR].setCorrect();
		iBoard = iB;
		iSuspects1 = iS1;
		iSuspects2 = iS2;
		iWeapons = iW;
		iPawn = iP;
		iArrows = iA;
		background = back;
		currentD = dice.get(0);
		text = new JTextArea();
		text2 = new JTextArea();
		notes = new JTextArea();
		categories = new JTextArea();
		guess1=false;
		guess2=false;
		text.setBounds(710, 10, 290, 100);
		text.setBackground(crayon2);
		text2.setBounds(710, 110, 290, 150);
		text2.setBackground(crayon2);
		notes.setBounds(1010, 10, 380, 420);
		notes.setBackground(crayon1);
		categories.setBounds(1010, 440, 380, 40);
		categories.setBackground(crayon2);
		text.setLineWrap(true);
		text2.setLineWrap(true);
		notes.setLineWrap(true);
		text.setWrapStyleWord(true);
		text2.setWrapStyleWord(true);
		notes.setWrapStyleWord(true);
		text.setFont(new Font("Serif",Font.BOLD,18));
		text2.setFont(new Font("Serif",Font.PLAIN,16));
		notes.setFont(new Font("Serif",Font.PLAIN,16));
		categories.setFont(new Font("Serif",Font.BOLD,18));
		notes.setText("\n Add your notes here.");
		categories.setText(" Rooms                 Suspects              Weapons");
		text.setEditable(false);
		text2.setEditable(false);
		categories.setEditable(false);
		GridLayout grid = new GridLayout(row,column);
		j = new JPanel(grid);
		j.setBounds(1000, 480, 410, 250);
		panelHolder = new JPanel[row][column];
		
		for(int i=0; i<row; i++){
			for(int k=0; k<column; k++){
				panelHolder[i][k] = new JPanel(new FlowLayout(FlowLayout.LEFT));
				j.add(panelHolder[i][k]);}}
		
		for(int a=0; a<9; a++)
			panelHolder[a][0].add(new JCheckBox(rooms[a].getName()));
		for(int b=0; b<7; b++)
			panelHolder[b][1].add(new JCheckBox(suspects[b].getName()));
		for(int c=0; c<6; c++)
			panelHolder[c][2].add(new JCheckBox(weapons[c].getName()));
		
		title = t;
		start = s;
		rules = r;
		start.addActionListener(this);}
	
	/**
	 * Function adds textAreas and JPanel when JButton("Start") is clicked
	 */
	public void actionPerformed(ActionEvent arg0){
		started=true;
		start.setEnabled(false);
		start.setVisible(false);
		rules.setEnabled(false);
		rules.setVisible(false);
		add(text);
		add(text2);
		add(notes);
		add(categories);
		add(j);}
	
	/**
	 * This class models Roll which implements Runnable
	 * @author ShaynaSnyder
	 *
	 */
	class Roll implements Runnable{
		ArrayList<Image> dice = new ArrayList<Image>();
		Random rand = new Random();
		
		/**
		 * Function creates Roll using an ArrayList of images of dice
		 * @param imgs
		 */
		public Roll(ArrayList<Image> imgs){
			dice = imgs;}
		
		/**
		 * run function sets Image currentD equal to a random image of a dice side
		 */
		public void run(){
			for(int i=0; i<5; i++){
				int temp = rand.nextInt(6);
				currentD = (dice.get(temp));
				text.setText(null);
				repaint();
				if(i==4)
				rollN+=temp+1;
				try{Thread.sleep(400);}
				catch (InterruptedException ex){;}}
		Game.this.rollComplete();}}
	
	/**
	 * Function is called to prompt the user to guess when pawn enters a room
	 * @param Room rGuess
	 */
	public void promptGuess(Room r){
		rGuess = r;
		inRoom=true;
		text.setText(String.format("\n Make a guess in the %s.\n Select a suspect.\n Then select a weapon.", r.getName()));
		text2.setText(String.format("\n Guess #%d\n Room: %s\n Suspect:\n Weapon:", guessN, rGuess.getName()));
		if(guess2==false)
			guess1=true;}
	
	/**
	 * Function is called when user selects a suspect after entering a room
	 * @param Suspect sGuess
	 */
	public void guessSuspect(Suspect s){
		text2.setText(String.format("\n Guess #%d\n Room: %s\n Suspect: %s\n Weapon:", guessN, rGuess.getName(), sGuess.getName()));
		guess2=true;
		guess1=false;}
	
	/**
	 * Function is called when user selects a weapon after guessing a suspect
	 * @param Weapon wGuess
	 */
	public void guessWeapon(Weapon w){
		text2.setText(String.format("\n Guess #%d\n Room: %s\n Suspect: %s\n Weapon: %s", guessN, rGuess.getName(), sGuess.getName(), wGuess.getName()));
		checkGuess();
		guessN+=1;
		guess2=false;
		if(lastArrow==1 || lastArrow==2){
			nextArrow=lastArrow+2;}
		else if(lastArrow==3 || lastArrow==4){
			nextArrow=lastArrow-2;}}
	
	/**
	 * Function is called after the user selects both a suspect and a weapon guess
	 * Outputs how many parts of the guess were correct and how many were incorrect
	 */
	public void checkGuess(){
		int correct=0;
		rollN=0;
		if(rGuess.getCorrect()==true)
			correct+=1;
		if(sGuess.getCorrect()==true)
			correct+=1;
		if(wGuess.getCorrect()==true)
			correct+=1;
		if(correct==3){
			text.setText("\n Congratulations! You WIN!");
			text2.setText("\n Click anywhere to return to the main menu.");}
		else
			text.setText(String.format("\n Guess #%d\n %d part(s) correct\n %d part(s) incorrect\n Roll the die.", guessN, correct, 3-correct));}
	
	/**
	 * Function paints graphics in JFrame and is called again with repaint()
	 */
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		if(started==true){
		g2.drawImage(iBoard, 0, 10, this);
		g2.drawImage(iSuspects1, 5, 570, this);
		g2.drawImage(iSuspects2, 405, 570, this);
		g2.drawImage(iWeapons, 705, 440, this);
		g2.drawImage(iPawn, pawnX, pawnY, this);
		g2.drawImage(iArrows, 710, 300, this);
		g2.drawImage(currentD, 850, 300, this);}
		else
			g2.drawImage(title, 350, 0, this);
		if(rollN==0 && inRoom==false && started==true){
			text.setText("\n Roll the die.");}
		if(text!=null && started==true)
			text.repaint();
		if(text!=null && started==true)
			text2.repaint();
		if(started==true){
		categories.repaint();
		j.repaint();}
		if(notes!=null && started==true)
			notes.repaint();
		}
	
	/*
	class ImagePanel extends JComponent{
		private Image background;
		public ImagePanel(Image image){
			background = image;}
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(background, 0, 0, this);}}
			*/
	
	/**
	 * Function is called when mouse is clicked
	 * Depending on click location, several other functions are called
	 */
	public void mouseClicked(MouseEvent e){
		clickX = e.getX();
		clickY = e.getY();
		if(started==false){
			start.setVisible(true);
			start.setEnabled(true);}
		if(clickY>300 && clickY<400 && clickX>850 && clickX<950 && rollN==0 && guess1==false && guess2==false){
			t = new Thread(roll);
			t.start();}
		if(guess1==false && guess2==false && rollN>0){
			//left, right, and down arrows
			if(clickY>=350 && clickY<=395 && clickX<=830 && clickX>=712){
				if(nextArrow==4)
					text.setText("\n Please exit the room.");
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
					text.setText("\n Please exit the room.");}
			//up arrow
			else if((clickY>=300 && clickY<=350) && (clickX>=755 && clickX<=793)){
				if(nextArrow==0 || nextArrow==4)
					upArrow();
				else
					text.setText("\n Please exit the room.");}
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
	
	/**
	 * Function is called when the dice has completed a roll
	 * Moves user on to next turn
	 */
	public void rollComplete(){
		turn+=1;
		if(turn>15){
			text.setText("\n YOU LOSE!\n Sorry, you did not guess the correct combination in 15 turns");
			text2.setText("\n Click anywhere to return to the main menu");
			started=false;}
		else
			text.setText(String.format("\n Turn #%d\n You rolled a %d.\n Please move %d space(s).", turn, rollN, rollN));}

	/**
	 * Function is called when the left arrow is clicked at an appropriate time
	 * Moves pawn one space to the left
	 */
	public void leftArrow(){
		if(pMovesY>=7 && pMovesY<=13 && pMovesX==-2)
			text.setText("\n You can't do that.");
		else if(pMovesX>-15){
			rollN-=1;
			pawnX-=26;
			pMovesX-=1;
			lastArrow=1;
			if(nextArrow==1){
				nextArrow=0;
				inRoom=false;}
			text.setText(String.format("\n Please move %d space(s).", rollN));}
		else
			text.setText("\n You can't do that.");}
	
	/**
	 * Function is called when the right arrow is clicked at an appropriate time
	 * Moves pawn one space to the right
	 */
	public void rightArrow(){
		if(pMovesY>=7 && pMovesY<=13 && pMovesX==-8)
			text.setText("\n You can't do that.");
		else if(pMovesX<6){
			rollN-=1;
			pawnX+=26;
			pMovesX+=1;
			lastArrow=3;
			if(nextArrow==3){
				nextArrow=0;
				inRoom=false;}
			text.setText(String.format("\n Please move %d space(s).", rollN));}
		else
			text.setText("\n You can't do that.");}
	
	/**
	 * Function is called when the down arrow is clicked at an appropriate time
	 * Moves pawn one space down
	 */
	public void downArrow(){
		if(pMovesX>=-7 && pMovesX<=-3 && pMovesY==6)
			text.setText("\n You can't do that.");
		else if(pMovesY<23){
			rollN-=1;
			pawnY+=21;
			pMovesY+=1;
			lastArrow=2;
			if(nextArrow==2){
				nextArrow=0;
				inRoom=false;}
			text.setText(String.format("\n Please move %d space(s).", rollN));}
		else
			text.setText("\n You can't do that.");}
	
	/**
	 * Function is called when the up arrow is clicked at an appropriate time
	 * Moves paawn one space up
	 */
	public void upArrow(){
		if(pMovesX>=-7 && pMovesX<=-3 && pMovesY==14)
			text.setText("\n You can't do that.");
		else if(pMovesY>0){
			rollN-=1;
			pawnY-=21;
			pMovesY-=1;
			lastArrow=4;
			if(nextArrow==4){
				nextArrow=0;
				inRoom=false;}
			text.setText(String.format("\n Please move %d space(s).", rollN));}
		else
			text.setText("\n You can't do that.");}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}