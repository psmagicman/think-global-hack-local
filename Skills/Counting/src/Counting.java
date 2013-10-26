import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Counting 
{
	public static void main(String[] args) 
	{	
	    JFrame f = new GameWindow();
	    GameLogic newGame = new GameLogic(1);	// change the magic number later
	    
	    JPanel mainContainer = new JPanel();
	    mainContainer.setBorder(BorderFactory.createLineBorder(Color.black)); //temp for testing
	    mainContainer.setSize(10, 10);
	    mainContainer.setVisible(true);
	    
	    //display question
	    JLabel question = new JLabel("What is the next number?");
	    question.setFont(new Font("Arial", 2, 28)); 
	    question.setVisible(true);
	    
	    //display initial random number
	    Integer randomNum = newGame.GenerateRandomNumber();
	    JLabel num = new JLabel(randomNum.toString());
	    num.setVisible(true);
	   
	    mainContainer.add(num);
	    mainContainer.add(question);
	    
	    f.add(mainContainer);
	}
}
