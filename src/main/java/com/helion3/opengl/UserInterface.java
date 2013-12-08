package com.helion3.opengl;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;



public abstract class UserInterface {
	
	
	/**
	 * Clear the screen and depth buffer
	 */
	public static void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
	}
	
	
	/**
	 * 
	 */
	public static void enterOrtho() {
		 GL11.glDisable(GL11.GL_DEPTH_TEST);
		 GL11.glMatrixMode(GL11.GL_PROJECTION); 
		 GL11.glPushMatrix();
		 GL11.glLoadIdentity();
		 GL11.glOrtho(0, 1024, 800,0, -1, 1);
		 GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 GL11.glPushMatrix();
		 GL11.glLoadIdentity();
	}
	
		
	/**
	 * 
	 */
	public static void leaveOrtho() {
		 // Restore the state of the projection mode
		 GL11.glMatrixMode(GL11.GL_PROJECTION);
		 GL11.glPopMatrix();

		 // Restore the state of the model view mode
		 GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 GL11.glPopMatrix();
		 GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	
	
	/**
	 * 
	 * @param c
	 */
	protected static void translateColor( Color c ){
		float r_f = (float) (c.getRed() / 255.0);
		float g_f = (float) (c.getGreen() / 255.0);
		float b_f = (float) (c.getBlue() / 255.0);
		float a_f = (float) (c.getAlpha() / 255.0); // @todo broken?
        glColor4f( r_f, g_f, b_f, a_f );
	}
	
	
	/**
	 * Draws a filled-color rectangle
	 * @param x
	 * @param z
	 * @param w
	 * @param h
	 */
	public static void fillRect( int x1, int y1, int w, int h, Color c ){
		
		int x2 = x1 + w;
		int y2 = y1 + h;

		// Set modes
		glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glDisable(GL_ALPHA_TEST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glShadeModel(GL_SMOOTH);
		
		translateColor(c);
		glRecti(x1, y1, x2, y2);
		
		// Reset modes
		glShadeModel(GL_FLAT);
        glDisable(GL_BLEND);
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_TEXTURE_2D);

    }
	
	
	
	/**
	 * Draws an empty rectangle
	 * @param x
	 * @param z
	 * @param w
	 * @param h
	 */
	public static void drawRect( int x1, int y1, int w, int h, Color c ){
		
		int x2 = x1 + w;
		int y2 = y1 + h;

		// Set modes
		glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glDisable(GL_ALPHA_TEST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glShadeModel(GL_SMOOTH);
		
		translateColor(c);
		glBegin(GL_LINE_LOOP);
		glVertex2f(x1, y1);
		glVertex2f(x2, y1);
		glVertex2f(x2, y2);
		glVertex2f(x1, y2);
		glEnd();  
		
		// Reset modes
		glShadeModel(GL_FLAT);
        glDisable(GL_BLEND);
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_TEXTURE_2D);

    }
	
	
	protected static Map<Color, ArrayList<int[]>> batchedRects = new HashMap<Color, ArrayList<int[]>>();  
	/**
	 * Draws a filled-color rectangle
	 * @param x
	 * @param z
	 * @param w
	 * @param h
	 */
	public static void fillRectBatched( int x1, int y1, int w, int h, Color c ){
		if (!batchedRects.containsKey(c)) {
			batchedRects.put(c, new ArrayList<int[]>());
		}
		((ArrayList<int[]>)batchedRects.get(c)).add(new int[] {x1, y1, x1+w, y1+h});
	}
	
	
	/**
	 * 
	 */
	public static void flushRectBatches() {
		org.newdawn.slick.Color.white.bind();
		// Set modes
		glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glDisable(GL_ALPHA_TEST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glShadeModel(GL_SMOOTH);

        for ( Color c : batchedRects.keySet()) {
			 
	        
	        // @todo clean this up, this should use a method
			float r_f = (float) (c.getRed() / 255.0);
			float g_f = (float) (c.getGreen() / 255.0);
			float b_f = (float) (c.getBlue() / 255.0);
			float a_f = (float) (c.getAlpha() / 255.0);
	        glColor4f( r_f, g_f, b_f, a_f );

			for (int [] coordpair : batchedRects.get(c)) {
				int x1 = coordpair[0];
				int y1 = coordpair[1];
				int x2 = coordpair[2];
				int y2 = coordpair[3];
				glRecti(x1, y1, x2, y2);
			}
		}
		batchedRects.clear();
		
		// Reset modes
		glShadeModel(GL_FLAT);
        glDisable(GL_BLEND);
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_TEXTURE_2D);
	}
	
	
	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param r
	 * @param num_segments
	 */
	public static void fillCircle( float x, float y, float r, int num_segments, Color c ){
		
		// Set modes
		glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glDisable(GL_ALPHA_TEST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glShadeModel(GL_SMOOTH);
		
		glPushMatrix();
		glTranslatef(x, y, 0);
		glScalef(r, r, 1);
		glBegin(GL_TRIANGLE_FAN);
		translateColor(c);
		glVertex2f(0, 0);
		for(int i = 0; i <= num_segments; i++){
		    double angle = Math.PI * 2 * i / num_segments;
		    glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
		}
		glEnd();
		glPopMatrix();
		
		// Reset modes
		glShadeModel(GL_FLAT);
        glDisable(GL_BLEND);
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_TEXTURE_2D);
		
	}
	
	
	/**
	 * Draws a string using the provided font.
	 * @param font
	 * @param text
	 * @param x
	 * @param y
	 * @param c
	 */
	public static void drawString( TrueTypeFont font, String text, float x, float y, org.newdawn.slick.Color c ){
		glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        int lineHeight = font.getHeight(text);
        String[] lines = text.split("\n");
        for(int i = 0; i<lines.length; i++) {
        	font.drawString( x, y+(i*lineHeight), lines[i], c);
        }
    	glDisable(GL_BLEND);
	}
	
	
	/**
	 * 
	 * @param f
	 * @param s
	 * @return
	 */
	public static int getLineWidth(TrueTypeFont f, String s) {
		if (s == null) return 0;
		if (s.length() == 0) return 0;
		int maxw = 0;
		for (String l : s.split("\n")) {
			int t = f.getWidth(l);
			if (t > maxw) maxw = t;
		}
		return maxw;
	}

	
	/**
	 * 
	 * @param s
	 * @return
	 */
	protected int countLines(String s) {
		if (s == null) return 0;
		if (s.length() == 0) return 0;
		int c = 1;
		int p = 0;
		int idx;
		do {
			idx = s.indexOf('\n', p);
			if (idx == -1) return c;
			c++;
			p = idx+1;
		} while (p < s.length());
		return c;
	}
	
	
	/**
	 * Draws an icon
	 * @param texture
	 * @param x
	 * @param y
	 */
	public static void drawIcon( Texture texture, int x, int y ){

		org.newdawn.slick.Color.white.bind();
		texture.bind();

		glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
//        glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP );
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(x,y);
			glTexCoord2f(1,0);
			glVertex2f(x+texture.getTextureWidth(),y);
			glTexCoord2f(1,1);
			glVertex2f(x+texture.getTextureWidth(),y+texture.getTextureHeight());
			glTexCoord2f(0,1);
			glVertex2f(x,y+texture.getTextureHeight());
		glEnd();
		glDisable(GL_BLEND);
	}
	
	
	/**
	 * Draws an icon
	 * @param textureKey
	 * @param x
	 * @param y
	 */
	public static void drawIcon( Image image, int x, int y ){
		org.newdawn.slick.Color.white.bind();
		image.bind();
		image.clampTexture();
		glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        image.draw();
		glDisable(GL_BLEND);
	}
	
	
	protected static Map<Texture, ArrayList<int[]>> batchedIcons = new HashMap<Texture, ArrayList<int[]>>();  
	
	
	/**
	 * 
	 * @param texture
	 * @param x
	 * @param y
	 */
	public static void drawIconBatched( Texture texture, int x, int y ) {
		if (!batchedIcons.containsKey(texture)) {
			batchedIcons.put(texture, new ArrayList<int[]>());
		}
		((ArrayList<int[]>)batchedIcons.get(texture)).add(new int[] {x, y});
	}
	
	
	/**
	 * 
	 */
	public static void flushIconBatches() {
		org.newdawn.slick.Color.white.bind();
		glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		for ( Texture texture : batchedIcons.keySet()) {
			texture.bind();
			int tw = texture.getTextureWidth();
			int th = texture.getTextureHeight();
			for (int [] xypair : batchedIcons.get(texture)) {
				int x = xypair[0];
				int y = xypair[1];
				glBegin(GL_QUADS);
				glTexCoord2f(0,0);
				glVertex2f(x,y);
				glTexCoord2f(1,0);
				glVertex2f(x+tw,y);
				glTexCoord2f(1,1);
				glVertex2f(x+tw,y+th);
				glTexCoord2f(0,1);
				glVertex2f(x,y+th);
				glEnd();
			}
		}
		batchedIcons.clear();
		glDisable(GL_BLEND);
	}
}