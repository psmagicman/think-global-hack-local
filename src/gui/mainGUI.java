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
	private User user;
	/** End of Variables **/

	public mainGUI() {
		// set the frame size
		setup();
		user = UserManagementService.mainUser;
		toolkit = getToolkit();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		setTitle(frame_title.getText());
		userPref(user);
		setVisible(true);
	}

	public void setup() {
		// set the frame size
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		toolkit = getToolkit();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//		Color bg = Color.DARK_GRAY;
		//		Color fg = Color.WHITE;
		//determines the font for the JButton/JPanel/JLabel
		//		Font newButtonFont=new Font("Arial Rounded",Font.BOLD,100);
		//		UIManager.put("Button.font", newButtonFont);
		//		UIManager.put("Button.foreground", fg);
		//		UIManager.put("Button.background", bg);
		//		UIManager.put("TextField.font", newButtonFont);
		//		UIManager.put("Label.font", newButtonFont);
		//	UIManager.put("Panel.background", bg);	
		
		frame_title = new JLabel("SAM");
	}
		

	// create buttons here; this is empty because each menu will override this
	// to implement buttons for specified screens
	public void makeButtons() {
	}
	
	//Decides whether to use the default or profile theme
	public void userPref(User name){
		if (name == null){
			Color bg = name.getPreferences().getTheme().background();
			Color fg = name.getPreferences().getTheme().foreground();
			Font newButtonFont=new Font("Arial Rounded",Font.BOLD,name.getPreferences().getFontSize());
			UIManager.put("Button.font", newButtonFont);
			UIManager.put("Button.foreground", fg);
			UIManager.put("Button.background", bg);
			UIManager.put("TextField.font", newButtonFont);
			UIManager.put("Label.font", newButtonFont);
			UIManager.put("Panel.background", bg);	
		}
		else{
			Color bg = name.getPreferences().getTheme().foreground();
			Color fg = name.getPreferences().getTheme().background();
			Font newButtonFont=new Font("Arial Rounded",Font.BOLD,name.getPreferences().getFontSize());
			UIManager.put("Button.font", newButtonFont);
			UIManager.put("Button.foreground", fg);
			UIManager.put("Button.background", bg);
			UIManager.put("TextField.font", newButtonFont);
			UIManager.put("Label.font", newButtonFont);
			UIManager.put("Panel.background", bg);
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
			UserMenu u = new UserMenu();
		}
	}
}
