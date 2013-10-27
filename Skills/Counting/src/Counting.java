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
	private static Font userFont = new Font("Arial", 2, 70); // temp hardcoded
	private static Color backgroundColor; //initialize to user preference color
	private static Color panelBackgroundColor = Color.white; // temporarily white
	private static Color highlightColor = Color.gray;

	private static JTextField[] AnswerFields;
	private static String Answer;
	
	private static JPanel numPanel;
	private static JLabel firstNumberLabel;
	private static JMenu levelMenu;
	private static GameLogic newGame;
	private static Integer currentNumber;
	
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
	    levelMenu = new JMenu("Level");
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
	    
	    levelMenu.setMnemonic(KeyEvent.VK_L);	//Todo maybe incorporate with the key listener? maybe..
	    
	    help.setFont(new Font("Arial", 2, 28));
	    levelMenu.setFont(new Font("Arial", 2, 28));
	    learn.setFont(new Font("Arial", 2, 28));    
	    practice.setFont(new Font("Arial", 2, 28));	    
	    EXIT.setFont(new Font("Arial", 2, 28));
	    
	    java.util.List<Integer> allLevels = newGame.GetLevels();
	    	    
	    for (final Integer lv : allLevels)
	    {
	    	JMenuItem temp = new JMenuItem(lv.toString());
	    	
	    	// highlight the current level
	    	if (lv == newGame.GetCurrentLevel())
	    		temp.setBackground(Counting.highlightColor);
	    	
	    	levelMenu.add(temp);	
	    	
	    	temp.addActionListener(new ActionListener() 
	    	{
	    	    @Override
	    	    public void actionPerformed (ActionEvent arg0) 
	    	    {
	    	    	
	            	ResetGame(lv);
	    	    }
	    	});
	    }
	    
	    levelMenu.addSeparator();
	    levelMenu.add("Exit");
   
	    gameMenuBar.add(help); 
	    
	    gameMenuBar.add(new JSeparator(SwingConstants.VERTICAL));
	    
	    gameMenuBar.add(levelMenu);
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
	    
	    problemLabel.setFont(userFont); 
	    
	    rPanel.add(problemLabel);
	    
	    rPanel.add(Box.createRigidArea(new Dimension(0, 100)));
	    
	    panel.add(rPanel);
        	    
	    numPanel = new JPanel();

	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    numPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
	    numPanel.setBackground(panelBackgroundColor); // random color for testing 
	    
	    numPanel.add(Box.createRigidArea(new Dimension(0, 300)));
	    
	    //display initial random number
	    currentNumber = newGame.GenerateRandomNumber();
	    
	    firstNumberLabel = new JLabel(currentNumber.toString());
	    firstNumberLabel.setFont(userFont);
	    numPanel.add(firstNumberLabel);
        
	    numPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        
	    JLabel arrow = new JLabel("->");
	    arrow.setFont(userFont);
	    numPanel.add(arrow);
        
	    numPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        
        //get the next number which is the answer for the current number
	    Integer answer = newGame.GetNextNumber();
	    Answer = answer.toString();
	    int answerLength = Answer.length();
	    
	    // Each digit for the answer has its own JTextField
	    AnswerFields = new JTextField[GameLogic.MAX_LEVEL];
	    for (int i = 0; i < GameLogic.MAX_LEVEL; ++i)
	    {
	    	AnswerFields[i] = new JTextField(1);
	    	AnswerFields[i].addKeyListener(new Counting());
	    	AnswerFields[i].setText("?");
	    	AnswerFields[i].setFont(userFont);
	    	AnswerFields[i].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		    AnswerFields[i].setBackground(backgroundColor);
		    
		    PlainDocument doc = (PlainDocument) AnswerFields[i].getDocument();
		    if (i < answerLength)
		    {
		    	doc.setDocumentFilter(new MyDocumentFilter(AnswerFields[i], Answer.charAt(i), new Counting()));
		    	AnswerFields[i].setVisible(true);
		    }
		    else
		    {
		    	doc.setDocumentFilter(new MyDocumentFilter(AnswerFields[i], '-', new Counting()));
		    	AnswerFields[i].setVisible(false);
		    }
		    numPanel.add(AnswerFields[i]);
	    }

	    rPanel.add(numPanel);
	    
	    numPanel.setBackground(Color.orange);
	    
	    numberOfAttemptsLabel = new JLabel("");
	    numberOfAttemptsLabel.setVisible(false);
	    rPanel.add(numberOfAttemptsLabel);
	    
	    //just a temp place holder until I figure out why orange thing is strecthing
	    JLabel footHolder = new JLabel("<html><font color='white'>...</font></html>");
	    footHolder.setFont(new Font("Arial", 2, 40));
	    rPanel.add(footHolder);
	    
        f.add(panel);
	    
	    JPanel directionPanel = new JPanel();
	    directionPanel.setBackground(Color.black);
	    directionPanel.setMaximumSize(new Dimension (10000,100));
	    JLabel direction = new JLabel("<html><font color='white'>I am a direction</font></html>");
	    direction.setFont(new Font("Arial", 2, 30));
	    directionPanel.add(direction);
	    panel.add(directionPanel);
	    
        f.setTitle("Counting Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
             
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        textToSpeech.getInstance().speakNow(problem);	
        
        // the location of the code here doesn't seem so good
	    textToSpeech.getInstance().speak(currentNumber.toString());
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

			speechPhrase compliment = new speechPhrase();
			
			textToSpeech.getInstance().speak(Answer);
			
			textToSpeech.getInstance().speak(compliment.getRandomCompliment());
			
			isDone = true;

		}
	}
	
	static void ResetGame(int level)
	{
		isDone = false;
		
		int previousAnswerLenght = Answer.length();
	    for (int i = 0; i < previousAnswerLenght; ++i)	// needs refactor with duplicate code above
	    {
	    	AnswerFields[i].setVisible(false);
	    }
	   
		numberOfAttemptsLabel.setVisible(false);
		
		levelMenu.getItem(newGame.GetCurrentLevel()-1).setBackground(levelMenu.getBackground());
		newGame.SetLevel(level);
		levelMenu.getItem(level-1).setBackground(Counting.highlightColor);
		
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

    	    textToSpeech.getInstance().speakNow(string);
        	
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
