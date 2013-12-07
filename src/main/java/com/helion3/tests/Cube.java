package com.helion3.tests;


public class Cube {
	
	
	/**
	 * 
	 * @param tesselator
	 * @param xOffset
	 * @param yOffset
	 * @param zOffset
	 */
	public static void draw( QuadTesselator tesselator, int xOffset, int yOffset, int zOffset){
		
//		float textureDim = 0.0625f;
//		
//		float topLeftX = textureDim * 1;
//		float topLeftY = textureDim * 1;
//		float topRightX = topLeftX + textureDim;
//		float topRightY = topLeftY;
//		float bottomLeftX = topLeftX;
//		float bottomLeftY = topLeftY + textureDim;
//		float bottomRightX = topRightX;
//		float bottomRightY = topRightY + textureDim;
//
//		float tint = 1f;
//		float vertexOffset = 5f;
		
	

//		tesselator.addColor(tint, tint, tint);
////		tesselator.addTextureCoord(bottomLeftX,bottomLeftY);
//		tesselator.addVertex(-(vertexOffset) + xOffset,-(vertexOffset) + yOffset,+(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint, tint, tint);
////		tesselator.addTextureCoord(bottomRightX,bottomRightY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint, tint, tint);
////		tesselator.addTextureCoord(topRightX,topRightY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);
//		
//		
//		tesselator.addColor(tint, tint, tint);
////		tesselator.addTextureCoord(topLeftX,topLeftY);
//		tesselator.addVertex(-(vertexOffset)+xOffset,+(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);
		
		
		
		
	
		
//		// SOUTH FACE
//		tesselator.addColor(tint-side2, tint-side2, tint-side2);
//		tesselator.addTextureCoord(bottomLeftX,bottomLeftY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint-side2, tint-side2, tint-side2);
//		tesselator.addTextureCoord(bottomRightX,bottomRightY);
//		tesselator.addVertex(-(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint-side, tint-side, tint-side);
//		tesselator.addTextureCoord(topRightX,topRightY);
//		tesselator.addVertex(-(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint-side, tint-side, tint-side);
//		tesselator.addTextureCoord(topLeftX,topLeftY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//	
//		// EAST FACE
//		tesselator.addColor(tint-side2, tint-side2, tint-side2);
//		tesselator.addTextureCoord(bottomLeftX,bottomLeftY);
//		tesselator.addVertex(-(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint-side2, tint-side2, tint-side2);
//		tesselator.addTextureCoord(bottomRightX,bottomRightY);
//		tesselator.addVertex(-(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint-side, tint-side, tint-side);
//		tesselator.addTextureCoord(topRightX,topRightY);
//		tesselator.addVertex(-(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint-side, tint-side, tint-side);
//		tesselator.addTextureCoord(topLeftX,topLeftY);
//		tesselator.addVertex(-(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//			
//		// WEST FACE
//		tesselator.addColor(tint-side2, tint-side2, tint-side2);
//		tesselator.addTextureCoord(bottomLeftX,bottomLeftY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint-side2, tint-side2, tint-side2);
//		tesselator.addTextureCoord(bottomRightX,bottomRightY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint-side, tint-side, tint-side);
//		tesselator.addTextureCoord(topRightX,topRightY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint-side, tint-side, tint-side);
//		tesselator.addTextureCoord(topLeftX,topLeftY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);	
//
//		// TOP FACE	
//		tesselator.addColor(tint, tint, tint);
//		tesselator.addTextureCoord(bottomLeftX,bottomLeftY);
//		tesselator.addVertex(-(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);
//								
//		tesselator.addColor(tint, tint, tint);
//		tesselator.addTextureCoord(bottomRightX,bottomRightY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);
//					
//		tesselator.addColor(tint, tint, tint);
//		tesselator.addTextureCoord(topRightX,topRightY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint, tint, tint);
//		tesselator.addTextureCoord(topLeftX,topLeftY);
//		tesselator.addVertex(-(vertexOffset)+ xOffset,+(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//				
//		// BOTTOM FACE
//		tesselator.addColor(tint, tint, tint);
//		tesselator.addTextureCoord(bottomLeftX,bottomLeftY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint, tint, tint);
//		tesselator.addTextureCoord(bottomRightX,bottomRightY);
//		tesselator.addVertex(+(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint, tint, tint);
//		tesselator.addTextureCoord(topRightX,topRightY);
//		tesselator.addVertex(-(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,+(vertexOffset) + zOffset);
//		
//		tesselator.addColor(tint, tint, tint);
//		tesselator.addTextureCoord(topLeftX,topLeftY);
//		tesselator.addVertex(-(vertexOffset)+ xOffset,-(vertexOffset)+ yOffset,-(vertexOffset) + zOffset);	

	}
}