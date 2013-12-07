package com.helion3.opengl;

public class Launcher {
	
 
	/**
	 * 
	 */
    private Launcher() {}

    
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
    	Game game = Game.getInstance();
    	try {
			game.start();
		} catch (Exception e) {
			e.printStackTrace();
 		}
    }
}