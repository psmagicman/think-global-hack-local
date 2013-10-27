package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import users.*;
import util.DirectoryParser;
import util.textToSpeech;

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
		setLayout(new GridLayout());
		setTitle(frame_title.getText());
		setVisible(true);
	}

	public void setup() {
		// set the frame size
		user = UserManagementService.getInstance().getMainUser();
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		int relativeFont = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth())/40);
		toolkit = getToolkit();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Color bg = Color.DARK_GRAY;
		Color fg = Color.WHITE;
		//determines the font for the JButton/JPanel/JLabel
		Font newButtonFont=new Font("Arial Rounded",Font.BOLD, relativeFont);
		UIManager.put("Button.font", newButtonFont);
		UIManager.put("Button.foreground", fg);
		UIManager.put("Button.background", bg);
		UIManager.put("TextField.font", newButtonFont);
		UIManager.put("Label.font", newButtonFont);
		UIManager.put("List.font", newButtonFont);
		UIManager.put("TextArea.font", newButtonFont);
		UIManager.put("TextArea.foreground", fg);
		UIManager.put("TextArea.background", bg);
		frame_title = new JLabel("SAM");
	}

	// create buttons here; this is empty because each menu will override this
	// to implement buttons for specified screens
	public void makeButtons() {
	}

	public void userPref(User name){
		if (name == null){
			//Use Default settings
			Color bg = Color.DARK_GRAY;
			Color fg = Color.WHITE;
			Font newButtonFont=new Font("Arial Rounded",Font.BOLD,name.getPreferences().getFontSize());
			UIManager.put("Button.font", newButtonFont);
			UIManager.put("Button.foreground", fg);
			UIManager.put("Button.background", bg);
			UIManager.put("TextField.font", newButtonFont);
			UIManager.put("Label.font", newButtonFont);
			UIManager.put("Panel.background", bg);
			UIManager.put("TextArea.font", newButtonFont);
			UIManager.put("TextArea.foreground", fg);
			UIManager.put("TextArea.background", bg);
		}
		else{

			Color fg = name.getPreferences().getTheme().foreground();
			Color bg = name.getPreferences().getTheme().background();
			//Loads User settings
			Font newButtonFont=new Font("Arial Rounded",Font.BOLD,name.getPreferences().getFontSize());
			UIManager.put("Button.font", newButtonFont);
			UIManager.put("Button.foreground", fg);
			UIManager.put("Button.background", bg);
			UIManager.put("TextField.font", newButtonFont);
			UIManager.put("Label.font", newButtonFont);
			UIManager.put("Panel.background", bg);
			UIManager.put("TextArea.font", newButtonFont);
			UIManager.put("TextArea.foreground", fg);
			UIManager.put("TextArea.background", bg);
		}
	}

	public class ExitAction extends AbstractAction {
		JFrame frameToClose;

		ExitAction(JFrame frameToClose) {
			this.frameToClose = frameToClose;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frameToClose.dispose();
			textToSpeech.getInstance().cancelSpeak();
		}
	}

	public class GameButtonAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent action) {
			GameMenu n = new GameMenu();
		}
	}

	public class HelpAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent action) {
			textToSpeech.getInstance().cancelSpeak();
			HelpMenu n = new HelpMenu();
		}
	}
}
