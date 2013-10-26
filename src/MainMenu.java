import javax.swing.*;
import javax.swing.border.EmptyBorder;

import users.NameTakenException;
import users.User;
import users.UserManagementService;

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
		
		// set the frame size
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		toolkit = getToolkit();
		screen = toolkit.getScreenSize();
		// set the location of the frame to the middle of the screen
		setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2 - getHeight() / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4,1));
		defineVariables();
		add(helpButton);
		add(gamesButton);
		add(optionButton);
		add(quitButton);
		setTitle(frame_title.getText());
		setUndecorated(true);
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
		frame_title = new JLabel("Main Menu");

		
		// add the radios to a button group

		// import User Preferences
		
		
		// add the buttons to the frame
		helpButton = new JButton("Help");
		gamesButton = new JButton("Games");
		optionButton = new JButton("Options");
		quitButton = new JButton("Quit");
		
	}
	
	public static void main(String[] args){
		UserMenu u = new UserMenu();		
		MainMenu s = new MainMenu();
		
		
		
		
	}
	
}