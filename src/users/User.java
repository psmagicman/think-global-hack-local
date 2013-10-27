package users;

import java.util.List;
import java.util.Map;

import util.textToSpeech;

public class User {

	private String path;
	private String name;
	private Preferences preferences;
	private Map<String, List<GameStat>> gameStats; //name and statistics for game
	private int id;

	public User(String name) {
		this.name = name;
		this.id = -1;
		this.preferences = new Preferences();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences prefs) {
		this.preferences = prefs;
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		
	}
	
	@Override public String toString(){
		return this.name;
	}
}
