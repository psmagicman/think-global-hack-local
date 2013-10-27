package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import module.GameLauncher;
import util.DirectoryParser;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainMenu extends mainGUI {
	
	/** Variables **/
	private JButton helpButton;
	private JButton gamesButton;
	private JButton optionButton;
	private JButton quitButton;
	/** End of Variables **/
	
	public MainMenu() {
		setup();
		setLayout(new GridLayout(2,2));
		defineVariables();
		makeButtons();
		// setUndecorated(true); // hides top bar
		setVisible(true);
	}
	
	@Override
	public void makeButtons() {
		
		//ImageIcon help = new ImageIcon("Images/H-icon.png");
		helpButton = new JButton("<html><font color=\"#FF6600\">" + "H" + "</font>" + "elp" + "</html>");
		
		gamesButton = new JButton("<html><font color=\"#FF6600\">" + "G" + "</font>" + "ames" +"</font>"+ "</html>");
		gamesButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('g'), "gameButtonPressed");
		gamesButton.getActionMap().put("gameButtonPressed", new GameButtonAction());
		
		optionButton = new JButton("<html><font color=\"#FF6600\">" + "O" + "</font>" + "ptions" + "</html>");
		quitButton = new JButton("<html><font color=\"#FF6600\">" + "Q" + "</font>" + "uit" + "</html>");
		add(helpButton);
		add(gamesButton);
		add(optionButton);
		add(quitButton);
	}
	
	private class GameButtonAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent action) {
			GameMenu n = new GameMenu();
		}
	}
	

	
}