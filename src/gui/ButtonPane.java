package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPane extends JPanel {

	private int buttonLocation;
	private int listSize;
	private ArrayList<JButton> buttonList;
	
	public ButtonPane(ArrayList<JButton> buttonList) {
		listSize = buttonList.size();
		this.buttonList = buttonList;		
		buttonLocation = 0;
		System.out.println(listSize);
		for(int i = 0; i < buttonList.size(); i++) {
			final int keyCount = i;
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
		System.out.println(buttonLocation);
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
		System.out.println(buttonLocation);
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
}
