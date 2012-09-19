package com.game;

import org.newdawn.slick.geom.Shape;

public class PlayerObstacleCollsion implements Collsion{

	private Shape playerShape;
	private Shape[] obstacleShapes;
	
	public PlayerObstacleCollsion(Shape playerShape,Shape[] obstacleShapes) {
		this.playerShape = playerShape;
		this.obstacleShapes = obstacleShapes;
	}
	
	public boolean checkCollsion() {
		for (int i = 0; i < obstacleShapes.length;i++) {
			Shape obsShape = obstacleShapes[i];
			if(checkCollsion(playerShape, obsShape)){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollsion(Shape playerShape, Shape obstacleShape){
		return playerShape.intersects(obstacleShape);
	}
}
