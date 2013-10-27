package util;

import java.io.*;

public class TextFileReader {

	public static String ReadFile(String filePath) {
		BufferedReader reader = null;
		String FileData = "";
		try {
			File helpFile = new File(filePath);
			if(!helpFile.exists()) {
				helpFile.createNewFile();
			} 
			reader = new BufferedReader(new FileReader(filePath));
			String line = null;
			while ((line = reader.readLine()) != null) {
				FileData += line + "\n";
			}
			reader.close();
		} catch (IOException ex) {
			
			ex.printStackTrace();
		} finally {
		}
		return FileData;
	}
}
