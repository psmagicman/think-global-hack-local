import javax.swing.*;

import users.UserManagementService;
import module.IGame;
import module.Preferences;
import module.Result;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements IGame {

	private void createAndShowGUI() {
		JButton testButton = new JButton("ImTheGame");
		testButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		add(testButton);
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		System.out.println("Got it!");
		System.out.println(UserManagementService.getMainUser());
		Game x = new Game();
		x.createAndShowGUI();
		x.setSize(300, 200);
		x.setVisible(true);
	}
}
