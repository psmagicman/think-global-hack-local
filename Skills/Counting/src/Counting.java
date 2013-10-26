import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Counting 
{
	public static void main(String[] args) 
	{
	    JFrame f = new GameWindow();
	    GameLogic newGame = new GameLogic(1);	// change the magic number later
	    Integer randomNum = newGame.GenerateRandomNumber();
	    JLabel num = new JLabel(randomNum.toString());
	    num.setVisible(true);
	    
	    JLabel question = new JLabel("What is the next number?");
	    question.setFont(new Font("Arial", 2, 28));
	    question.setHorizontalTextPosition((SwingConstants.CENTER));
	    question.setVerticalTextPosition((SwingConstants.CENTER));
	    
	    question.setVisible(true);
	    
	    f.add(num);
	    f.add(question);
	}
}
