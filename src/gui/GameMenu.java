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

public class GameMenu extends mainGUI {
	private JLabel frame_title;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GameMenu() {
		DirectoryParser directoryParser = new DirectoryParser(System.getProperty("user.dir") + "/Games");
		
		setup();
		setLayout(new GridLayout(directoryParser.getCategoryStrings().size(), 1));
		defineVariables();		
		// Pass the list of strings, and add a button to each
		createButtons(directoryParser.getCategoryStrings());
		
		// This function takes out the frame
		//this.setUndecorated(true);
		setVisible(true);
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
