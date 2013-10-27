import javax.swing.*;

import module.GameLauncher;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class TestClassLoaderMain extends JFrame {

	private void createAndShowGUI() {
		JButton testButton = new JButton("test");
		testButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameLauncher gameLauncher = new GameLauncher();
//				gameLauncher.launchGame("Users/triciajose/Documents/think-global-hack-local/Games/game.jar");
				gameLauncher.launchGame("/Games/Category 1/game.jar");
			}
		});

		add(testButton);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestClassLoaderMain x = new TestClassLoaderMain();
		x.createAndShowGUI();
		x.setSize(300, 200);
		x.setVisible(true);
	}

}
