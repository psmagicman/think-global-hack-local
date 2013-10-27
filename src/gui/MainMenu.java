package gui;

import gui.mainGUI.ExitAction;

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
		helpButton = new JButton("Help");
		gamesButton = new JButton("Games");
		optionButton = new JButton("Options");
		quitButton = new JButton("Quit");
		
		quitButton.addActionListener(new ExitAction(this));
		gamesButton.addActionListener(new GameButtonAction());
		helpButton.addActionListener(new HelpAction());
		
		gamesButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('g'), "gameButtonPressed");
		gamesButton.getActionMap().put("gameButtonPressed", new GameButtonAction());		
		quitButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('q'), "exitButtonPressed");
		quitButton.getActionMap().put("exitButtonPressed", new ExitAction(this));
		helpButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('h'), "helpButtonPressed");
		helpButton.getActionMap().put("helpButtonPressed", new HelpAction());
		
		add(helpButton);
		add(gamesButton);
		add(optionButton);
		add(quitButton);
	}
}