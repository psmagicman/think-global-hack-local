/*
 * OptionMenu.java
 * 
 * @author 	Wilbur Yu
 * @date	October 26, 2013
 */

package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.util.ArrayList;
import java.util.List;

import gui.mainGUI;
import gui.mainGUI.ExitAction;
import users.Preferences;
import users.Themes;
import users.User;
import users.UserManagementService;

import util.textToSpeech;

public class OptionMenu extends mainGUI {
	
	private JLabel frame_title;
	
	private JButton volumeButton;
	private JButton colorButton;
	private JButton speedButton;
	private JButton fontButton;
	private JButton backButton;
	
	private int _type;
	
	private int _volumeLevel;
	private int _fontLevel;
	private int _speedLevel;
	private int _themeLevel;
	
	private JButton currentSelectedButton; 
	User _mainUser;
	
	public OptionMenu() {
		// INIT
	_mainUser = UserManagementService.getInstance().getMainUser(); //TODO: UNCOMMENT
	Preferences prefs = _mainUser.getPreferences();
	_volumeLevel = prefs.getVolumeLevel();
	_fontLevel = prefs.getFontLevel();
	_speedLevel = prefs.getSpeedLevel();
	_themeLevel = prefs.getThemeLevel();
	
	setup();
	userPref(_mainUser);
	setLayout(new GridLayout(5,1));
	makeButtons();
	setVisible(true);
}

	private void saveOptions()
	{
		Preferences prefs = _mainUser.getPreferences();
		prefs.setVolume(_volumeLevel);
		prefs.setSpeed(_speedLevel);
		prefs.setFontSize(_fontLevel);
		prefs.setTheme(_themeLevel);
		UserManagementService.getInstance().getMainUser().setPreferences(prefs);
//		System.out.println("PREFS:\n" + "volume: " + UserManagementService.getInstance().getMainUser().getPreferences().getVolume() + "\nSpeed: " + UserManagementService.getInstance().getMainUser().getPreferences().getSpeed() 
//				+ "\nFont size: " + UserManagementService.getInstance().getMainUser().getPreferences().getFontSize() + 
//				"\nTheme: " + UserManagementService.getInstance().getMainUser().getPreferences().getTheme()); 
		
		System.out.println("theme level: " + _themeLevel);
		UserManagementService.getInstance().saveMainUser();

	}

	public void refreshUI()
	{
		setVisible(false);
		remove(volumeButton);
		remove(colorButton);
		remove(speedButton);
		remove(fontButton);
		remove(backButton);
		userPref(UserManagementService.getInstance().getMainUser());
		makeButtons();
		setVisible(true);
	}
	
