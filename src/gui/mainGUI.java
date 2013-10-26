package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
		screen = toolkit.getScreenSize();
		// set the location of the frame to the middle of the screen
		setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2 - getHeight() / 2);
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
	// to implement buttons for specified screen
	public void makeButtons() {
	}
	
	
	
}
