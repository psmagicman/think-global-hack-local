import util.*;


public class gameLauncher {
	
	public static void main(String[] args) {
		//System.out.println("hello");
		DirectoryParser test = new DirectoryParser(System.getProperty("user.dir") + "\\Games");
		
		// TESTING DirectoryParser.java TO BE DELETED
		System.out.println("Test print Directories in \\Games:");
		for(String directory : test.getCategoryStrings())
			System.out.println(directory);
		
		System.out.println("\nTest print filenames in first directory:");
		for(String directory : test.getFileStrings(0))
			System.out.println(directory);
		
		System.out.println("\nTest print filenames in first directory with .jar:");
		for(String directory : test.getFileStringsNoJar(0))
			System.out.println(directory);
		
		System.out.println();
		mainGUI new_gui = new mainGUI();
	}
}