	private class Dialog extends JFrame {
		private void createAndShowDialog() {
			JPanel mainPanel = new JPanel(new GridLayout(2,1));
			int size = 8;
			if (_type == 1) 
				size = 5;
			else if (_type == 3) 
				size = 3;
			JPanel topPanel = new JPanel(new GridLayout(1,size));
			JPanel botPanel = new JPanel(new GridLayout(1,2));
			ArrayList<JButton> mainButtonList = new ArrayList<JButton>();
			for (int i = 1; i <= size; i++) {
				JButton buttonToAdd = new JButton();
				if (_type == 1) // background colour
				{
					String buttonTitle;
					switch(i) {
						case 1: buttonTitle = "Gray"; break;
						case 2: buttonTitle = "White"; break;
						case 3: buttonTitle = "Blue"; break;
						case 4: buttonTitle = "Purple"; break;
						case 5: buttonTitle = "Pink"; break;
						default: buttonTitle = ""; break;
					}
					if(i == _themeLevel)
					{
						buttonToAdd.setText(">" + buttonTitle);
						currentSelectedButton = buttonToAdd;
					}
					else
						buttonToAdd.setText(buttonTitle);
				}
				else if(_type == 3) // font size
				{
					String buttonTitle;
					switch(i) {
						case 1: buttonTitle = "Small"; break; 
						case 2: buttonTitle = "Medium"; break; 
						case 3: buttonTitle = "Large"; break; 
						default: buttonTitle = ""; break;
					}
					if(i==_fontLevel)
					{
						buttonToAdd.setText(">" + buttonTitle);
						currentSelectedButton = buttonToAdd;
					}
					else 
						buttonToAdd.setText(buttonTitle);
				}
				else
				{
					if(_type == 2 && i == _speedLevel)
					{
						buttonToAdd.setText(">" + new Integer(i).toString());
						currentSelectedButton = buttonToAdd;
					}
					else if(_type == 0 && i == _volumeLevel)
					{
						buttonToAdd.setText(">" + new Integer(i).toString());
						currentSelectedButton = buttonToAdd;
					}
					else 
						buttonToAdd.setText(new Integer(i).toString());
				}
				this.add(buttonToAdd);
				mainButtonList.add(buttonToAdd);
				buttonToAdd.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(new Integer(i).toString()), i);
			
				final int level = i;
				buttonToAdd.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton button = (JButton)e.getSource();
						if(currentSelectedButton.getText().charAt(0) == '>')
						{
							currentSelectedButton.setText(currentSelectedButton.getText().substring(1));
						}
						if(button.getText().charAt(0) != '>')
							button.setText(">" + button.getText());
						currentSelectedButton = button;
						if (_type == 0) {
							_volumeLevel = level;
							float volToPlayAt = 1; 
							switch(_volumeLevel)
							{
								case 1: volToPlayAt = (float)0.65; break;
								case 2: volToPlayAt = (float)0.70; break;
								case 3: volToPlayAt = (float)0.75; break;
								case 4: volToPlayAt = (float)0.8; break;
								case 5: volToPlayAt = (float)0.85; break; 
								case 6: volToPlayAt = (float)0.9; break;
								case 7: volToPlayAt = (float)0.95; break;
								case 8: volToPlayAt = (float)1; break;
							}
							textToSpeech.getInstance().setVolume(volToPlayAt);
							textToSpeech.getInstance().setWPM(100);
							textToSpeech.getInstance().speakNow(new Integer(level).toString());
						}
						else if (_type == 1) {
							_themeLevel = level;
							
						}
						else if (_type == 2) {
							_speedLevel = level;
							textToSpeech.getInstance().setWPM(_speedLevel*10+100);
							textToSpeech.getInstance().setVolume((float)1.0);
							textToSpeech.getInstance().speakNow(new Integer(level).toString() + " poody poody poody poody");
						}
						else if (_type == 3) {
							_fontLevel = level;
						}
					}
				});
				topPanel.add(buttonToAdd);
			}
			
			JButton okayButton = new JButton("OK");
			okayButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Preferences prefs = _mainUser.getPreferences();
					if (_type == 0) prefs.setVolume(_volumeLevel);
					else if (_type == 1) prefs.setTheme(_themeLevel);
					else if (_type == 2) prefs.setSpeed(_speedLevel);
					else if (_type == 3) prefs.setFontSize(_fontLevel);
					saveOptions();
					setVisible(false);
					refreshUI();
				}
			});
			botPanel.add(okayButton);
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					saveOptions();
					setVisible(false);
					dispose();
				}
			});
			botPanel.add(cancelButton);
			mainButtonList.add(okayButton);
			mainButtonList.add(cancelButton);
			new ButtonPane(mainButtonList);
			setContentPane(mainPanel);
			mainPanel.add(topPanel);
			mainPanel.add(botPanel);
			setSize(Toolkit.getDefaultToolkit().getScreenSize());
			setVisible(true);
		}
	}
	
	private class VolumeDialogAction extends AbstractAction {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_type = 0;
			Dialog dlg = new Dialog();
			dlg.createAndShowDialog();
		}
	}
	
	private class ColorDialogAction extends AbstractAction {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_type = 1;
				Dialog dlg = new Dialog();
				dlg.createAndShowDialog();
			}
		}
	
	private class FontDialogAction extends AbstractAction {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_type = 3;
			Dialog dlg = new Dialog();
			dlg.createAndShowDialog();
		}
	}
	
	private class SpeedDialogAction extends AbstractAction {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_type = 2;
			Dialog dlg = new Dialog();
			dlg.createAndShowDialog();
		}
	}
	
	private class BackDialogAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			_type = 4;
			Dialog dlg = new Dialog();
			dlg.createAndShowDialog();
			saveOptions();			
			setVisible(false);
			dispose();
			MainMenu m = new MainMenu();
		}
		
	}
	
	public void makeButtons() {
		String hexc = UserManagementService.getInstance().getMainUser().getPreferences().getTheme().letter();
		volumeButton = new JButton("<html><font color=\"#"+ hexc + "\">" + "V" + "</font>" + "olume" + "</html>");
		volumeButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('v'), "volumeButtonPressed");
		volumeButton.addActionListener(new VolumeDialogAction());
		volumeButton.getActionMap().put("volumeButtonPressed", new VolumeDialogAction());
		volumeButton.setName("volume");
		
		colorButton = new JButton("<html><font color=\"#"+ hexc + "\">" + "B" + "</font>" + "ackground Colour" + "</html>");
		colorButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('b'), "colorButtonPressed");
		colorButton.addActionListener(new ColorDialogAction());
		colorButton.getActionMap().put("colorButtonPressed", new ColorDialogAction());
		colorButton.setName("background color");
		
		speedButton = new JButton("<html><font color=\"#"+ hexc + "\">" + "S" + "</font>" + "peed" + "</html>");
		speedButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "speedButtonPressed");
		speedButton.addActionListener(new SpeedDialogAction());
		speedButton.getActionMap().put("speedButtonPressed", new SpeedDialogAction());
		speedButton.setName("speed");
		
		fontButton = new JButton("<html><font color=\"#"+ hexc + "\">" + "F" + "</font>" + "ont Size" + "</html>");
		fontButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('f'), "fontButtonPressed");
		fontButton.addActionListener(new FontDialogAction());
		fontButton.getActionMap().put("fontButtonPressed", new FontDialogAction());
		fontButton.setName("font size");
		
		backButton = new JButton("<html><font color=\"#"+ hexc + "\">" + "E" + "</font>" + "xit" + "</html>");
		backButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveOptions();			
				setVisible(false);
				dispose();
				MainMenu m = new MainMenu();
			}
		});
		backButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('e'), "backButtonPressed");
		backButton.addActionListener(new BackDialogAction());
		backButton.getActionMap().put("backButtonPressed", new BackDialogAction());
		backButton.setName("exit");
			
		ArrayList<JButton> mainButtonList = new ArrayList<JButton>();
		mainButtonList.add(volumeButton);
		mainButtonList.add(colorButton);
		mainButtonList.add(speedButton);
		mainButtonList.add(fontButton);
		mainButtonList.add(backButton);
		new ButtonPane(mainButtonList);
		
			this.add(volumeButton);
			this.add(colorButton);
			this.add(speedButton);
			this.add(fontButton);
			this.add(backButton);
		}
	}
