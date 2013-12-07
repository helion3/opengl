package com.helion3.tests;

public class ColorSquare {
	
	
	/**
	 * 
	 * @param quadTesselator
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void draw( QuadTesselator quadTesselator, int x, int y, int z ){
		
		float size = 5f;
		
		// bottom left
		quadTesselator.addVertex(-(size)+x, -(size)+y, size+z);
		quadTesselator.addColor(1, 0, 0);
		
		// bottom right
		quadTesselator.addVertex(size+x, -(size)+y, size+z);
		quadTesselator.addColor(0, 1, 0);
		
		// top right
		quadTesselator.addVertex(size+x, size+y, size+z);
		quadTesselator.addColor(0, 0, 1);
		
		// top left
		quadTesselator.addVertex(-(size)+x, size+y, size+z);
		quadTesselator.addColor(1, 0, 1);

		quadTesselator.render();
	}

}