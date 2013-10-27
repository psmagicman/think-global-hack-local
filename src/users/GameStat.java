package users;

import java.util.HashMap;
import java.util.Map;

public class GameStat {
	private String name;
	private int numberOfTries;
	private int numberOfSuccess;
	private int currentLevel;
	private int totalLevel;
	private String description;
	private Map<String,Object> customStats;
	
	public GameStat(){
		name = "game";
		numberOfTries = 0;
		numberOfSuccess = 0;
		currentLevel = 1;
		totalLevel = 1;
		description = "description";	
		customStats = new HashMap<String, Object>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfTries() {
		return numberOfTries;
	}
	public void setNumberOfTries(int numberOfTries) {
		this.numberOfTries = numberOfTries;
	}
	public int getNumberOfSuccess() {
		return numberOfSuccess;
	}
	public void setNumberOfSuccess(int numberOfSuccess) {
		this.numberOfSuccess = numberOfSuccess;
	}
	public int getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	public int getTotalLevel() {
		return totalLevel;
	}
	public void setTotalLevel(int totalLevel) {
		this.totalLevel = totalLevel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setCustom(String key, Object value) {
		customStats.put(key, value);
	}
	
	public Object getCustom(String key) {
		return customStats.get(key);
	}
	
	

}
