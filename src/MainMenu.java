import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class MainMenu extends JFrame {
	
	/** Variables **/
	private JLabel frame_title;
	private Toolkit toolkit;
	private Dimension screen;
	private JButton helpButton;
	private JButton gamesButton;
	private JButton optionButton;
	private JButton quitButton;
	/** End of Variables **/
	
	public MainMenu() {
		
		setup();
		setLayout(new GridLayout(4,1));
		defineVariables();
		makeButtons();
		setTitle(frame_title.getText());
		setUndecorated(true);
		setVisible(true);
		
	}
	
	/*
	 * Set up the frame
	 */
	public void setup() {
		// set the frame size
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		toolkit = getToolkit();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		frame_title = new JLabel("Main Menu");

		
		// add the radios to a button group		
		
		// add the buttons to the frame
		
	}
	
	public void makeButtons() {
		helpButton = new JButton("Help");
		gamesButton = new JButton("Games");
		optionButton = new JButton("Options");
		quitButton = new JButton("Quit");
		add(helpButton);
		add(gamesButton);
		add(optionButton);
		add(quitButton);
	}

	public static void main(String[] args){
		UserMenu u = new UserMenu();
		//MainMenu s = new MainMenu();
	}
	
}