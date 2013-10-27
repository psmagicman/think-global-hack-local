/*
 * OptionMenu.java
 * 
 * @author 	Wilbur Yu
 * @date	October 26, 2013
 */

package gui;

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

import java.util.List;

import gui.mainGUI;
import gui.mainGUI.ExitAction;
import users.Preferences;
import users.Themes;
import users.User;
import users.UserManagementService;

public class OptionMenu extends mainGUI {
	
	private JLabel frame_title;
	
	private JButton volumeButton;
	private JButton colorButton;
	private JButton speedButton;
	private JButton fontButton;
	private JButton backButton;
	
	private int _type;
	
	private int _volumeLevel;
	private int _fontSize;
	private int _speed;
	private Themes _theme;
	
	User _mainUser;
	
	public OptionMenu() {
		setup();
		// INIT
		_mainUser = new User("Bob"); //TODO: DELETE
		//_mainUser = UserManagementService.getInstance().getMainUser(); //TODO: UNCOMMENT
		Preferences prefs = _mainUser.getPreferences();
		_volumeLevel = prefs.getVolume();
		_fontSize = prefs.getFontSize();
		_speed = prefs.getSpeed();
		_theme = prefs.getTheme();
		
		setLayout(new GridLayout(5,1));
		makeButtons();
		setVisible(true);
	}
	
	private class Dialog extends JFrame {
		private void createAndShowDialog() {
			JPanel mainPanel = new JPanel(new GridLayout(2,1));
			JPanel topPanel = new JPanel(new GridLayout(1,8));
			JPanel botPanel = new JPanel(new GridLayout(1,2));
			
			for (int i = 1; i <= 8; i++) {
				JButton buttonToAdd = new JButton();
				buttonToAdd.setText(new Integer(i).toString());
				
				this.add(buttonToAdd);
				buttonToAdd.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(new Integer(i).toString()), i);
			
				final int level = i;
				buttonToAdd.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if (_type == 0) {
							System.out.println(_type);
							_volumeLevel = level;
							//TODO: Play sound depending on volume
						}
						else if (_type == 1) { 
							System.out.println(_type);
							switch(level) {
								case 1: break;
								case 2: break;
								case 3: break;
								case 4: break;
								case 5: break;
								case 6: break;
								case 7: break;
								case 8: break;
								default: break;
								// TODO: Display theme on background
							}
						}
						else if (_type == 2) {
							System.out.println(_type);
							_speed = level;
							// TODO: Talk at the said speed in "One one thousand"
						}
						else if (_type == 3) {
							System.out.println(_type);
							_fontSize = level;
							// TODO: Display new font size dynamically
						}
					}
				});
				topPanel.add(buttonToAdd);
			}
			
			JButton okayButton = new JButton("Ok");
			okayButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Preferences prefs = _mainUser.getPreferences();
					if (_type == 0) prefs.setVolume(_volumeLevel);
					else if (_type == 1) prefs.setTheme(_theme);
					else if (_type == 2) prefs.setSpeed(_speed);
					else if (_type == 3) prefs.setFontSize(_fontSize);
					//System.out.println(_volumeLevel); TEST
					_mainUser.setPreferences(prefs);
					
				}
			});
			botPanel.add(okayButton);
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					dispose();
				}
			});
			botPanel.add(cancelButton);
			
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
			_type = 2;
			Dialog dlg = new Dialog();
			dlg.createAndShowDialog();
		}
	}
	
	private class SpeedDialogAction extends AbstractAction {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_type = 3;
			Dialog dlg = new Dialog();
			dlg.createAndShowDialog();
		}
	}
	private void goToMainMenu() {
		MainMenu s = new MainMenu();
	}
	
	public void makeButtons() {
		volumeButton = new JButton("Volume");
		volumeButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('v'), "volumeButtonPressed");
		volumeButton.addActionListener(new VolumeDialogAction());
		volumeButton.getActionMap().put("volumeButtonPressed", new VolumeDialogAction());
		
		colorButton = new JButton("Background Color");
		colorButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('c'), "colorButtonPressed");
		colorButton.addActionListener(new ColorDialogAction());
		colorButton.getActionMap().put("colorButtonPressed", new ColorDialogAction());
		
		speedButton = new JButton("Speed");
		speedButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "speedButtonPressed");
		speedButton.addActionListener(new SpeedDialogAction());
		speedButton.getActionMap().put("speedButtonPressed", new SpeedDialogAction());
		
		fontButton = new JButton("Font Size");
		fontButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('f'), "fontButtonPressed");
		fontButton.addActionListener(new FontDialogAction());
		fontButton.getActionMap().put("fontButtonPressed", new FontDialogAction());
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		backButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('b'), "backButtonPressed");
		
		this.add(volumeButton);
		this.add(colorButton);
		this.add(speedButton);
		this.add(fontButton);
		this.add(backButton);
	}
}
