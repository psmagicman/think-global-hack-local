package users;

import java.awt.Color;

public enum Themes {
	
		DEFAULT (Color.DARK_GRAY, Color.WHITE, "ff6600"),
		VENUS   (Color.WHITE, Color.DARK_GRAY, "ff6600");
				
	 	private final Color background;   // in kilograms
	    private final Color foreground; // in meters
	    private final String letter;
	    
	    Themes(){
	    	this.background = Color.DARK_GRAY;
	    	this.foreground = Color.WHITE;
	    	this.letter = "ff6600";
	    }
	      
	    Themes(Color bg, Color fg, String l) {
	        this.background = bg;
	        this.foreground = fg;
	        this.letter = l;
	    }
	    private Color background() { return background; }
	    private Color foreground() { return foreground; }
	    private String letter() { return letter; }

}
