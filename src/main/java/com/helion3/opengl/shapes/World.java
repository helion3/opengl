package com.helion3.opengl.shapes;

import java.util.ArrayList;
import java.util.List;

public class World {
	
	private List<Chunk> loadedChunks = new ArrayList<Chunk>();
	
	
	/**
	 * 
	 */
	public World(){
		int blocksRendered = 0;
		// Build all of the initial chunks
		for( int x = 1; x < 10; x++ ){
			for( int z = 1; z < 10; z++ ){
				Chunk c = new Chunk();
				blocksRendered += c.prerender(x, z);
				loadedChunks.add( c );
			}
		}
		System.out.println("Blocks rendering: " + blocksRendered);
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