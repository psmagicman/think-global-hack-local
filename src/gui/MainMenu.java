package gui;

import gui.mainGUI.ExitAction;

import javax.swing.*;

import module.GameLauncher;
import util.*;

import java.awt.*;
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
		
		//read out instructions
		textToSpeech.speak("Use your mouse or keyboard to select an option");
		//TO DO: highlight menu items and read them
		helpButton.setOpaque(true);
		
		for  (int count=1; count<= 4; count++) {
			try { 
				Thread.sleep(1000); 
				;
				} catch (InterruptedException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
				} 
				switch (count) {
				case 1: 
					textToSpeech.speak("Help");
					break;
				case 2:
					textToSpeech.speak("Games");
					break;
				case 3:
					textToSpeech.speak("Options");
					break;
				case 4:
					textToSpeech.speak("Quit");
					break;
				default: break;
				}
		}		
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