/* 
 * CategoryMenu.java
 * 
 * @author GuiTeam
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import module.GameLauncher;
import util.DirectoryParser;

public class CategoryMenu extends mainGUI {
	private JLabel frame_title;
	private JPanel contentPane;
	private DirectoryParser directoryParser;
	private int categoryIndex;

	/**
	 * Create the frame.
	 */
	public CategoryMenu(DirectoryParser directoryParser, int categoryIndex) {
		this.directoryParser = directoryParser;
		this.categoryIndex = categoryIndex;
		setup();
		setLayout(new GridLayout(directoryParser.getFileStringsNoJar(categoryIndex).size(), 1));
		defineVariables();		
		// Pass the list of strings, and add a button to each
		createButtons(directoryParser.getFileStringsNoJar(categoryIndex));
		// This function takes out the frame
		//this.setUndecorated(true);
		setVisible(true);
	}
	
	private void createButtons(List<String> gameStrings) {
		for( int i = 0; i < gameStrings.size(); i++ ) {
			JButton buttonToAdd = new JButton();
			buttonToAdd.setText(gameStrings.get(i));
			buttonToAdd.setSize(20, 5);
			this.add(buttonToAdd);; 
			buttonToAdd.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(new Integer(i).toString()), "launch" + gameStrings.get(i) + "Game");
			String gameToLaunch = directoryParser.directoryName + "/" + directoryParser.getCategoryStrings().get(categoryIndex) + "/" + gameStrings.get(i) + ".jar";
			buttonToAdd.getActionMap().put("launch" + gameStrings.get(i) + "Game", new GameLaunchAction(gameToLaunch));
		}
	}
	
	private class GameLaunchAction extends AbstractAction {
		
		private String gameToLaunch;
		
		public GameLaunchAction(String gameToLaunch) {
			this.gameToLaunch = gameToLaunch;
		}
		
		public void actionPerformed(ActionEvent action) {
			GameLauncher newGame = new GameLauncher();
			newGame.launchGame(gameToLaunch);
		}
	}
	

}
