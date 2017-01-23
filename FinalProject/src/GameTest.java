import javax.swing.*;
import java.awt.*;


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
			
			String pawn = "resources/pawn.png";
			Image iP = toolkit.getImage(GameTest.class.getResource(pawn));
			iP = iP.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
			
			String arrows = "resources/arrows.png";
			Image iA = toolkit.getImage(GameTest.class.getResource(arrows));
			iA = iA.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
			
			JFrame frame = new JFrame("Clue");
			frame.getContentPane().add(new Game(iB, iS1, iS2, iP, iA));
			frame.setSize(1000, 800);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);}
		catch(Exception ex){;}}
}