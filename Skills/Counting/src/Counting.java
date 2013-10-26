import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



public class Counting 
{
	private static Color backgroundColor; //initialize to userpreference color
	public static void main(String[] args) 	// User will be passed down. use the user's preference for style, level, etc.
	{	
		
	    JFrame f = new GameWindow();
	    GameLogic newGame = new GameLogic(3);	// set to 1 only if there is no user preference.
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(backgroundColor);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    
	    panel.setBorder(new EmptyBorder (new Insets(30, 30, 30, 30)));
	    
	    //display question prompt
	    JLabel title = new JLabel("What is the next number?");
	    title.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel.add(title);
	    
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        
	    
	    JPanel numPanel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    
	    //display initial random number
	    Integer randomNum = newGame.GenerateRandomNumber();
	    numPanel.add(new JLabel(randomNum.toString()));
        
	    numPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
	    numPanel.add(new JLabel("->"));
        
	    numPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        //get the next number
	    Integer answer = newGame.GetNextNumber();
	    String answerString = answer.toString();
	    int answerLength = answerString.length();
	    
	    String questionMarks = new String();
	    for (int i = 0; i < answerLength; ++i)
	    {
	    	questionMarks = questionMarks.concat("?");
	    }
	    	  
	    JTextField answerField = new JTextField(answerLength);
	    answerField.setText(questionMarks);
	    answerField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	    answerField.setBackground(backgroundColor);
	    numPanel.add(answerField);
	    
	    panel.add(numPanel);
	    
        f.add(panel);
        
        f.pack();
               
        f.setTitle("RigidArea");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        
        
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	
	void ApplyUserPreference()
	{
		backgroundColor = Color.gray;
	}
}
