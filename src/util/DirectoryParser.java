/*
 * DirectoryParser.java
 * 
 * Returns directories in "\Games" as strings to be used as categories
 * Returns filenames in "\Games\<categoryindex>" to be used as game names. Contains option to return with .jar prefix and without 
 * 
 * @author Wesley Tsai
 */
package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryParser {
	private File[] categoryDirectories;
	public String directoryName;
	
	public DirectoryParser(String directoryName) {
		this.directoryName = directoryName;
		File directory = new File(directoryName);
		if (directory.exists() == false) {
			directory.mkdir();
			new File(directoryName + "/AddGamesHere").mkdir();
		}
		categoryDirectories = directory.listFiles();
		
	}
	
	public List<String> getCategoryStrings() {
		List<String> categories = new ArrayList<String>();
		
		if (categoryDirectories != null) {
			for (File file : categoryDirectories){
	            if (file.isDirectory()){
	            	categories.add(file.getName());
	            }
	        }
		}

		return categories;
	}
	
	/*
	 * This method returns the filenames of a category directory
	 * @param the index of the category directory you wish to list files of
	 */
	public List<String> getFileStrings(int index) {
		List<String> fileNames = new ArrayList<String>();
		
		File directory = new File(directoryName + "/" + categoryDirectories[index].getName());
		
		if (directory.listFiles() != null) {
			for (File file : directory.listFiles()){
	            if (file.isFile()){
	            	fileNames.add(file.getName());
	            }
	        }
		}
		
		return fileNames;
	}
	
	public List<String> getFileStringsNoJar(int index) {
		List<String> fileNames = new ArrayList<String>();
		
		File directory = new File(directoryName + "/" + categoryDirectories[index].getName());
		
		for (File file : directory.listFiles()){
            if (file.isFile()){
            	fileNames.add(file.getName().substring(0, file.getName().length() - 4));
            }
        }
		
		return fileNames;
	}
}
