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
	}
	

	/**
	 * Build the tileset for this chunk
	 * @param chunkX
	 * @param chunkZ
	 */
	public void prerender(){
		for( int x = 0; x < ROWS; x++ ){
			for( int z = 0; z < COLUMNS; z++ ){
				for( int y = 0; y < HEIGHT; y++ ){
					
					// Only render blocks on the edges, saves a bit of work in checking neighbor blocks
					// in a real app with random terrain, this won't cut it.
					if( x == 0 || z == 0 || y == 0 || x == (ROWS-1) || y == (HEIGHT-1) || z == (COLUMNS-1) ){
						blocks[x][z][y] = 1;
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
	 * Adds all of the blocks for this chunk to the renderer, only renders visible faces
	 * @todo In a real app, there needs to be a way of checking blocks in neighboring chunks
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	protected void prerenderBlock( int x, int y, int z ){
		
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
		if (z < Chunk.COLUMNS ) {
			if (z != 0 && (z == Chunk.COLUMNS - 1) || (blocks[x][y][z + 1] != 1)){
		
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
		}
		
		// SOUTH FACE
		if (z >= 0) {
			if (z != Chunk.COLUMNS - 1 && (z == 0) || (blocks[x][y][z - 1] != 1)){
		
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
		}
		
		// WEST FACE
		if (x < Chunk.ROWS) {
			if (x != 0 && (x == Chunk.ROWS - 1) || (blocks[x + 1][y][z] != 1)){
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
		}
			
		// EAST FACE
		if (x >= 0) {
			if (x != Chunk.COLUMNS - 1 && (x == 0) || (blocks[x - 1][y][z] != 1)){
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
		}
		
		// TOP FACE
		if(y < HEIGHT){
			// If we're the top block, or there's no visible block above
			if ( (y == HEIGHT - 1) || (blocks[x][y + 1][z] != 1) ){
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
		}
		
		// BOTTOM FACE
		if (y >= 0) {
			if (y != HEIGHT - 1 && (y == 0) || (blocks[x][y - 1][z] != 1)){
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
}