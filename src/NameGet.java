import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import java.util.*;

public class NameGet extends JFrame {

	/** Variables **/

	/** End of Variables 
	 * @return **/

	public NameGet() {
		
		/**Retrieve the file name from the directory
		 * Input: "C:\Hello\AnotherFolder\The File Name.PDF"
		 * Return: "The File Name.PDF"
		 */

		//		String nameGet(String fileName){
		//			File f = new File(fileName);
		//			return f.getName();
		//		}

		// nameToButton();
	}
	/**
	 * @param fileName
	 * Input: a list of string of file names 
	 * Return: a list of JButton with the appropriate names
	 */
	private void nameToButton(ArrayList<String> fileName){
		for(int i = 0; i < fileName.size(); i++){
			JButton fn = new JButton(fileName.get(i));
			add(fn);
		}
	}
}