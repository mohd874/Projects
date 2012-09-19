package com.game;

import org.newdawn.slick.geom.Shape;

public class PlayerPlatformCollsion implements Collsion {

	private Shape playerShape;
	private Shape platformShape;
	
	public PlayerPlatformCollsion(Shape playerShape, Shape platformShape) {
		this.playerShape = playerShape;
		this.platformShape = platformShape;
	}
	
	public boolean checkCollsion() {
		return playerShape.intersects(platformShape);
	}
	
	
}
