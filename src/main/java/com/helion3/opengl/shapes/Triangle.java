package com.helion3.opengl.shapes;

import com.helion3.opengl.rendering.TriangleRenderer;

public class Triangle {
	
	
	private static TriangleRenderer triangleTesselator = new TriangleRenderer();
	
	
	/**
	 * 
	 */
	public static void draw(){
		
		triangleTesselator.addVertex(-0.5f, -0.5f, 0f);
		triangleTesselator.addColor(1, 0, 0);
		
		triangleTesselator.addVertex(+0.5f, -0.5f, 0f);
		triangleTesselator.addColor(0, 1, 0);
		
		triangleTesselator.addVertex(+0.5f, +0.5f, 0f);
		triangleTesselator.addColor(0, 0, 1);

		triangleTesselator.render();
		
	}
}