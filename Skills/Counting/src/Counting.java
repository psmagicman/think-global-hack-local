import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class Counting 
{
	private static Color backgroundColor; //initialize to userpreference color
	public static void main(String[] args) 	// User will be passed down. use the user's preference for style, level, etc.
	{	
		
	    JFrame f = new GameWindow();
	    GameLogic newGame = new GameLogic(3);	// set to 1 only if there is no user preference.
	    
	    
	    JPanel panel = new JPanel();
	    
	    RoundedPanel rPanel = new RoundedPanel();
	    rPanel.setBounds(10,10,200,200);
	    rPanel.setBackground(Color.white);
	    
	    panel.setBackground(backgroundColor);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    
	    panel.setBorder(new EmptyBorder (new Insets(30, 30, 30, 30)));
	    
	    //display question prompt
	    JLabel title = new JLabel("What is the next number?");
	    title.setFont(new Font("Arial", 2, 28)); 
	    rPanel.add(title);
	    
	    rPanel.add(Box.createRigidArea(new Dimension(0, 100)));
	    
	    panel.add(rPanel);
	    
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        
	    
	    JPanel numPanel = new JPanel();
	    //numPanel.setBorder(etched);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    numPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
	    numPanel.setBackground(Color.blue); // random color for testing 
	    
	    //display initial random number
	    Integer randomNum = newGame.GenerateRandomNumber();
	    numPanel.add(new JLabel(randomNum.toString()));
        
	    numPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
	    numPanel.add(new JLabel("->"));
        
	    numPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        //get the next number
	    Integer answer = newGame.GetNextNumber();
	    final String answerString = answer.toString();
	    int answerLength = answerString.length();
	    
	    String questionMarks = new String();
	    for (int i = 0; i < answerLength; ++i)
	    {
	    	questionMarks = questionMarks.concat("?");
	    }
	    
	    final JTextField answerField1 = new JTextField(1); // make a loop
	    //answerField.setDocument(new JTextFieldLimit(answerLength));
	    answerField1.setText("?");
	    PlainDocument doc1 = (PlainDocument) answerField1.getDocument();
	    doc1.setDocumentFilter(new MyDocumentFilter(answerField1, answerString.charAt(0)));
	      
	      final JTextField answerField2 = new JTextField(1);
		    //answerField.setDocument(new JTextFieldLimit(answerLength));
	      answerField2.setText("?");
		    PlainDocument doc2 = (PlainDocument) answerField2.getDocument();
		    doc2.setDocumentFilter(new MyDocumentFilter(answerField2, answerString.charAt(1)));
		      
		      final JTextField answerField3 = new JTextField(1);
			    //answerField.setDocument(new JTextFieldLimit(answerLength));
		      answerField3.setText("?");
			    PlainDocument doc3 = (PlainDocument) answerField3.getDocument();
			    doc3.setDocumentFilter(new MyDocumentFilter(answerField3, answerString.charAt(2)));
	    
	    answerField1.setText("?");
	    answerField2.setText("?");
	    answerField3.setText("?");
	    
	    answerField1.addActionListener(new ActionListener()
	    {
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("wadsffdf");
				
				/*
				if (answerString.charAt(0) == answerField1.getText().charAt(0))
				{
					answerField1.setBackground(Color.green);
					//System.out.println(2);
				}
				else
				{
					answerField1.setBackground(Color.red);
					//System.out.println(3);
				}
				*/
			}
	    });
	    
	    
	    answerField1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	    answerField1.setBackground(backgroundColor);
	    numPanel.add(answerField1);
	    answerField2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	    answerField2.setBackground(backgroundColor);
	    numPanel.add(answerField2);
	    answerField3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	    answerField3.setBackground(backgroundColor);
	    numPanel.add(answerField3);
	    
	    
	    
	    
	    rPanel.add(numPanel);
	    
	    //panel.add(numPanel);
	    
        f.add(panel);
                       
        f.setTitle("Counting Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
             
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	
	void ApplyUserPreference()
	{
		backgroundColor = Color.gray;
	}
		
}

class MyDocumentFilter extends DocumentFilter
{
	private JTextField _textField;
	private char _answer;
	
	MyDocumentFilter(JTextField textField, char answer)
	{
		_textField = textField;
		_answer = answer;
	}
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
        	super.remove(fp, 1, 1);
        	super.insertString(fp, 0, string, aset);
        }
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
        	
			if (_answer == _textField.getText().charAt(0))
			{
				_textField.setBackground(Color.green);
				//System.out.println(2);
			}
			else
			{
				_textField.setBackground(Color.red);
				//System.out.println(3);
			}
        	
        }
    }
}
