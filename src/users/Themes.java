package users;

import java.awt.Color;

public enum Themes {
	//Name(background,foreground,letter)
	DEFAULT 	 (Color.DARK_GRAY, Color.WHITE, "ff6600"),
	ORANGE_SPOT  (Color.WHITE, Color.DARK_GRAY, "ff6600"),
	ICY			 (Color.BLACK, Color.CYAN, "ffffff"),
	COTTON_CANDY (Color.DARK_GRAY, Color.MAGENTA, "0099FF"),
	WATERMELON	 (Color.BLACK, Color.PINK, "66FF00");


	private final Color background;   // in kilograms
	private final Color foreground; // in meters
	private final String letter;

	Themes(){
		background = Color.DARK_GRAY;
		foreground = Color.WHITE;
		letter = "ff6600";
	}

	Themes(Color bg, Color fg, String l) {
		background = bg;
		foreground = fg;
		letter = l;
	}
	public Color background() {
		return background; 
	}
	public Color foreground() {
		return foreground; 
	}
	public String letter() { 
		return letter; 
	}

}
