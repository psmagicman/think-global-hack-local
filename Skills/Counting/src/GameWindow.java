import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;
	
public class GameWindow extends JFrame
{
	private JLabel frame_title;
	private Toolkit toolkit;
	public Dimension screenSize;
	
	public GameWindow() 
	{
		setTitle("Counting");
		setSize(300,200); // default size is 0,0
		setLocation(10,200); // default is 0,0 (top left corner)
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		defineVariables();
		
		toolkit = getToolkit();
		screenSize = toolkit.getScreenSize();
		
	}
	
	private void defineVariables() {
		//refactor into here later
	}
	
}
