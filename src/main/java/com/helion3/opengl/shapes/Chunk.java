package com.helion3.opengl.shapes;

import com.helion3.opengl.rendering.TextureQuadRenderer;


public class Chunk {
	
	public static final int ROWS = 16;
	public static final int COLUMNS = 16;
	public static final int HEIGHT = 16;
	
	private int bufferSize = 192 * ROWS * COLUMNS * HEIGHT; // 192 per block * chunk dimensions
	private TextureQuadRenderer quadTesselator = new TextureQuadRenderer(bufferSize);
	
	private byte[][][] blocks = new byte[ROWS][COLUMNS][HEIGHT];
	private int chunkX;
	private int chunkZ;
	
	
	/**
	 * 
	 * @param chunkX
	 * @param chunkZ
	 */
	public Chunk( int chunkX, int chunkZ ){
		
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		
		// build tiles
		for( byte x = 0; x < ROWS; x++ ){
			for( byte z = 0; z < COLUMNS; z++ ){
				for( byte y = 0; y < HEIGHT; y++ ){
					blocks[x][z][y] = 1; // for now, 1 solid, 0 = air
				}
			}
		}
	}
	

	/**
	 * Build the tileset for this chunk
	 * @param chunkX
	 * @param chunkZ
	 */
	public void prerender(){
		for( byte x = 0; x < ROWS; x++ ){
			for( byte z = 0; z < COLUMNS; z++ ){
				for( byte y = 0; y < HEIGHT; y++ ){
					
					// Only process blocks on the edges, saves a bit of work in deciding which faces to render
					// in a real app with random terrain, this won't quite cut it.
					if( x == 0 || z == 0 || y == 0 || x == (ROWS-1) || y == (HEIGHT-1) || z == (COLUMNS-1) ){
						prerenderBlock( x, y, z );
					}
				}
			}
		}
		quadTesselator.saveToBuffer();
	}
	
	
	/**
	 * 
	 */
	public void render(){
		quadTesselator.render();
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	protected int getBlockAt( byte x, byte y, byte z ){
		int blockAt = 0;
		try {
			blockAt = blocks[x][y][z];
		} catch( Exception e ){
			// no block
		}
		return blockAt;
	}
	
	
	/**
	 * Adds all of the blocks for this chunk to the renderer, only renders visible faces
	 * @todo In a real app, there needs to be a way of checking blocks in neighboring chunks
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	protected void prerenderBlock( byte x, byte y, byte z ){
		
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
		
		float realX = (chunkX*ROWS)+x;
		float realZ = (chunkZ*COLUMNS)+z;
		
		// NORTH FACE
		// If no block exists at next location
		if( getBlockAt(x,y,(byte)(z+1)) == 0 ){
	
			// bottom left
			quadTesselator.addVertex(-(size)+realX, -(size)+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
			
			// bottom right
			quadTesselator.addVertex(size+realX, -(size)+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
			
			// top right
			quadTesselator.addVertex(size+realX, size+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topRightX,topRightY);
			
			// top left
			quadTesselator.addVertex(-(size)+realX, size+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topLeftX,topLeftY);
			
		}
		
		// SOUTH FACE
		if( getBlockAt(x,y,(byte)(z-1)) == 0 ){
		
			// bottom left
			quadTesselator.addVertex(size+realX, -(size)+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
			
			// bottom right
			quadTesselator.addVertex(-(size)+realX, -(size)+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
			
			// top right
			quadTesselator.addVertex(-(size)+realX, size+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topRightX,topRightY);
			
			// top left
			quadTesselator.addVertex(size+realX, size+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topLeftX,topLeftY);
		}
		
		// WEST FACE
		if( getBlockAt((byte)(x+1),y,z) == 0 ){
			// bottom left
			quadTesselator.addVertex(size+realX, -(size)+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
			
			// bottom right
			quadTesselator.addVertex(size+realX, -(size)+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
			
			// top right
			quadTesselator.addVertex(size+realX, size+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topRightX,topRightY);
			
			// top left
			quadTesselator.addVertex(size+realX, size+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topLeftX,topLeftY);
		}
			
		// EAST FACE
		if( getBlockAt((byte)(x-1),y,z) == 0 ){
			// bottom left
			quadTesselator.addVertex(-(size)+realX, -(size)+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
			
			// bottom right
			quadTesselator.addVertex(-(size)+realX, -(size)+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
			
			// top right
			quadTesselator.addVertex(-(size)+realX, size+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topRightX,topRightY);
			
			// top left
			quadTesselator.addVertex(-(size)+realX, size+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topLeftX,topLeftY);
		}
		
		// TOP FACE
		if( getBlockAt(x,(byte)(y+1),z) == 0 ){
			// bottom left
			quadTesselator.addVertex(-(size)+realX, size+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
			
			// bottom right
			quadTesselator.addVertex(size+realX, size+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
			
			// top right
			quadTesselator.addVertex(size+realX, size+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topRightX,topRightY);
			
			// top left
			quadTesselator.addVertex(-(size)+realX, size+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topLeftX,topLeftY);
		}
		
		// BOTTOM FACE
		if( getBlockAt(x,(byte)(y-1),z) == 0 ){
			// bottom left
			quadTesselator.addVertex(size+realX, -(size)+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
			
			// bottom right
			quadTesselator.addVertex(size+realX, -(size)+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(bottomRightX,bottomRightY);
			
			// top right
			quadTesselator.addVertex(-(size)+realX, -(size)+y, size+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topRightX,topRightY);
			
			// top left
			quadTesselator.addVertex(-(size)+realX, -(size)+y, -(size)+realZ);
			quadTesselator.addColor(1, 1, 1);
			quadTesselator.addTextureCoord(topLeftX,topLeftY);
		}
	}
}