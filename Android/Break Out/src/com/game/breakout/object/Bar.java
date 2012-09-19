package com.game.breakout.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.game.breakout.BreakOut;
import com.game.breakout.GameObject;

public class Bar extends GameObject{


	public Bar(float x, float y, Bitmap image, BreakOut g){
		super(x,y,image,g);
		borders.set(x-image.getWidth()/2,y,x+image.getWidth()/2, y+image.getHeight());
	}

	public void update(){
		super.update();
		setX(g.getTouchX());
		centerX = x;
		borders.set(getX()-image.getWidth()/2,getY(),getX()+image.getWidth()/2, getY()+image.getHeight());
	}

	public void draw(Canvas c){
		c.drawBitmap(image, getX()-image.getWidth()/2, g.getHeight()-image.getHeight(), p);

		//test
		//draw center
//		p.setARGB(150, 255, 0, 0);
//		c.drawRect(centerX, centerY, centerX+5, centerY+5, p);
		//draw borders

//		c.drawRect(borders, p);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Bitmap getImage(){
		return image;
	}

	public int getWidth() {
		return image.getWidth();
	}
}