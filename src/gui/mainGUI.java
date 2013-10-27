package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import users.*;
import util.DirectoryParser;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class mainGUI extends JFrame {

	/** Variables **/
	private JLabel frame_title;
	private Toolkit toolkit;
	private Dimension screen;
	/** End of Variables **/

	public mainGUI() {

		// set the frame size
		setup();
		toolkit = getToolkit();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		defineVariables();
		setTitle(frame_title.getText());

		setVisible(true);

	}

	public void setup() {
		// set the frame size
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		toolkit = getToolkit();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//determines the font for the JButton/JPanel/JLabel
		Font newButtonFont=new Font("Arial Rounded",Font.BOLD,100);
		UIManager.put("Button.font", newButtonFont);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.background", Color.DARK_GRAY);
		UIManager.put("TextField.font", newButtonFont);
		UIManager.put("Label.font", newButtonFont);
		UIManager.put("Panel.background", Color.WHITE);		
	}

	/**
	 * define_variables function
	 * This function defines the variables for the GUI
	 */
	public void defineVariables() {
		JPanel buttons_area = new JPanel();
		buttons_area.setBorder(new EmptyBorder(10, 10, 10, 10) );
		buttons_area.setLayout(new GridLayout());

		// define title
		frame_title = new JLabel("SAM");
	}

	// create buttons here; this is empty because each menu will override this
	// to implement buttons for specified screens
	public void makeButtons() {
	}
	
	public void defaultPref(Preferences name){
		if (name == null){
			Font newButtonFont=new Font("Arial Rounded",Font.BOLD,50);
			UIManager.put("Button.font", newButtonFont);
			UIManager.put("Button.foreground", Color.DARK_GRAY);
			UIManager.put("Button.background", Color.WHITE);
		}
		else{
			Font newButtonFont=new Font("Arial Rounded",Font.BOLD,name.getFontSize());
			UIManager.put("Button.font", newButtonFont);
			UIManager.put("Button.foreground", name.getFontColour());
			UIManager.put("Button.background", name.getBackgroundColour());
		}
	}
}
