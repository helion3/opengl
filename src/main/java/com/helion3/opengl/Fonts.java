package com.helion3.opengl;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class Fonts {
	
	
	private TrueTypeFont fontResource;
	
	/**
	 * 
	 */
	public static final Fonts arialPlain = new Fonts("Arial",12);

	
	
	/**
	 * 
	 */
	public Fonts( String fontName, int size ){
		this(fontName, size, Font.PLAIN);
	}

	
	/**
	 * 
	 */
	public Fonts( String fontName, int size, int weight ){
		Font font = new Font(fontName, weight, size);
		fontResource = new TrueTypeFont(font, true);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public TrueTypeFont get(){
		return fontResource;
	}
}