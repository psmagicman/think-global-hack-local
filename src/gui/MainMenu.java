package gui;

import gui.mainGUI.ExitAction;

import javax.swing.*;

import module.GameLauncher;
import util.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

import users.*;

public class MainMenu extends mainGUI {

	/** Variables **/
	private JButton helpButton;
	private JButton gamesButton;
	private JButton optionButton;
	private JButton quitButton;
	
	private User user;
	private String hexc;
	
	private String helpLabelText = "<html><font color=\"#"+ hexc + "\">" + "H" + "</font>" + "elp" + "</html>";
	private String gameLabelText = "<html><font color=\"#"+ hexc + "\">" + "G" + "</font>" + "ames" +"</font>"+ "</html>";
	private String optionLabelText = "<html><font color=\"#"+ hexc + "\">" + "O" + "</font>" + "ptions" + "</html>";
	private String quitLabelText = "<html><font color=\"#"+ hexc + "\">" + "Q" + "</font>" + "uit" + "</html>";

	/** End of Variables **/

	public MainMenu() {
		//setup();
		setLayout(new GridLayout(2,1));

		user = UserManagementService.getMainUser();

		//creates all the JButtons
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
		hexc = user.getPreferences().getTheme().letter();
		
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
	
	public class ExitAction extends AbstractAction {
		JFrame frameToClose;
		
		ExitAction(JFrame frameToClose) {
			this.frameToClose= frameToClose; 
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frameToClose.dispose();
			
		}
	}
	
	public class GameButtonAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent action) {
			GameMenu n = new GameMenu();
		}
	}
}