import gui.mainGUI;
import util.*;


public class GameLauncherMenu {
	
	public static void main(String[] args) {
		//System.out.println("hello");
		DirectoryParser directoryParser = new DirectoryParser(System.getProperty("user.dir") + "\\Games");
		
		// TESTING DirectoryParser.java TO BE DELETED
		System.out.println("Test print Directories in \\Games:");
		for(String directory : directoryParser.getCategoryStrings())
			System.out.println(directory);
		
		System.out.println("\nTest print filenames in first directory:");
		for(String directory : directoryParser.getFileStrings(0))
			System.out.println(directory);
		
		System.out.println("\nTest print filenames in first directory with .jar:");
		for(String directory : directoryParser.getFileStringsNoJar(0))
			System.out.println(directory);
		
		System.out.println();
		mainGUI new_gui = new mainGUI();
		textToSpeech.speak("hello"); // demo sound
	}
}
