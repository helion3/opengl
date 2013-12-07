package com.helion3.opengl.shapes;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LEQUAL;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import org.newdawn.slick.opengl.Texture;

import com.helion3.opengl.rendering.TextureQuadRenderer;


public class Chunk {
	
	public static final int ROWS = 16;
	public static final int COLUMNS = 16;
	public static final int HEIGHT = 16;
	
	private static int bufferSize = 192 * ROWS * COLUMNS * HEIGHT; // 192 per block * chunk dimensions
	private static TextureQuadRenderer quadTesselator = new TextureQuadRenderer(bufferSize);

	/**
	 * 
	 * @param quadTesselator
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void draw( Texture texture, int chunkX, int chunkZ ){
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		
		texture.bind();
		glActiveTexture(GL_TEXTURE0 + 0);
		
		for( int x = 0; x < ROWS; x++ ){
			for( int z = 0; z < COLUMNS; z++ ){
				for( int y = 0; y < HEIGHT; y++ ){
					drawBlock( (chunkX*ROWS)+x, y, (chunkZ*COLUMNS)+z );
				}
			}
		}
		
		quadTesselator.render();
		
	}
	
	
	/**
	 * Adds all of the blocks for this chunk to the renderer
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	protected static void drawBlock( int x, int y, int z ){
		
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
		
	}
}