package com.helion3.opengl.shapes;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import org.newdawn.slick.opengl.Texture;

import com.helion3.opengl.rendering.TextureQuadRenderer;


public class TextureCube {
	
	private static TextureQuadRenderer quadTesselator = new TextureQuadRenderer();
	
	
	/**
	 * 
	 * @param quadTesselator
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void draw( Texture texture, int x, int y, int z ){
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		
		texture.bind();
		glActiveTexture(GL_TEXTURE0 + 0);
		
		float size = 0.5f;
		
		float textureDim = 0.0625f;
		
		float topLeftX = textureDim * 1;
		float topLeftY = textureDim * 1;
		float topRightX = topLeftX + textureDim;
		float topRightY = topLeftY;
		float bottomLeftX = topLeftX;
		float bottomLeftY = topLeftY + textureDim;
		float bottomRightX = topRightX;
		float bottomRightY = topRightY + textureDim;
		
		// NORTH FACE
		
		// bottom left
		quadTesselator.addVertex(-(size)+x, -(size)+y, size+z);
		quadTesselator.addColor(1, 0, 0);
		quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
		
		// bottom right
		quadTesselator.addVertex(size+x, -(size)+y, size+z);
		quadTesselator.addColor(0, 1, 0);
		quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
		
		// top right
		quadTesselator.addVertex(size+x, size+y, size+z);
		quadTesselator.addColor(0, 0, 1);
		quadTesselator.addTextureCoord(topRightX,topRightY);
		
		// top left
		quadTesselator.addVertex(-(size)+x, size+y, size+z);
		quadTesselator.addColor(1, 0, 1);
		quadTesselator.addTextureCoord(topLeftX,topLeftY);
		
		// SOUTH FACE
		
		// bottom left
		quadTesselator.addVertex(size+x, -(size)+y, -(size)+z);
		quadTesselator.addColor(1, 0, 0);
		quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
		
		// bottom right
		quadTesselator.addVertex(-(size)+x, -(size)+y, -(size)+z);
		quadTesselator.addColor(0, 1, 0);
		quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
		
		// top right
		quadTesselator.addVertex(-(size)+x, size+y, -(size)+z);
		quadTesselator.addColor(0, 0, 1);
		quadTesselator.addTextureCoord(topRightX,topRightY);
		
		// top left
		quadTesselator.addVertex(size+x, size+y, -(size)+z);
		quadTesselator.addColor(1, 0, 1);
		quadTesselator.addTextureCoord(topLeftX,topLeftY);
		
		// WEST FACE
		
		// bottom left
		quadTesselator.addVertex(size+x, -(size)+y, size+z);
		quadTesselator.addColor(1, 0, 0);
		quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
		
		// bottom right
		quadTesselator.addVertex(size+x, -(size)+y, -(size)+z);
		quadTesselator.addColor(0, 1, 0);
		quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
		
		// top right
		quadTesselator.addVertex(size+x, size+y, -(size)+z);
		quadTesselator.addColor(0, 0, 1);
		quadTesselator.addTextureCoord(topRightX,topRightY);
		
		// top left
		quadTesselator.addVertex(size+x, size+y, size+z);
		quadTesselator.addColor(1, 0, 1);
		quadTesselator.addTextureCoord(topLeftX,topLeftY);
		
		// EAST FACE
		
		// bottom left
		quadTesselator.addVertex(-(size)+x, -(size)+y, -(size)+z);
		quadTesselator.addColor(1, 0, 0);
		quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
		
		// bottom right
		quadTesselator.addVertex(-(size)+x, -(size)+y, size+z);
		quadTesselator.addColor(0, 1, 0);
		quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
		
		// top right
		quadTesselator.addVertex(-(size)+x, size+y, size+z);
		quadTesselator.addColor(0, 0, 1);
		quadTesselator.addTextureCoord(topRightX,topRightY);
		
		// top left
		quadTesselator.addVertex(-(size)+x, size+y, -(size)+z);
		quadTesselator.addColor(1, 0, 1);
		quadTesselator.addTextureCoord(topLeftX,topLeftY);
		
		// TOP FACE
		
		// bottom left
		quadTesselator.addVertex(-(size)+x, size+y, size+z);
		quadTesselator.addColor(1, 0, 0);
		quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
		
		// bottom right
		quadTesselator.addVertex(size+x, size+y, size+z);
		quadTesselator.addColor(0, 1, 0);
		quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
		
		// top right
		quadTesselator.addVertex(size+x, size+y, -(size)+z);
		quadTesselator.addColor(0, 0, 1);
		quadTesselator.addTextureCoord(topRightX,topRightY);
		
		// top left
		quadTesselator.addVertex(-(size)+x, size+y, -(size)+z);
		quadTesselator.addColor(1, 0, 1);
		quadTesselator.addTextureCoord(topLeftX,topLeftY);
		
		// BOTTOM FACE
		
		// bottom left
		quadTesselator.addVertex(size+x, -(size)+y, -(size)+z);
		quadTesselator.addColor(1, 0, 0);
		quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
		
		// bottom right
		quadTesselator.addVertex(size+x, -(size)+y, size+z);
		quadTesselator.addColor(0, 1, 0);
		quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
		
		// top right
		quadTesselator.addVertex(-(size)+x, -(size)+y, size+z);
		quadTesselator.addColor(0, 0, 1);
		quadTesselator.addTextureCoord(topRightX,topRightY);
		
		// top left
		quadTesselator.addVertex(-(size)+x, -(size)+y, -(size)+z);
		quadTesselator.addColor(1, 0, 1);
		quadTesselator.addTextureCoord(topLeftX,topLeftY);

		quadTesselator.render();
		
	}
}