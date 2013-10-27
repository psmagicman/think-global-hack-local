import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.ImageIcon;

public class Counting 
{
	private static Color backgroundColor; //initialize to userpreference color
	private static Color panelBackgroundColor = Color.white; // temporarily white

	private static JTextField[] AnswerFields;
	private static String Answer;
	
	public static int numberOfAttempts = 0;
	private static JLabel numberOfAttemptsLabel;
	
	public static void main(String[] args) 	// User will be passed down. use the user's preference for style, level, etc.
	{			
	    JFrame f = new GameWindow();
	    GameLogic newGame = new GameLogic(3);	// set to 1 only if there is no user preference.	    

	    //add menu for levels 
	    JMenuBar gameMenuBar = new JMenuBar();
	    JMenu level = new JMenu("Level");
	    //level.setMnemonic(KeyEvent.VK_L);
	    
	    JMenuItem levelItem = new JMenuItem("1");
	    

	    levelItem.addActionListener(new ActionListener() 
	    {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) 
	    	{
	    		System.exit(0);

	    	}
	    });
    
	    gameMenuBar.add(level);
	    f.setJMenuBar(gameMenuBar);
	    f.add(gameMenuBar);
	    
	    
	    JPanel panel = new JPanel();
	    
	    RoundedPanel rPanel = new RoundedPanel();
	    rPanel.setBounds(10,10,200,200);
	    rPanel.setBackground(Color.white);

	    panel.setBackground(backgroundColor);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    
	    panel.setBorder(new EmptyBorder (new Insets(30, 30, 30, 30)));
	    
	    //display question prompt
	    String problem = "What is the next number?";
	    JLabel problemLabel = new JLabel(problem);
	    TextToSpeechWrapper problemSpeaker = new TextToSpeechWrapper(problem);
	    Thread thread = new Thread(problemSpeaker);
	    thread.start();
	    problemLabel.setFont(new Font("Arial", 2, 28)); 
	    rPanel.add(problemLabel);
	    
	    rPanel.add(Box.createRigidArea(new Dimension(0, 100)));
	    
	    panel.add(rPanel);
        	    
	    JPanel numPanel = new JPanel();

	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    numPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
	    numPanel.setBackground(panelBackgroundColor); // random color for testing 
	    
	    //display initial random number
	    Integer randomNum = newGame.GenerateRandomNumber();
	    
	    JLabel firstNum = new JLabel(randomNum.toString());
	    firstNum.setFont(new Font("Arial", 2, 28));
	    numPanel.add(firstNum);
        
	    numPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
	    JLabel arrow = new JLabel("->");
	    arrow.setFont(new Font("Arial", 2, 28));
	    numPanel.add(arrow);
        
	    numPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        //get the next number
	    Integer answer = newGame.GetNextNumber();
	    Answer = answer.toString();
	    int answerLength = Answer.length();
	    
	    AnswerFields = new JTextField[answerLength];
	    for (int i = 0; i < answerLength; ++i)
	    {
	    	AnswerFields[i] = new JTextField(1);
	    	AnswerFields[i].setText("?");
	    	AnswerFields[i].setFont(new Font("Arial", 2, 28)); 
		    PlainDocument doc = (PlainDocument) AnswerFields[i].getDocument();
		    doc.setDocumentFilter(new MyDocumentFilter(AnswerFields[i], Answer.charAt(i), new Counting()));
		    
		    AnswerFields[i].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		    AnswerFields[i].setBackground(backgroundColor);
		    numPanel.add(AnswerFields[i]);
	    }

	    rPanel.add(numPanel);
	    
	    numberOfAttemptsLabel = new JLabel("");
	    numberOfAttemptsLabel.setVisible(false);
	    rPanel.add(numberOfAttemptsLabel);
	    
        f.add(panel);
                       
        f.setTitle("Counting Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
             
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
   
        // refactor later
        textToSpeech.speak(randomNum.toString());
	}
	
	void ApplyUserPreference()
	{
		backgroundColor = Color.gray;
	}
		
	void CheckIfFinished()
	{
		String answerFromUser = "";
		for (int i = 0; i < Answer.length(); ++i)
	    {
			answerFromUser += AnswerFields[i].getText();
	    }
		
		if (answerFromUser.equals(Answer))
		{
			numberOfAttemptsLabel.setText("Number of Attempts: " + numberOfAttempts);
			numberOfAttemptsLabel.setVisible(true);
		}
	}
}

class MyDocumentFilter extends DocumentFilter
{
	private JTextField _textField;
	private char _answer;
	private Counting _countingInstance;
	
	MyDocumentFilter(JTextField textField, char answer, Counting countingInstance)
	{
		_textField = textField;
		_answer = answer;
		_countingInstance = countingInstance;
	}
	/*
    @Override
    public void insertString(DocumentFilter.FilterBypass fp
            , int offset, String string, AttributeSet aset)
                                throws BadLocationException
    {
        int len = string.length();
        boolean isValidInteger = true;

        for (int i = 0; i < len; i++)
        {
        	if (!Character.isDigit(string.charAt(i)) && (string.charAt(i) != '?'))
            {
                isValidInteger = false;
                break;
            }
        }
        if (isValidInteger)
        {
        	super.remove(fp, 0, 1);
        	super.insertString(fp, 0, string, aset);
        	textToSpeech.speak(string);
        	
			if (_answer == _textField.getText().charAt(0))
			{
				_textField.setBackground(Color.green);	// no font color?
			}
			else
			{
				_textField.setBackground(Color.red);
				Counting.numberOfAttempts++;
			}
        }
    }
    */

    @Override
    public void replace(DocumentFilter.FilterBypass fp, int offset
                    , int length, String string, AttributeSet aset)
                                        throws BadLocationException
    {
        int len = string.length();
        boolean isValidInteger = true;

        for (int i = 0; i < len; i++)
        {
        	if (!Character.isDigit(string.charAt(i)) && (string.charAt(i) != '?'))
            {
                isValidInteger = false;
                break;
            }
        }
        if (isValidInteger)
        {
        	super.remove(fp, 0, 1);
        	super.insertString(fp, 0, string, aset);
        	textToSpeech.speak(string);
        	
			if (_answer == _textField.getText().charAt(0))
			{
				_textField.setBackground(Color.green);	// no font color?
				
				_countingInstance.CheckIfFinished();
			}
			else
			{
				_textField.setBackground(Color.red);
				Counting.numberOfAttempts++;
			}
        }
    }
}

class TextToSpeechWrapper implements Runnable
{
	private String stringToSpeak;
	
	TextToSpeechWrapper(String text)
	{
		stringToSpeak = text;
	}
	
	@Override
	public void run() 
	{
		textToSpeech.speak(stringToSpeak);
	}
}

