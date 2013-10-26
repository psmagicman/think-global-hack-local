import gui.mainGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainMenu extends mainGUI {
	
	/** Variables **/
	private JLabel frame_title;
	private Toolkit toolkit;
	private Dimension screen;
	private JButton helpButton;
	private JButton gamesButton;
	private JButton optionButton;
	private JButton quitButton;
	/** End of Variables **/
	
	public MainMenu() {
		
		setup();
		setLayout(new GridLayout(4,1));
		defineVariables();
		makeButtons();
		setTitle(frame_title.getText());
		setUndecorated(true);
		setVisible(true);
		
	}
	
	@Override
	public void makeButtons() {
		helpButton = new JButton("Help");
		gamesButton = new JButton("Games");
		optionButton = new JButton("Options");
		quitButton = new JButton("Quit");
		add(helpButton);
		add(gamesButton);
		add(optionButton);
		add(quitButton);
	}
	
	public static void main(String[] args){
		UserMenu u = new UserMenu();
		MainMenu s = new MainMenu();
	}
	
}