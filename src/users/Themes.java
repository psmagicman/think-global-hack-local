package users;

import java.awt.Color;

public enum Themes {
	//Name(background,foreground,letter)
		DEFAULT (Color.DARK_GRAY, Color.WHITE, "ff6600"),
		VENUS   (Color.WHITE, Color.DARK_GRAY, "000000");
				
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
