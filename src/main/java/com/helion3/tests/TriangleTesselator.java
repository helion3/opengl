package com.helion3.tests;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.ARBBufferObject.*;
import static org.lwjgl.opengl.ARBVertexBufferObject.*;
import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.opengl.GL15.*;

public class TriangleTesselator {
	
	private static int bufferSize = 18;
	
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
     */
    public void render(){
    	
    	interleavedBuffer.flip();

    	glGenBuffersARB(ib);
    	int vHandle = ib.get(0);
        
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);

        glBindBufferARB(GL_ARRAY_BUFFER_ARB, vHandle);
        glBufferDataARB(GL_ARRAY_BUFFER_ARB, interleavedBuffer, GL_STATIC_DRAW_ARB);
        
        /* Stride = float positions before the next instance of the same parameter. (3 vertex, 3 color = 6 multiply by 4 for bytes per float */
        /* Offset = how many floats come before instance of color (etc) float multiplied by size of float */
        
        glVertexPointer(3, GL_FLOAT, 24, 0); // float at index 0
        glColorPointer(3, GL_FLOAT, 24, 12); // float at index 3 (* 4 bytes)

        glDrawArrays(GL_TRIANGLES, 0, vertexCount);

        glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);

        glDisableClientState(GL_COLOR_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);

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