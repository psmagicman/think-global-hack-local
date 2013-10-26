package users;

public class User {

	private String name;
	private Preferences preferences;
	//private GameLevels levels;
	private int id;

	public User(String name) {
		this.name = name;
		this.id = -1;
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
}
