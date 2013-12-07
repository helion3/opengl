package com.helion3.tests;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.ARBBufferObject.*;
import static org.lwjgl.opengl.ARBVertexBufferObject.*;
import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.opengl.GL15.*;

public class Tesselator {
	
	private static int bufferSize = 16*16*128 * 9 * 2; // chunk size * vertex/color/texture size (adding * 2 for now so I have room, real size unknown)
	
	private FloatBuffer interleavedBuffer = BufferUtils.createFloatBuffer(bufferSize);
	private IntBuffer ib = BufferUtils.createIntBuffer(2);
    protected int vertexCount = 0;
    
    
    /**
     * 
     * @param x
     * @param y
     * @param z
     */
    public void addVertex( float x, float y, float z ){
    	interleavedBuffer.put(x).put(y).put(z);
    	vertexCount++;
    }
    
    
    /**
     * Adds an RGB color to the buffer
     * 
     * @param r
     * @param g
     * @param b
     */
    public void addColor( float r, float g, float b ){
    	interleavedBuffer.put(r).put(g).put(b);
    }
    
    
    /**
     * 
     * @param x
     * @param y
     */
    public void addTextureCoord( float x, float y ){
    	interleavedBuffer.put(x).put(y);
    }
    
    
    /**
     * 
     */
    public void render(){
    	
    	System.out.println("Rendering...");
    	
    	interleavedBuffer.flip();

    	glGenBuffersARB(ib);
    	int vHandle = ib.get(0);
        
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        glBindBufferARB(GL_ARRAY_BUFFER_ARB, vHandle);
        glBufferDataARB(GL_ARRAY_BUFFER_ARB, interleavedBuffer, GL_STATIC_DRAW_ARB);
        
        /* Stride = float positions before the next instance of the same parameter. (3 vertex, 3 color, 2 texture coords  8 multiply by 4 for bytes per float */
        /* Offset = which index offset the right values begin */
        
        glVertexPointer(3, GL_FLOAT, 38, 0); // float at index 0
        glColorPointer(3, GL_FLOAT, 38, 12); // float at index 3 (* 4 bytes)
        glTexCoordPointer(2, GL_FLOAT, 38, 24); // float at index 6 (* 4 bytes)

        //If you are not using IBOs:
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);

        glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);

        glDisableClientState(GL_COLOR_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);

        // cleanup VBO handles
        ib.put(0, vHandle);
        glDeleteBuffersARB(ib);
        
        clear();
    	
    }
    
    
    /**
     * 
     */
    protected void clear(){
    	vertexCount = 0;
    	interleavedBuffer.clear();
    }
}