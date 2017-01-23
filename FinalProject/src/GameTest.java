import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.URL;

public class GameTest extends JComponent implements MouseListener{
	Game game = new Game();
	JFrame frame;
	private JTextArea text;
	Image iBoard, iSuspects, iPawn, iArrows;
	int correctS, correctW, correctR, clickX, clickY, mouseX, mouseY;
	int pawnX=445, pawnY=0, pMovesX=0, pMovesY=-1;

	
	public static void main(String[] args) {
		Game game = new Game();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		try{
			//URL board = new URL("http://www.printablee.com/postpic/2014/09/clue-board-game-rooms_259531.jpg");
			String board = "/resources/boardgame.jpg";
			Image iB = toolkit.getImage(GameTest.class.getResource(board));
			iB = iB.getScaledInstance(700, 550, Image.SCALE_SMOOTH);
			
			URL suspects = new URL("https://s-media-cache-ak0.pinimg.com/originals/38/14/a9/3814a9faf7368fe933658e8bfa4a5a66.jpg");
			Image iS = toolkit.getImage(suspects);
			iS = iS.getScaledInstance(600, 300, Image.SCALE_SMOOTH);
			
			//URL pawn = new URL("https://img.clipartfest.com/163b9a8f76858d8877a35bbc044748e9_sorry-game-piece-clip-art-game-pawn-clipart-transparent_566-800.png");
			String pawn = "resources/pawn.png";
			Image iP = toolkit.getImage(GameTest.class.getResource(pawn));
			iP = iP.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
			
			String arrows = "resources/arrows.png";
			Image iA = toolkit.getImage(GameTest.class.getResource(arrows));
			iA = iA.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
			
			JFrame frame = new JFrame("Clue");
			frame.getContentPane().add(new GameTest(game, iB, iS, iP, iA));
			frame.setSize(900, 900);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);}
		catch(Exception ex){;}}
	
	public GameTest(Game g, Image iB, Image iS, Image iP, Image iA){
		game = g;
		iBoard = iB;
		iSuspects = iS;
		iPawn = iP;
		iArrows = iA;
		addMouseListener(this);
		int cS, cW, cR;
		Random rnd = new Random();
		cS = rnd.nextInt(7);
		cW = rnd.nextInt(6);
		cR = rnd.nextInt(9);
		game.setCorrect(cS, cW, cR);
		}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(iBoard, 0, 10, this);
		g2.drawImage(iSuspects, 0, 560, this);
		g2.drawImage(iPawn, pawnX, pawnY, this);
		g2.drawImage(iArrows, 710, 410, this);
		if(text!=null)
			text.repaint();}
	
	public void mouseClicked(MouseEvent e){
		clickX = e.getX();
		clickY = e.getY();
		text = new JTextArea();
		text.setBounds(610, 570, 280, 280);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setFont(new Font("Serif",Font.BOLD,20));
		add(text);
		text.setEditable(false);
		
	
		//left, right, and down arrows
		if(clickY>=460 && clickY<=505 && clickX<=830){
			//right arrow
			if(clickX>=793 && pMovesY>=0){
				if(pMovesX<6){
					pawnX+=26;
					pMovesX+=1;}
				else
					text.setText("You can't do that.");}
			//down arrow
			else if(clickX>=755 && clickX<=793){
				if(pMovesY<23){
					pawnY+=21;
					pMovesY+=1;}
				else
					text.setText("You can't do that.");}
			//left arrow
			else if(clickX>712 && pMovesY>=0){
				if(pMovesX>-15){
					pawnX-=26;
					pMovesX-=1;}
				else
					text.setText("You can't do that.");}
			else
				text.setText("You can't do that.");}
		//up arrow
		else if((clickY>=410 && clickY<=460) && (clickX>=755 && clickX<=793)){
			if(pMovesY>0){
				pawnY-=21;
				pMovesY-=1;}
			else
				text.setText("You can't do that.");}
		
		//lounge
		if(pMovesX>=1 && pMovesY<=4){
			text.setText("Make a guess in the Lounge.");}
		//hall
		if(pMovesX>=-7 && pMovesX<=-2 && pMovesY<=5){
			text.setText("Make a guess in the Hall.");}
		//study
		if(pMovesX<=-10 && pMovesY<=2){
			text.setText("Make a guess in the Study.");}
		
		game.setPawnPosition(pMovesX, pMovesY);
		repaint();
	}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}