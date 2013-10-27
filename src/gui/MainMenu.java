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
	
	private String helpLabelText = "<html><font color=\"#FF6600\">" + "H" + "</font>" + "elp" + "</html>";
	private String gameLabelText = "<html><font color=\"#FF6600\">" + "G" + "</font>" + "ames" +"</font>"+ "</html>";
	private String optionLabelText = "<html><font color=\"#FF6600\">" + "O" + "</font>" + "ptions" + "</html>";
	private String quitLabelText = "<html><font color=\"#FF6600\">" + "Q" + "</font>" + "uit" + "</html>";

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

		helpButton = new JButton(helpLabelText);
		gamesButton = new JButton(gameLabelText);
		optionButton = new JButton(optionLabelText);		
		quitButton = new JButton(quitLabelText);
		
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