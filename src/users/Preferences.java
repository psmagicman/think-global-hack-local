package users;

import java.awt.Color;
import java.io.File;

public class Preferences {
	
	String voice; 
	int speed;
	Themes theme;
	//Color backgroundColour;
	//Color fontColour;
	int fontSize;
	int volume;

	public Preferences(){
		voice = "kevin";
		speed = 100;
		//backgroundColour = Color.green;
		//fontColour = Color.yellow;
		theme = Themes.DEFAULT;
		fontSize = 27;
		volume = 10;
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
	
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		if(fontSize > 27){
			this.fontSize = fontSize;
		}		
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public int getVolume(){
		return this.volume;
	}
	
	public Themes getTheme() {
		return this.theme;
	}
	
	public void setTheme(Themes theme) {
		this.theme = theme;
	}

	

	
	
	

}
