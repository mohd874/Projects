package com.game;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public abstract class Actor {

	protected float x;
	protected float y;
	protected Shape border;
	protected boolean isAlive;
	protected BasicGame game;
		
	public abstract void update(GameContainer gc, int delta) throws SlickException ;
	
	public abstract void render(GameContainer gc, Graphics g2d) throws SlickException;

	public void setLocation(float x, float y){
		setX(x);
		setY(y);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Shape getBorder() {
		return border;
	}

	public void setBorder(Shape border) {
		this.border = border;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	
}
