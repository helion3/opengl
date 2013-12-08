package com.helion3.opengl.rendering;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.ARBBufferObject.*;
import static org.lwjgl.opengl.ARBVertexBufferObject.*;
import static org.lwjgl.opengl.GL11.*;

public class TextureQuadRenderer {
	
	private FloatBuffer interleavedBuffer;
	private IntBuffer ib = BufferUtils.createIntBuffer(1);
    protected int vertexCount = 0;
    protected int bufferId = -1;
    
    
    /**
     * 
     * @param bufferSize
     */
    public TextureQuadRenderer( int bufferSize ){
    	this.interleavedBuffer = BufferUtils.createFloatBuffer(bufferSize);
    	glGenBuffersARB(ib);
    	bufferId = ib.get(0);
    }
    
    
    /**
     * 
     * @return
     */
    public int getBufferId(){
    	return bufferId;
    }
    
    
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
    public void saveToBuffer(){
    	interleavedBuffer.rewind();
    	glBindBufferARB(GL_ARRAY_BUFFER_ARB, bufferId);
        glBufferDataARB(GL_ARRAY_BUFFER_ARB, interleavedBuffer, GL_STATIC_DRAW_ARB);
        interleavedBuffer = null;
    }

    
    /**
     * The stride is how much space is between two tuples - 3 vertex floats, 3 color floats = 6. 6 x 4 = 24
     * the offset is how much space is between 0 and the first appearance of the first tuple. 3 vertex floats before 1st color. 3 x 4 = 12
     */
    public void render(){
    	
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        glBindBufferARB(GL_ARRAY_BUFFER_ARB, bufferId);
        
        glVertexPointer(3, GL_FLOAT, 32, 0);
        glColorPointer(3, GL_FLOAT, 32, 12);
        glTexCoordPointer(3, GL_FLOAT, 32, 24);

        glDrawArrays(GL_QUADS, 0, vertexCount);

        glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);

        glDisableClientState(GL_COLOR_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        
    }
    
    
    public void destroy(){
    	ib.put(0, bufferId);
        glDeleteBuffersARB(ib);
    }
}