package gui;

import gui.mainGUI.ExitAction;

import javax.swing.*;

import module.GameLauncher;
import users.UserManagementService;
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

	private String helpLabelText;
	private String gameLabelText;
	private String optionLabelText;
	private String quitLabelText;


	/** End of Variables **/

	public MainMenu() {
		setup();
		user = UserManagementService.getInstance().getMainUser();
		userPref(user);
		//setup();
		setLayout(new GridLayout(2,1));
		textToSpeech.getInstance().setWPM(UserManagementService.getInstance().getMainUser().getPreferences().getSpeed());
		textToSpeech.getInstance().setVolume(UserManagementService.getInstance().getMainUser().getPreferences().getVolume());
		setTitle("Welcome: " + UserManagementService.getInstance().getMainUser().getName()); // 
		user = UserManagementService.getInstance().getMainUser();

		//creates all the JButtons
		makeButtons();
		setVisible(true);
		//read out instructions
		textToSpeech.getInstance().speakNonInterrupted("Use your mouse or keyboard to select an option");
		
		//TO DO: highlight menu items and read them
		helpButton.setOpaque(true);

	}

		@Override
		public void makeButtons() {
		//ImageIcon help = new ImageIcon("Images/H-icon.png");
		updateStrings(UserManagementService.getInstance().getMainUser().getPreferences().getTheme().letter());
		hexc = user.getPreferences().getTheme().letter();
	
		helpButton = new JButton(helpLabelText);
		gamesButton = new JButton(gameLabelText);
		optionButton = new JButton(optionLabelText);		
		quitButton = new JButton(quitLabelText);
		
		optionButton.addActionListener(new OptionButtonAction());
		quitButton.addActionListener(new QuitAction());
		gamesButton.addActionListener(new GameButtonAction());
		helpButton.addActionListener(new HelpAction());
		
		gamesButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('g'), "gameButtonPressed");
		gamesButton.getActionMap().put("gameButtonPressed", new GameButtonAction());

		optionButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('o'), "optionButtonPressed");
		optionButton.getActionMap().put("optionButtonPressed", new OptionButtonAction());

		quitButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('q'), "exitButtonPressed");
		quitButton.getActionMap().put("exitButtonPressed", new QuitAction());
		
		helpButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('h'), "helpButtonPressed");
		helpButton.getActionMap().put("helpButtonPressed", new HelpAction());
		
		helpButton.setName("help");
		gamesButton.setName("games");
		optionButton.setName("options");
		quitButton.setName("quit");
		
		// the part below adds button cycling with arrow keys
		ArrayList<JButton> mainButtonList = new ArrayList<JButton>();
		mainButtonList.add(helpButton);
		mainButtonList.add(gamesButton);
		mainButtonList.add(optionButton);
		mainButtonList.add(quitButton);
		
		new ButtonPane(mainButtonList);
		
		add(helpButton);
		add(gamesButton);
		add(optionButton);
		add(quitButton);
		}
			
	private void updateStrings(String hexc)
	{
		helpLabelText = "<html><font color=\"#"+ hexc + "\">" + "H" + "</font>" + "elp" + "</html>";
		gameLabelText = "<html><font color=\"#"+ hexc + "\">" + "G" + "</font>" + "ames" +"</font>"+ "</html>";
		optionLabelText = "<html><font color=\"#"+ hexc + "\">" + "O" + "</font>" + "ptions" + "</html>";
		quitLabelText = "<html><font color=\"#"+ hexc + "\">" + "Q" + "</font>" + "uit" + "</html>";
	}
	
	private class QuitAction extends AbstractAction {
		QuitAction()
		{}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(EXIT_ON_CLOSE);
		}
	}
	
	public class GameButtonAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent action) {
			textToSpeech.getInstance().cancelSpeak();
			GameMenu n = new GameMenu();
		}
	}
	
	public class OptionButtonAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent action) {
			textToSpeech.getInstance().cancelSpeak();
			OptionMenu n = new OptionMenu();
			setVisible(false);
			dispose();
		}
	}
}
