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

	public String NameGet() {

		/**Retrieve the file name from the directory
		 * Input: "C:\Hello\AnotherFolder\The File Name.PDF"
		 * Return: "The File Name.PDF"
		 */

		String nameGet(String fileName){
			File f = new File(fileName);
			return f.getName();
		}

	}
}