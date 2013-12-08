package com.helion3.opengl.shapes;

import java.util.ArrayList;
import java.util.List;

public class World {
	
	List<Chunk> loadedChunks = new ArrayList<Chunk>();
	
	
	/**
	 * 
	 */
	public World(){
		// Build all of the initial chunks
		for( int x = 1; x < 10; x++ ){
			for( int z = 1; z < 10; z++ ){
				loadedChunks.add( new Chunk(x, z) );
			}
		}
	}
	
	
	/**
	 * 
	 */
	public void render(){
		for( Chunk c : loadedChunks ){
			c.render();
		}
	}
}