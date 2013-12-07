package com.helion3.tests;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class Game {
	
	private static Game instance;
	
    /**
     * 
     * @return
     */
    public static Game getInstance(){
    	if( instance == null ) instance = new Game();
    	return instance;
    }
    
    
    /**
	 * Public static
	 */
    public static final String name = "Entropy";
	public static final Logger log = Logger.getLogger(name);
	public static final Properties prop = new Properties();
	public static final short GAME_WIDTH = 1024;
	public static final short GAME_HEIGHT = 768;
	public static int delta;
	
	/**
	 * Private
	 */
	private long lastFrame;			
	private int fps;	
	private long lastFPS;
	private Tesselator tesselator = new Tesselator();
	private TriangleTesselator triangleTesselator = new TriangleTesselator();
	private Texture texture;
	    	
	/**
	 * 
	 */
    public Game(){}
    
    
	/**
	 * 
	 * @param name
	 * @throws LWJGLException 
	 */
    public void start() throws LWJGLException {
		
		// Init display
		Display.setResizable(true);
        Display.setTitle( Game.name );
        Display.setVSyncEnabled(true);
        Display.setDisplayMode(new DisplayMode(Game.GAME_WIDTH, Game.GAME_HEIGHT));
        
        // Set viewport
        PixelFormat pixelFormat = new PixelFormat();
		ContextAttribs contextAtrributes = new ContextAttribs(3, 0);
		Display.create(pixelFormat, contextAtrributes);
		glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
		
        initGL();
        getDelta();
        lastFPS = getTime();

        // Begin loop
        displayLoop();
		
	}
	    
	    
    /**
	 * 
	 */
	protected void displayLoop(){
		while (!Display.isCloseRequested()){
			if (Display.wasResized()) {
				initGL();
			}
			getDelta();
			update();
			render();
			Display.update();
		}
		Display.destroy();
		System.exit(0);
	}
		
		
	/**
	 * 
	 */
	protected void initGL(){
		
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

//		int width = Display.getDisplayMode().getWidth(); // Get window width
//		int height = Display.getDisplayMode().getHeight(); // Get window height
//
//		GL11.glViewport(0, 0, width, height); // Reset The Current Viewport
//		GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
//		GL11.glLoadIdentity(); // Reset The Projection Matrix
//		//60.0f is the Field of Vision
//		
//		GLU.gluPerspective(60f, ((float) width / (float) height), 0.1f, 200.0f); // Calculate The Aspect Ratio Of The Window
//		GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix
//		GL11.glLoadIdentity(); // Reset The Modelview Matrix
//
//		GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping 
//		GL11.glClearColor(0.1f, 0.4f, 0.6f, 0.0f); // Background R G B A
//		GL11.glClearDepth(1.0f); // Depth Buffer Setup
//		GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
//		GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Test To Do
//		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST); // Really Nice Perspective Calculations
//		
////		//Cull faces - Only draw the front face
////		GL11.glCullFace(GL11.GL_BACK);
////		GL11.glEnable(GL11.GL_CULL_FACE);
//		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/terrain.png"));
			texture.bind();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	protected void updateFPS(){
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	protected int getDelta(){
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    if (delta == 0) {
	    	delta = 1;
	    }
	    Game.delta = delta;
	    return delta;
	}
	
	
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	protected long getTime(){
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	
	/**
	 * 
	 * @param delta
	 */
	protected void update(){
		
		updateFPS();

	}
	

	/**
	 * Render
	 * @todo move to screens
	 */
	private void render() {
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
		
		drawTextureSquare();
//		drawCube();
		
	}
	
	
	/**
	 * 
	 */
	protected void drawTextureSquare(){
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		float textureDim = 0.0625f;
		
		float topLeftX = textureDim * 1;
		float topLeftY = textureDim * 1;
		float topRightX = topLeftX + textureDim;
		float topRightY = topLeftY;
		float bottomLeftX = topLeftX;
		float bottomLeftY = topLeftY + textureDim;
		float bottomRightX = topRightX;
		float bottomRightY = topRightY + textureDim;
		
		// top left
		triangleTesselator.addVertex(-0.5f, +0.5f, 0f);
		triangleTesselator.addColor(1, 0, 1);
		triangleTesselator.addTextureCoord(topLeftX,topLeftY);
		
		// bottom left
		triangleTesselator.addVertex(-0.5f, -0.5f, 0f);
		triangleTesselator.addColor(1, 0, 0);
		triangleTesselator.addTextureCoord(bottomLeftX,bottomLeftY);
		
		// bottom right
		triangleTesselator.addVertex(+0.5f, -0.5f, 0f);
		triangleTesselator.addColor(0, 1, 0);
		triangleTesselator.addTextureCoord(bottomRightX,bottomRightY);

		// top right
		triangleTesselator.addVertex(+0.5f, +0.5f, 0f);
		triangleTesselator.addColor(0, 0, 1);
		triangleTesselator.addTextureCoord(topRightX,topRightY);

		triangleTesselator.render();
		
	}
	
	
	/**
	 * 
	 */
	protected void drawSquare(){
		
		// top left
		triangleTesselator.addVertex(-0.5f, +0.5f, 0f);
		triangleTesselator.addColor(1, 0, 1);
		
		// bottom left
		triangleTesselator.addVertex(-0.5f, -0.5f, 0f);
		triangleTesselator.addColor(1, 0, 0);
		
		// bottom right
		triangleTesselator.addVertex(+0.5f, -0.5f, 0f);
		triangleTesselator.addColor(0, 1, 0);

		// top right
		triangleTesselator.addVertex(+0.5f, +0.5f, 0f);
		triangleTesselator.addColor(0, 0, 1);

		triangleTesselator.render();
		
	}
	
	
	/**
	 * 
	 */
	protected void drawTriangle(){
		
		// bottom left
		triangleTesselator.addVertex(-0.5f, -0.5f, 0f);
		triangleTesselator.addColor(1, 0, 0);
		
		// bottom right
		triangleTesselator.addVertex(+0.5f, -0.5f, 0f);
		triangleTesselator.addColor(0, 1, 0);

		// top right
		triangleTesselator.addVertex(+0.5f, +0.5f, 0f);
		triangleTesselator.addColor(0, 0, 1);

		triangleTesselator.render();
		
	}
	
	
	/**
	 * 
	 */
	protected void drawCube(){
		
		
		GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping 
		texture.bind();
		GL13.glActiveTexture(GL_TEXTURE0 + 0);
//		glEnable(GL11.GL_CULL_FACE);
		
		float textureDim = 0.0625f;
		
		float topLeftX = textureDim * 1;
		float topLeftY = textureDim * 1;
		float topRightX = topLeftX + textureDim;
		float topRightY = topLeftY;
		float bottomLeftX = topLeftX;
		float bottomLeftY = topLeftY + textureDim;
		float bottomRightX = topRightX;
		float bottomRightY = topRightY + textureDim;
		float vertexOffset = 0.5f;
		
		// north
		tesselator.addColor(1f, 1f, 1f);
		tesselator.addTextureCoord(bottomLeftX,bottomLeftY);
		tesselator.addVertex(-(vertexOffset),-(vertexOffset),+(vertexOffset));
		
		tesselator.addColor(1f, 1f, 1f);
		tesselator.addTextureCoord(bottomRightX,bottomRightY);
		tesselator.addVertex(+(vertexOffset),-(vertexOffset),+(vertexOffset));
		
		tesselator.addColor(1f, 1f, 1f);
		tesselator.addTextureCoord(topRightX,topRightY);
		tesselator.addVertex(+(vertexOffset),+(vertexOffset),+(vertexOffset));
		
		tesselator.addColor(1f, 1f, 1f);
		tesselator.addTextureCoord(topLeftX,topLeftY);
		tesselator.addVertex(-(vertexOffset),+(vertexOffset),+(vertexOffset));
		
		// east
		tesselator.addColor(1f, 1f, 1f);
		tesselator.addTextureCoord(bottomLeftX,bottomLeftY);
		tesselator.addVertex(-(vertexOffset),-(vertexOffset),-(vertexOffset) );
		
		tesselator.addColor(1f, 1f, 1f);
		tesselator.addTextureCoord(bottomRightX,bottomRightY);
		tesselator.addVertex(-(vertexOffset),-(vertexOffset),+(vertexOffset));
		
		tesselator.addColor(1f, 1f, 1f);
		tesselator.addTextureCoord(topRightX,topRightY);
		tesselator.addVertex(-(vertexOffset),+(vertexOffset),+(vertexOffset));
		
		tesselator.addColor(1f, 1f, 1f);
		tesselator.addTextureCoord(topLeftX,topLeftY);
		tesselator.addVertex(-(vertexOffset),+(vertexOffset),-(vertexOffset) );
		
		tesselator.render();
	}
}