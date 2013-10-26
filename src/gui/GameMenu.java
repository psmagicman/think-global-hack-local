/* 
 * GameMenu.java
 * 
 * This file contains the category selection menu
 * The buttons are generated based on the directory names in the Games\ directory
 * 
 * Template taken from "mainGUI.java"
 * 
 * @author Wesley Tsai
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.DirectoryParser;

public class GameMenu extends JFrame {

	private JLabel frame_title;
	private Toolkit toolkit;
	private Dimension screen;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GameMenu() {
		DirectoryParser directoryParser = new DirectoryParser(System.getProperty("user.dir") + "\\Games");
		
		// set the frame size
		setSize(200, directoryParser.getCategoryStrings().size()*50);
		toolkit = getToolkit();
		screen = toolkit.getScreenSize();
		
		// set the location of the frame to the middle of the screen
		setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2 - getHeight() / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(directoryParser.getCategoryStrings().size(), 1));
		defineVariables();
		setTitle(frame_title.getText());
		
		// Pass the list of strings, and add a button to each
		createButtons(directoryParser.getCategoryStrings());
		
		// This function takes out the frame
		//this.setUndecorated(true);
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
	
	private void createButtons(List<String> categoryStrings) {
		for( int i = 0; i < categoryStrings.size(); i++ ) {
			JButton buttonToAdd = new JButton();
			buttonToAdd.setLabel(categoryStrings.get(i));
			buttonToAdd.setSize(20, 5);
			this.add(buttonToAdd);
		}
	}
}
