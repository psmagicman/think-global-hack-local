package gui;

import gui.mainGUI.ExitAction;

import javax.swing.*;

import module.GameLauncher;
import users.UserManagementService;
import util.*;

import java.awt.*;
import java.awt.event.ActionEvent;
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
		defaultPref(UserManagementService.getInstance().getMainUser().getPreferences());
		setLayout(new GridLayout(2,1));
		textToSpeech.getInstance().setWPM(UserManagementService.getInstance().getMainUser().getPreferences().getSpeed());
		textToSpeech.getInstance().setVolume(UserManagementService.getInstance().getMainUser().getPreferences().getVolume());
		setTitle("Welcome: " + UserManagementService.getInstance().getMainUser().getName()); // 
		makeButtons();
		// setUndecorated(true); // hides top bar
		setVisible(true);
		
		//read out instructions
		textToSpeech.getInstance().speak("Use your mouse or keyboard to select an option");
		//TO DO: highlight menu items and read them
		helpButton.setOpaque(true);
		
		for  (int count=1; count<= 4; count++) {
			try { 
				Thread.sleep(500); 
				;
				} catch (InterruptedException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
				} 
				switch (count) {
				case 1: 
					textToSpeech.getInstance().speak("Help");
					break;
				case 2:
					textToSpeech.getInstance().speak("Games");
					break;
				case 3:
					textToSpeech.getInstance().speak("Options");
					break;
				case 4:
					textToSpeech.getInstance().speak("Quit");
					break;
				default: break;
				}
		}		
	}

	@Override
	public void makeButtons() {

		//ImageIcon help = new ImageIcon("Images/H-icon.png");

		helpButton = new JButton(helpLabelText);
		gamesButton = new JButton(gameLabelText);
		optionButton = new JButton(optionLabelText);		
		quitButton = new JButton(quitLabelText);
		
		quitButton.addActionListener(new QuitAction());
		gamesButton.addActionListener(new GameButtonAction());
		optionButton.addActionListener(new OptionButtonAction());

		gamesButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('g'), "gameButtonPressed");
		gamesButton.getActionMap().put("gameButtonPressed", new GameButtonAction());

		optionButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('o'), "optionButtonPressed");
		optionButton.getActionMap().put("optionButtonPressed", new OptionButtonAction());
		
		quitButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('q'), "exitButtonPressed");
		quitButton.getActionMap().put("exitButtonPressed", new QuitAction());

		add(helpButton);
		add(gamesButton);
		add(optionButton);
		add(quitButton);
	}
	
	private class QuitAction extends AbstractAction {
		QuitAction()
		{}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(EXIT_ON_CLOSE);
		}
	}
	
	private class GameButtonAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent action) {
			GameMenu n = new GameMenu();
		}
	}
	
	public class OptionButtonAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent action) {
			OptionMenu n = new OptionMenu();
		}
	}
}