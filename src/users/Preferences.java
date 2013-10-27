package users;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;

import util.textToSpeech;

public class Preferences {
	
	String voice; 
	int speed;
	int speedLevel;
	Themes theme;
	int themeLevel;
	int fontSize;
	int fontLevel;
	float volume;
	int volumeLevel; 

	public Preferences(){
		voice = "kevin";
		speedLevel = 8;
		setSpeed(speedLevel);
		fontLevel = 1; 
		setFontSize(fontLevel);
		volumeLevel = 8;
		setVolume(volumeLevel);
		themeLevel = 1;
		setTheme(themeLevel);
	}
	
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public int getSpeed() {
		return this.speed;
	}
	
	public int getSpeedLevel()
	{
		return this.speedLevel;
	}
	
	public void setSpeed(int speed) {
		this.speedLevel = speed;
		this.speed = speed*10 + 100;
		textToSpeech.getInstance().setWPM(this.speed);
	}
	public int getFontSize() {
		return fontSize;
	}
	public int getFontLevel()
	{
		return fontLevel;
	}
	public void setFontSize(int font) {
		this.fontLevel = font;
		double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		switch(font)
		{
			case 1: this.fontSize = (int)screenWidth/50; break; 
			case 2: this.fontSize =  (int)screenWidth/40; break;
			case 3: this.fontSize = (int)screenWidth/25; break;
			default: this.fontSize = (int)screenWidth/40;
		}	
	}

	public void setVolume(int volume) {
		switch(volume)
		{
			case 1: this.volume = (float)0.65; break;
			case 2: this.volume = (float)0.70; break;
			case 3: this.volume = (float)0.75; break;
			case 4: this.volume = (float)0.8; break;
			case 5: this.volume = (float)0.85; break; 
			case 6: this.volume = (float)0.9; break;
			case 7: this.volume = (float)0.95; break;
			case 8: this.volume = (float)1; break;
		}
		this.volumeLevel = volume;
		textToSpeech.getInstance().setVolume(this.volume);
	}
	public float getVolume(){
		return this.volume;
	}
	public int getVolumeLevel()
	{
		return this.volumeLevel;
	}
	
	public Themes getTheme(){
		return theme;
	}
	
	public int getThemeLevel()
	{
		return themeLevel; 
	}
	
	public void setTheme(int themeLevel)
	{	
		this.themeLevel = themeLevel;
		switch(themeLevel)
		{
			case 1: this.theme = Themes.DEFAULT; break;
			case 2: this.theme = Themes.ORANGE_SPOT; break;
			case 3: this.theme = Themes.ICY; break;
			case 4: this.theme = Themes.COTTON_CANDY; break;
			case 5: this.theme = Themes.WATERMELON; break;
			default: this.theme = Themes.DEFAULT; break;
		}
	}
	
}
