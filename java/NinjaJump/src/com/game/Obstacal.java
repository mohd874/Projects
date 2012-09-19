package com.game;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Obstacal extends Actor {

	public Image image;
	
	//Patterns
	public static final int ZIGZAG=0;
	public static final int MIX=3;
	public static final int SYMMETRICAL=2;
	public static final int PROJECTILE=1;
	
	//Obstacles types indexes
	public static final int TALL_OBSTACLE=0;
	public static final int SHORT_OBSTACLE=6;
	
	private float xSpeed;
	
	public Obstacal(Image image, BasicGame game) {
		this.image = image;
		border = new Rectangle(x, y, image.getWidth(), image.getHeight());
		this.game = game;
		setXSpeed(0);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		y = y + 5F;
		x = x + getXSpeed();
		
		if(x > gc.getWidth())setX(-this.image.getWidth());
		else if(x < -this.image.getWidth())setX(gc.getWidth());
		
		border.setX(x);
		border.setY(y);
	}

	@Override
	public void render(GameContainer gc, Graphics g2d) throws SlickException {
		image.draw(x, y);
	}

	public float getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}
}
