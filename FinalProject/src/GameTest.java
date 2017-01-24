import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class GameTest extends JComponent{
	
	public static void main(String[] args) {
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		try{
			String board = "/resources/boardgame.jpg";
			Image iB = toolkit.getImage(GameTest.class.getResource(board));
			iB = iB.getScaledInstance(700, 550, Image.SCALE_SMOOTH);
			
			String suspects1 = "/resources/suspects1.jpg";
			Image iS1 = toolkit.getImage(GameTest.class.getResource(suspects1));
			iS1 = iS1.getScaledInstance(400, 170, Image.SCALE_SMOOTH);
			
			String suspects2 = "/resources/suspects2.jpg";
			Image iS2 = toolkit.getImage(GameTest.class.getResource(suspects2));
			iS2 = iS2.getScaledInstance(300, 170, Image.SCALE_SMOOTH);
			
			String weapons = "/resources/weapons.jpg";
			Image iW = toolkit.getImage(GameTest.class.getResource(weapons));
			iW = iW.getScaledInstance(270, 300, Image.SCALE_SMOOTH);
			
			String pawn = "resources/pawn.png";
			Image iP = toolkit.getImage(GameTest.class.getResource(pawn));
			iP = iP.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
			
			String arrows = "resources/arrows.png";
			Image iA = toolkit.getImage(GameTest.class.getResource(arrows));
			iA = iA.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
			
			String dice1 = "resources/dice1.png";
			Image d1 = toolkit.getImage(GameTest.class.getResource(dice1));
			d1 = d1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			
			String dice2 = "resources/dice2.png";
			Image d2 = toolkit.getImage(GameTest.class.getResource(dice2));
			d2 = d2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			
			String dice3 = "resources/dice3.png";
			Image d3 = toolkit.getImage(GameTest.class.getResource(dice3));
			d3 = d3.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			
			String dice4 = "resources/dice4.png";
			Image d4 = toolkit.getImage(GameTest.class.getResource(dice4));
			d4 = d4.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			
			String dice5 = "resources/dice5.png";
			Image d5 = toolkit.getImage(GameTest.class.getResource(dice5));
			d5 = d5.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			
			String dice6 = "resources/dice6.png";
			Image d6 = toolkit.getImage(GameTest.class.getResource(dice6));
			d6 = d6.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			
			JFrame frame = new JFrame("Clue");
			frame.getContentPane().add(new Game(iB, iS1, iS2, iW, iP, iA, d1, d2, d3, d4, d5, d6));
			frame.setSize(1000, 800);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);}
		catch(Exception ex){;}}
}