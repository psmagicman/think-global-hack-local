import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import java.util.*;

public class NameGet extends JFrame {
	
	/** Variables **/
	private JLabel frame_title;
	private Toolkit toolkit;
	private Dimension screen;
	
	/** End of Variables **/
	
	public NameGet() {
		
		// set the frame size
		setSize(300, 200);
		toolkit = getToolkit();
		screen = toolkit.getScreenSize();
		// set the location of the frame to the middle of the screen
		setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2 - getHeight() / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		defineVariables();
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
		buttons_area.setLayout(new GridLayout(4, 1));
		
		// define the buttons and title
		frame_title = new JLabel("Hackathon!");

		
		// add the radios to a button group

		
		// add the buttons to the frame
		
		
	}
	
	
	private String nameGet(String fileName){
		File f = new File(fileName);
		return f.getName();
	}
	
	public static void main(String[] args){
		NameGet ng = new NameGet();
	}
}
