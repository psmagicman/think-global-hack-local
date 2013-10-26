/* 
 * CategoryMenu.java
 * 
 * @author GuiTeam
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

public class CategoryMenu extends mainGUI {
	private JLabel frame_title;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CategoryMenu(DirectoryParser directoryParser, int categoryIndex) {
		setup();
		setLayout(new GridLayout(directoryParser.getFileStringsNoJar(categoryIndex).size(), 1));
		defineVariables();		
		// Pass the list of strings, and add a button to each
		createButtons(directoryParser.getFileStringsNoJar(categoryIndex));
		
		// This function takes out the frame
		//this.setUndecorated(true);
		setVisible(true);
	}

	private void createButtons(List<String> gameStrings) {
		for( int i = 0; i < gameStrings.size(); i++ ) {
			JButton buttonToAdd = new JButton();
			buttonToAdd.setLabel(gameStrings.get(i));
			buttonToAdd.setSize(20, 5);
			this.add(buttonToAdd);
		}
	}
}
