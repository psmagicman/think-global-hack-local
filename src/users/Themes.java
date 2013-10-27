package users;

import java.awt.Color;

public enum Themes {
	//Name(background,foreground,letter)
	DEFAULT 	 (Color.DARK_GRAY, Color.WHITE, "ff6600"),
	ORANGE_SPOT  (Color.WHITE, Color.DARK_GRAY, "ff6600"),
	ICY			 (Color.CYAN, Color.BLACK, "000cff"),
	COTTON_CANDY (Color.MAGENTA, Color.WHITE, "000cff"),
	WATERMELON	 (Color.PINK, Color.WHITE, "66FF00");


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
