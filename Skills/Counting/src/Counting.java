import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Counting 
{
	public static void main(String[] args) 	// User will be passed down. use the user's preference for style, level, etc.
	{	
	    JFrame f = new GameWindow();
	    GameLogic newGame = new GameLogic(3);	// set to 1 only if there is no user preference.
	    
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
	    JLabel numLabel = new JLabel(randomNum.toString());
	    numLabel.setLocation(300, 300); // magic number for right now
	    numLabel.setVisible(true);
	    
	    //get the next number
	    Integer answer = newGame.GetNextNumber();
	    String answerString = answer.toString();
	    int answerLength = answerString.length();
	    
	    String questionMarks = new String();
	    for (int i = 0; i < answerLength; ++i)
	    {
	    	questionMarks = questionMarks.concat("?");
	    }
	    
	    JLabel answerLabel = new JLabel(questionMarks);
	    answerLabel.setVisible(true);
	    
	    // Add all the components to the main component
	    mainContainer.add(question);
	    mainContainer.add(numLabel);
	    mainContainer.add(answerLabel);

	    f.add(mainContainer);
	}
}
