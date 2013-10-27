import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class Counting implements KeyListener
{
	private static Color backgroundColor; //initialize to user preference color
	private static Color panelBackgroundColor = Color.white; // temporarily white

	private static JTextField[] AnswerFields;
	private static String Answer;
	
	private static JPanel numPanel;
	private static JLabel firstNumberLabel;
	private static GameLogic newGame;
	private static Integer currentNumber;
	
	public static Thread thread = null;
	public static TextToSpeakWrapper speaker = null;
	public static boolean isAfterAllSpeakingForProblem = false;
	public static boolean isDone = false;
	
	public static int numberOfAttempts = 0;
	private static JLabel numberOfAttemptsLabel;
	
	public static void main(String[] args) 	// User will be passed down. use the user's preference for style, level, etc.
	{			
	    JFrame f = new GameWindow();
	    f.addKeyListener(new Counting());
	    newGame = new GameLogic(GameLogic.MIN_LEVEL);	// set to GameLogic.MIN_LEVEL if there is no user preference.	    

	    //add menu for levels 
	    JMenuBar gameMenuBar = new JMenuBar();
	    
	    JMenu help = new JMenu("Help");
	    JMenu level = new JMenu("Level");
	    JMenu learn = new JMenu("Learn");
	    JMenu practice = new JMenu("Practice");
	    JMenu EXIT = new JMenu("EXIT");
	    EXIT.addActionListener(new ActionListener() 
	    {
	    	//doesn't work right now
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
        });
	    
	    level.setMnemonic(KeyEvent.VK_L);	//Todo maybe incorporate with the key listener? maybe..
	    
	    help.setFont(new Font("Arial", 2, 28));
	    level.setFont(new Font("Arial", 2, 28));
	    learn.setFont(new Font("Arial", 2, 28));    
	    practice.setFont(new Font("Arial", 2, 28));	    
	    EXIT.setFont(new Font("Arial", 2, 28));
	    
	    java.util.List<Integer> allLevels = newGame.GetLevels();
	    	    
	    for (Integer lv : allLevels)
	    {
	    	level.add(new JMenuItem(lv.toString()));	    	
	    }
	    
	    level.addSeparator();
	    level.add("Exit");
   
	    gameMenuBar.add(help); 
	    
	    gameMenuBar.add(new JSeparator(SwingConstants.VERTICAL));
	    
	    gameMenuBar.add(level);
	    f.setJMenuBar(gameMenuBar);
	    
	    gameMenuBar.add(new JSeparator(SwingConstants.VERTICAL));
	      
	    gameMenuBar.add(learn);  
	    
	    gameMenuBar.add(new JSeparator(SwingConstants.VERTICAL));
	    
	    gameMenuBar.add(practice);  
	    
	    gameMenuBar.add(new JSeparator(SwingConstants.VERTICAL));
	    
	    gameMenuBar.add(EXIT);  
	    
	    JPanel panel = new JPanel();
	    
	    RoundedPanel rPanel = new RoundedPanel();
	    rPanel.setLayout(new BoxLayout(rPanel, BoxLayout.Y_AXIS));
	    
	    rPanel.setBounds(10,10,200,200); //?
	    rPanel.setFocusable(true);
	    rPanel.setBackground(Color.white);

	    panel.setBackground(backgroundColor);
	    
	    panel.setBorder(new EmptyBorder (new Insets(30, 30, 30, 30))); //buffer space around screen
	    
	    rPanel.add(Box.createRigidArea(new Dimension(0, 100)));
	    
	    //display question prompt
	    String problem = "What is the next number?";
	    JLabel problemLabel = new JLabel(problem);
	    
	    speaker = new TextToSpeakWrapper(problem);
	    thread = new Thread(speaker);
	    thread.start();
	    problemLabel.setFont(new Font("Arial", 2, 50)); 
	    
	    rPanel.add(problemLabel);
	    
	    rPanel.add(Box.createRigidArea(new Dimension(0, 100)));
	    
	    panel.add(rPanel);
        	    
	    numPanel = new JPanel();

	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    numPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
	    numPanel.setBackground(panelBackgroundColor); // random color for testing 
	    
	    //display initial random number
	    currentNumber = newGame.GenerateRandomNumber();
	    
	    firstNumberLabel = new JLabel(currentNumber.toString());
	    firstNumberLabel.setFont(new Font("Arial", 2, 50));
	    numPanel.add(firstNumberLabel);
        
	    numPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
	    JLabel arrow = new JLabel("->");
	    arrow.setFont(new Font("Arial", 2, 50));
	    numPanel.add(arrow);
        
	    numPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        //get the next number which is the answer for the current number
	    Integer answer = newGame.GetNextNumber();
	    Answer = answer.toString();
	    int answerLength = Answer.length();
	    
	    // Each digit for the answer has its own JTextField
	    AnswerFields = new JTextField[answerLength];
	    for (int i = 0; i < answerLength; ++i)
	    {
	    	AnswerFields[i] = new JTextField(1);
	    	AnswerFields[i].addKeyListener(new Counting());
	    	AnswerFields[i].setText("?");
	    	AnswerFields[i].setFont(new Font("Arial", 2, 50)); 
		    PlainDocument doc = (PlainDocument) AnswerFields[i].getDocument();
		    doc.setDocumentFilter(new MyDocumentFilter(AnswerFields[i], Answer.charAt(i), new Counting()));
		    
		    AnswerFields[i].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		    AnswerFields[i].setBackground(backgroundColor);
		    numPanel.add(AnswerFields[i]);
	    }

	    rPanel.add(numPanel);
	    
	    numPanel.setBackground(Color.orange);
	    
	    numberOfAttemptsLabel = new JLabel("");
	    numberOfAttemptsLabel.setVisible(false);
	    rPanel.add(numberOfAttemptsLabel);
	    
	    rPanel.add(new JLabel("end"));
	    
        f.add(panel);
	    
	    JPanel directionPanel = new JPanel();
	    directionPanel.setBackground(Color.black);
	    directionPanel.setMaximumSize(new Dimension (10000,100));
	    JLabel direction = new JLabel("<html><font color='white'>I am a direction</font></html>");
	    directionPanel.add(direction);
	    panel.add(directionPanel);
	    
        f.setTitle("Counting Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
             
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
   
        // the location of the code here doesn't seem so good
	    speaker = new TextToSpeakWrapper(currentNumber.toString());
	    try 
	    {
			thread.join();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	    thread = new Thread(speaker);
	    thread.start();
	    try 
	    {
			thread.join();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	    isAfterAllSpeakingForProblem = true;	// do not let the speaker read the digit when it's reading the problem
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
			numberOfAttemptsLabel.setFont(new Font("Arial", 2, 28));	

			speaker = new TextToSpeakWrapper(Answer);
			try 
			{
				thread.join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			thread = new Thread(speaker);
			thread.start();
			isDone = true;	//may wanna thread join
		}
	}
	
	void ResetGame(int level)
	{
		thread = null;
		speaker = null;
		isAfterAllSpeakingForProblem = false;
		isDone = false;
		
		int previousAnswerLenght = Answer.length();
	    for (int i = 0; i < previousAnswerLenght; ++i)	// needs refactor with duplicate code above
	    {
	    	AnswerFields[i].setVisible(false);
	    }
	   

		numberOfAttemptsLabel.setVisible(false);
		newGame.SetLevel(level);
		currentNumber = newGame.GenerateRandomNumber();
		firstNumberLabel.setText(currentNumber.toString());
		
		Answer = ((Integer)newGame.GetNextNumber()).toString();
	    for (int i = 0; i < Answer.length(); ++i)	// needs refactor with duplicate code above
	    {
	    	AnswerFields[i].setText("?");
	    	AnswerFields[i].setBackground(panelBackgroundColor);
	    	AnswerFields[i].setVisible(true);
	    	PlainDocument doc = (PlainDocument) AnswerFields[i].getDocument();
		    ((MyDocumentFilter) doc.getDocumentFilter()).setAnswer(Answer.charAt(i));
	    }	    
	    
	    numberOfAttempts = 0;	// this needs to be after AnswerFields.setText()
	}
	
	public void keyPressed(KeyEvent e) 
	{
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ENTER && isDone)
        {
        	ResetGame(newGame.GetCurrentLevel());
        }
    }

	@Override
	public void keyReleased(KeyEvent e) 
	{
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
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

	public void setAnswer(char answer)
	{
		_answer = answer;
	}
	
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
    	    try 
    	    {
    	    	while (!Counting.isAfterAllSpeakingForProblem && Counting.thread != null && Counting.thread.isAlive())
    	    		Counting.thread.join();
    		} catch (InterruptedException e) 
    		{
    			e.printStackTrace();
    		}
        	Counting.speaker = new TextToSpeakWrapper(string);
        	Counting.thread = new Thread(Counting.speaker);
        	Counting.thread.start();
        	
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
    
    @Override
    public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException 
    {
    	// Prevent from deleting the digit
    }
}

class TextToSpeakWrapper implements Runnable
{
	private String stringToSpeak;
	
	TextToSpeakWrapper(String text)
	{
		stringToSpeak = text;
	}
	
	@Override
	public void run() 
	{
		textToSpeech.speak(stringToSpeak);
	}
}
