package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;


import users.User;
import users.UserManagementService;
import util.textToSpeech;
import gui.mainGUI;

public class ButtonPane extends JPanel {

	private int buttonLocation;
	private int listSize;
	private ArrayList<JButton> buttonList;
	
	public ButtonPane(ArrayList<JButton> buttonList) {
		listSize = buttonList.size();
		this.buttonList = buttonList;		
		buttonLocation = 0;
		for(int i = 0; i < buttonList.size(); i++) {
			final int keyCount = i;
			buttonList.get(i).addFocusListener(focusButton);
			buttonList.get(keyCount).addMouseListener(mouseEars);
			buttonList.get(i).addKeyListener(keyboCommand);
			buttonList.get(keyCount).addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_UP:
						cycleUp(buttonLocation);
						break;
					case KeyEvent.VK_DOWN:
						cycleDown(buttonLocation);
						break;
					default:
						break;
					}
					
				}
			});
		}
	}
	
	private void cycleDown(int indexLocation) {
		
		if(indexLocation < listSize && indexLocation >= 0) {
			indexLocation++;
		}
		if(indexLocation >= listSize) {
			indexLocation = 0;
		}
		buttonLocation = indexLocation;
		buttonList.get(indexLocation).requestFocus();
	}
	
	private void cycleUp(int indexLocation) {
		
		if(indexLocation < listSize && indexLocation >= 0) {
			indexLocation--;
		}
		if(indexLocation < 0) {
			indexLocation = listSize - 1;
		}
		buttonLocation = indexLocation;
		buttonList.get(indexLocation).requestFocus();
	}
	
	private KeyListener keyboCommand = new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			
			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
				((JButton) e.getComponent()).doClick();
			}

		}
	};
	
	private FocusListener focusButton = new FocusListener() {
		@Override
		public void focusGained(FocusEvent e) {
			((JButton) e.getComponent()).setBackground(Color.ORANGE);
			if( ((JButton) e.getComponent()).getName().length() > 0)
				textToSpeech.getInstance().speakNow(((JButton) e.getComponent()).getName());
		}
		
		@Override
		public void focusLost(FocusEvent e) {
			((JButton) e.getComponent()).setBackground(UserManagementService.getInstance().getMainUser().getPreferences().getTheme().background());
		}
	};
	
	private MouseListener mouseEars = new MouseListener() {
		/**
		 * This function will respond to mouse clicks on the buttons
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		/**
		 * This function will respond to the mouse hovering over the button
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Add speech on hover?
			
		}

		/**
		 * This function will respond to the mouse holding the button down
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			//((JButton) e.getComponent()).setBackground(Color.ORANGE);
		}

		/**
		 * This function will respond to the mouse releasing the button press
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			//((JButton) e.getComponent()).setBackground(Color.DARK_GRAY);			
		}

		/**
		 * This function will respond to the mouse leaving the button area
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Kill the speech on exit?
			
		}
	};
	
}
