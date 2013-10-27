import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;
	
public class GameWindow extends JFrame
{
	private JLabel frame_title;
	private Toolkit toolkit;
	public Dimension screenSize;
	static public int ScreenWidth;
	static public int ScreenHeight;
	
	public GameWindow() 
	{
		setTitle("Counting");
		setSize(10,15); // default size is 0,0
		setLocation(100,100); // default is 0,0 (top left corner)
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		defineVariables();
		
		toolkit = getToolkit();
		screenSize = toolkit.getScreenSize();		
	}
	
	private void defineVariables() {
		//refactor into here later
	}
	
	public static int GetScreenWorkingWidth() {
		ScreenWidth = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
		ScreenHeight = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
	
}
