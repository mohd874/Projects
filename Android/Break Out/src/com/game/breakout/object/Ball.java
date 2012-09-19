package com.game.breakout.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.game.breakout.object.Bar;
import com.game.breakout.object.Block;
import com.game.breakout.BreakOut;
import com.game.breakout.GameObject;

public class Ball extends GameObject{

	boolean positiveX;
	boolean positiveY;
	float velocityX;
	float velocityY;
	Bar bar;
	Block[] blocks;

	private final float MAX_SPEED = 160f;
	private final float SPEED_DIFF = 30f;

	public Ball(float x, float y, Bitmap image, BreakOut g, Bar bar, Block[] blocks){
		super(x,y,image,g);
		this.bar = bar;
		this.blocks = blocks;
		positiveX = true;
		positiveY = false;
		velocityX = MAX_SPEED/2;
		velocityY = MAX_SPEED/2;
	}

	public void update(){
		super.update();
//		float distance = distance(x, bar.getX(), y, bar.getY());

//		Log.d("Ball update", "distance: "+(distance)+" vs width: "+image.getWidth()+" vs height: "+image.getHeight());
		boolean barIntersect = bar.getBorders().intersect(borders);
		boolean blockIntersect = false;
		for(int i=0;i<blocks.length;i++){
			if(blocks[i].getBorders().intersect(borders) && !blocks[i].isBroken()){
				blockIntersect = true;
				blocks[i].breakMe();
				break;
			}
		}

		if(getX() <= 0 && velocityX < 0)
			velocityX *= -1;
		else if(getX() >=g.getWidth()-image.getWidth() && velocityX > 0)
			velocityX *= -1;

		//if the ball collide with the bar
		if(barIntersect){
			if(this.centerX > bar.getCenterX())
				velocityX = Math.abs(velocityX);
			else if(this.centerX < bar.getCenterX())
				velocityX = Math.abs(velocityX) * (-1);

			ballRedirect();
			velocityY = Math.abs(velocityY) * (-1);
		}

		if(getY() <= 0 && velocityY <= 0){
			velocityY = Math.abs(velocityY);
		}
		//if the ball hit a block while moving up, it should go down
		if(blockIntersect){
			velocityY *= -1;
		}

		if(getY() >=g.getHeight())g.gameEnd();

		setX(getX() + (velocityX/10));
		setY(getY() + (velocityY/10));

		this.borders.set(x, y, x+this.image.getWidth(), y+image.getHeight());
	}

	private void ballRedirect() {
		float xLoc = Math.abs(bar.getCenterX() - this.centerX);
		float halfWidthBar = bar.getWidth()/2;
		velocityX = (xLoc/halfWidthBar)*100;
		if(velocityX > (MAX_SPEED-SPEED_DIFF))velocityX = MAX_SPEED - SPEED_DIFF;
		velocityY = MAX_SPEED - velocityX;

		if(bar.getCenterX() > this.centerX){
			velocityX *= -1;
		}

		String tag = "BallRedirect";
		String msg = "bar.width/2: "+halfWidthBar+" | xLoc: "+xLoc+" | velocityX: "+velocityX+" | velocityY: "+velocityY;

		Log.i(tag, msg);
	}

	public void draw(Canvas c){
		c.drawBitmap(image, getX(), getY(), p);

		//test
		//draw center
//		p.setARGB(150, 255, 0, 0);
//		c.drawRect(centerX, centerY, centerX+5, centerY+5, p);

//		c.drawRect(this.borders, p);
	}
}