import javax.swing.*;

import users.UserManagementService;
import users.User;
import module.IGame;
import module.Preferences;
import module.Result;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import users.UserManagementService;

public class TestClassLoaderGame extends JFrame implements IGame {

	boolean _status = true;

	private void createAndShowGUI() {
		JButton testButton = new JButton("ImTheGame");
		testButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Set mainUser here
				User mainUser = UserManagementService.getInstance().getMainUser();
				mainUser.setName("Bob");
			}
		});

		add(testButton);
	}
	
	public void dostuff(){
		
	}

	public void playGame() {
		// TODO Auto-generated method stub	
	}
	
	public void startGame() {
		TestClassLoaderGame x = new TestClassLoaderGame();
		x.createAndShowGUI();
		x.setSize(300, 200);
		x.setVisible(true);
		createAndShowGUI();	
	}
}
