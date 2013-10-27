package gui;

import gui.mainGUI.ExitAction;
import gui.mainGUI.GameButtonAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.*;
import javax.swing.text.JTextComponent;

import module.GameLauncher;
import util.DirectoryParser;
import util.TextFileReader;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HelpMenu extends mainGUI {

	/** Variables **/
	private JButton quitButton;
	private SelectionTextPane helpText;
	private JScrollPane helpScroll;

	/** End of Variables **/

	public HelpMenu() {
		setup();
		setLayout(new GridLayout(2, 1));
		makeHelpText();
		makeButtons();
		setVisible(true);
	}

	@Override
	public void makeButtons() {

		// ImageIcon help = new ImageIcon("Images/H-icon.png");
		quitButton = new JButton("Quit");

		quitButton.addActionListener(new ExitAction(this));
		quitButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke('q'), "exitButtonPressed");
		quitButton.getActionMap()
				.put("exitButtonPressed", new ExitAction(this));

		add(quitButton);
	}

	public void makeHelpText() {
		String helpInfo = TextFileReader.ReadFile("./help.txt");
		helpText = new SelectionTextPane(helpInfo);
		helpScroll = new JScrollPane(helpText,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		helpText.setCaretPosition(0);
		add(helpScroll);
	}
}