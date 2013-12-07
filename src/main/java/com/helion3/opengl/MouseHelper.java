package com.helion3.opengl;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class MouseHelper {

	
	/**
	 * Grabs the mouse and locks it in place, so that it becomes the steering
	 * device.
	 */
	public static void grab(){
		Mouse.setCursorPosition( Display.getWidth()/2 , Display.getHeight()/2);
		Mouse.setGrabbed(true);
	}
	
	
	/**
	 * Releases the mouse so the user may use it normally.
	 */
	public static void letGo(){
		Mouse.setCursorPosition( Display.getWidth()/2 , Display.getHeight()/2);
		Mouse.setGrabbed(false);
	}
}