package com.helion3.opengl.shapes;

import java.util.ArrayList;
import java.util.List;

public class World {
	
	private List<Chunk> loadedChunks = new ArrayList<Chunk>();
	
	
	/**
	 * 
	 */
	public World(){
		// Build all of the initial chunks
		for( int x = 1; x < 15; x++ ){
			for( int z = 1; z < 16; z++ ){
				Chunk c = new Chunk(x, z);
				c.prerender();
				loadedChunks.add( c );
			}
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public List<Chunk> getLoadedChunks(){
		return loadedChunks;
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