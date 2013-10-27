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

		helpButton = new JButton("<html><font color=\"#FF6600\">" + "H" + "</font>" + "elp" + "</html>");
		gamesButton = new JButton("<html><font color=\"#FF6600\">" + "G" + "</font>" + "ames" +"</font>"+ "</html>");
		optionButton = new JButton("<html><font color=\"#FF6600\">" + "O" + "</font>" + "ptions" + "</html>");		
		quitButton = new JButton("<html><font color=\"#FF6600\">" + "Q" + "</font>" + "uit" + "</html>");		
		quitButton.addActionListener(new ExitAction(this));
		gamesButton.addActionListener(new GameButtonAction());

		gamesButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('g'), "gameButtonPressed");
		gamesButton.getActionMap().put("gameButtonPressed", new GameButtonAction());		
		quitButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('q'), "exitButtonPressed");
		quitButton.getActionMap().put("exitButtonPressed", new ExitAction(this));

		add(helpButton);
		add(gamesButton);
		add(optionButton);
		add(quitButton);
	}
}