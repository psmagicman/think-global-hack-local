import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class Skills extends JFrame {
	
	/** Variables **/
	private JLabel frame_title;
	private Toolkit toolkit;
	private Dimension screen;
	private JButton helpButton;
	private JButton countingButton;
	private JButton addingButton;
	private JButton subtractingButton;
	private JButton roundingButton;
	private JButton multiplyingButton;
	private JButton dividingButton;
	private JButton exitButton;
	/** End of Variables **/
	
	public Skills() {
		
		// set the frame size
		setSize(300, 200);
		toolkit = getToolkit();
		screen = toolkit.getScreenSize();
		// set the location of the frame to the middle of the screen
		setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2 - getHeight() / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4,2));
		defineVariables();
		add(helpButton);
		add(countingButton);
		add(addingButton);
		add(subtractingButton);
		add(roundingButton);
		add(multiplyingButton);
		add(dividingButton);
		add(exitButton);
		setTitle(frame_title.getText());
		
		setVisible(true);
		
	}
	
	/**
	 * define_variables function
	 * This function defines the variables for the GUI
	 */
	private void defineVariables() {
		JPanel buttons_area = new JPanel();
		buttons_area.setBorder(new EmptyBorder(10, 10, 10, 10) );
		buttons_area.setLayout(new GridLayout(4, 2));
		
		// define the buttons and title
		frame_title = new JLabel("Skills!");

		
		// add the radios to a button group

		
		// add the buttons to the frame
		helpButton = new JButton("Help");
		countingButton = new JButton("Counting");
		addingButton = new JButton("Adding");
		subtractingButton = new JButton("Subtracting");
		roundingButton = new JButton("Rounding");
		multiplyingButton = new JButton("Multiplying");
		dividingButton = new JButton("Dividing");
		exitButton = new JButton("Exit");
		
	}
	
	public static void main(String[] args){
		Skills s = new Skills();
	}
}