package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import users.*;
import util.DirectoryParser;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class mainGUI extends JFrame {

	/** Variables **/
	private JLabel frame_title;
	private Toolkit toolkit;
	private Dimension screen;
	/** End of Variables **/

	public mainGUI() {
		// set the frame size
		setup();
		toolkit = getToolkit();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		setTitle(frame_title.getText());

		setVisible(true);
	}

	public void setup() {
		// set the frame size
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		toolkit = getToolkit();
		Color bg = Color.DARK_GRAY;
		Color fg = Color.WHITE;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//determines the font for the JButton/JPanel/JLabel
		Font newButtonFont=new Font("Arial Rounded",Font.BOLD,100);
		UIManager.put("Button.font", newButtonFont);
		UIManager.put("Button.foreground", fg);
		UIManager.put("Button.background", bg);
		UIManager.put("TextField.font", newButtonFont);
		UIManager.put("Label.font", newButtonFont);

			
		frame_title = new JLabel("SAM");
	
		UIManager.put("Panel.background", bg);	
	}
		

	// create buttons here; this is empty because each menu will override this
	// to implement buttons for specified screens
	public void makeButtons() {
	}
	//Decides whether to use the default or profile theme
	public void defaultPref(Preferences name){
		if (name == null){
			Font newButtonFont=new Font("Arial Rounded",Font.BOLD,50);
			UIManager.put("Button.font", newButtonFont);
			UIManager.put("Button.foreground", Color.DARK_GRAY);
			UIManager.put("Button.background", Color.WHITE);
		}
		else{
			Font newButtonFont=new Font("Arial Rounded",Font.BOLD,name.getFontSize());
			UIManager.put("Button.font", newButtonFont);
			UIManager.put("Button.foreground", name.getFontColour());
			UIManager.put("Button.background", name.getBackgroundColour());
		}
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
