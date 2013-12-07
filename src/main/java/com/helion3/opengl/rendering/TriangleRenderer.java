package com.helion3.opengl.rendering;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.ARBBufferObject.*;
import static org.lwjgl.opengl.ARBVertexBufferObject.*;
import static org.lwjgl.opengl.GL11.*;

public class TriangleRenderer {
	
	private static int bufferSize = 192; // extra room
	
	private FloatBuffer interleavedBuffer = BufferUtils.createFloatBuffer(bufferSize);
	private IntBuffer ib = BufferUtils.createIntBuffer(1);
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
     * The stride is how much space is between two tuples - 3 vertex floats, 3 color floats = 6. 6 x 4 = 24
     * the offset is how much space is between 0 and the first appearance of the first tuple. 3 vertex floats before 1st color. 3 x 4 = 12
     */
    public void render(){
    	
    	interleavedBuffer.flip();

    	glGenBuffersARB(ib);
    	int vHandle = ib.get(0);
        
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);

        glBindBufferARB(GL_ARRAY_BUFFER_ARB, vHandle);
        glBufferDataARB(GL_ARRAY_BUFFER_ARB, interleavedBuffer, GL_STATIC_DRAW_ARB);
        
        glVertexPointer(3, GL_FLOAT, 24, 0);
        glColorPointer(3, GL_FLOAT, 24, 12);

        glDrawArrays(GL_TRIANGLE_STRIP, 0, vertexCount);

        glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);

        glDisableClientState(GL_COLOR_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);

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