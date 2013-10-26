package users;

import java.awt.Color;
import java.io.File;

public class Preferences {
	//TODO : change to Enum
	String voice; 
	int speed;
	Color backgroundColour;
	Color fontColour;
	int fontSize;
	//File f;
	
	//TODO: tweak
	Preferences(){
		voice = "Allen";
		speed = 1;
		backgroundColour = Color.green;
		fontColour = Color.yellow;
		fontSize = 12;
	}
	
	/*Preferences(String v, int s, ){
		voice = v;
		speed = s;
		backgroundColour = Color.green;
		fontColour = Color.yellow;
		fontSize = 12;
	}*/
	
	
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Color getBackgroundColour() {
		return backgroundColour;
	}
	public void setBackgroundColour(Color backgroundColour) {
		this.backgroundColour = backgroundColour;
	}
	public Color getFontColour() {
		return fontColour;
	}
	public void setFontColour(Color fontColour) {
		this.fontColour = fontColour;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	
	//Preferences(string fontname, int fontsize, string font)
	
	

}
