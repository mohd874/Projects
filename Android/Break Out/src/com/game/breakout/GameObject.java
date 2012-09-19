package com.game.breakout;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public abstract class GameObject{

	protected BreakOut g;
	protected RectF borders;

	protected float x;
	protected float y;
	protected float centerX;
	protected float centerY;
	protected Bitmap image;

	protected Paint p;

	protected GameObject(float x, float y, Bitmap image, BreakOut g){
		this.setX(x);
		this.setY(y);
		this.image = image;
		this.g = g;
		p = new Paint();
		borders = new RectF(x,y,x+image.getWidth(), y+image.getHeight());
		this.centerX = x + image.getWidth()/2;
		this.centerY = y + image.getHeight()/2;
	}

	public void update(){
		borders.set(getX(),getY(),getX()+image.getWidth(), getY()+image.getHeight());
		centerX = x + image.getWidth()/2;
		centerY = y + image.getHeight()/2;
	}

	public abstract void draw(Canvas c);

	public float distance(float x1, float x2, float y1, float y2){
		float diffX = 0;
		float diffY = 0;

		if(x1 > x2)
			diffX = Math.abs(x1 - x2);
		else
			diffX = Math.abs(x2 - x1);

		if(y1 > y2)
			diffY = Math.abs(y1 - y2);
		else
			diffY = Math.abs(y2 - y1);

		float d = (float) (Math.pow(diffX, 2)+Math.pow(diffY, 2));

		return (float) Math.sqrt(d);
	}

	public RectF getBorders(){
		return borders;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getX() {
		return x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getY() {
		return y;
	}


	public float getCenterX() {
		return centerX;
	}

	public void setCenterX(float centerX) {
		this.centerX = centerX;
	}

	public float getCenterY() {
		return centerY;
	}

	public void setCenterY(float centerY) {
		this.centerY = centerY;
	}

}