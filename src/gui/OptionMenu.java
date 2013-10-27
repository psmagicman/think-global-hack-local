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
import users.Preferences;
import users.User;
import users.UserManagementService;

public class OptionMenu extends mainGUI {
	
	private JLabel frame_title;
	
	private JButton volumeButton;
	private JButton colorButton;
	private JButton speedButton;
	private JButton fontButton;
	private JButton backButton;
	
	User _mainUser;
	
	public OptionMenu() {
		setup();
		_mainUser = new User("Bob"); //TODO: DELETE
		//_mainUser = UserManagementService.getInstance().getMainUser(); //TODO: UNCOMMENT
		System.out.println(_mainUser.getName());
		setLayout(new GridLayout(5,1));
		makeButtons();
		setVisible(true);
	}
	
	private class VolumeDialog extends JFrame {
		private void createAndShowVolumeDialog() {
			setName("Edit Volume");
			JPanel mainPanel = new JPanel(new GridLayout(2,1));
			JPanel topPanel = new JPanel(new GridLayout(1,8));
			JPanel botPanel = new JPanel(new GridLayout(1,2));
			
			for (int i = 1; i <= 8; i++) {
				JButton buttonToAdd = new JButton();
				buttonToAdd.setText(new Integer(i).toString());
				
				this.add(buttonToAdd);
				buttonToAdd.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(new Integer(i).toString()), "volumeTo" + i);
				
				buttonToAdd.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						Preferences prefs = _mainUser.getPreferences();
						_mainUser.setPreferences(prefs);
					}
				});
				
				topPanel.add(buttonToAdd);
				
				//TODO: Play sound depending on volume
			}
			
			JButton okayButton = new JButton("Ok");
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
			VolumeDialog dlg = new VolumeDialog();
			dlg.createAndShowVolumeDialog();
		}
	}
	
	private void goToMainMenu() {
		MainMenu s = new MainMenu();
	}
	
	public void makeButtons() {
		volumeButton = new JButton("Volume");
		colorButton = new JButton("Background Color");
		speedButton = new JButton("Speed");
		fontButton = new JButton("Font Size");
		backButton = new JButton("Back");
		
		volumeButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('v'), "volumeButtonPressed");
		volumeButton.addActionListener(new VolumeDialogAction());
		volumeButton.getActionMap().put("volumeButtonPressed", new VolumeDialogAction());
		
		this.add(volumeButton);
		this.add(colorButton);
		this.add(speedButton);
		this.add(fontButton);
		this.add(backButton);
	}
}
