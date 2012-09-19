package com.game.breakout.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.game.breakout.BreakOut;
import com.game.breakout.GameObject;

public class Block extends GameObject{

	boolean broken;
	public Block(float x, float y, Bitmap image, BreakOut g){
		super(x,y,image,g);
		broken = false;
//		Log.d("","Block x: "+x+" y: "+y);
	}

	public boolean isBroken() {
		return broken;
	}

	public void update(){
		if(broken)
			return;
	}

	public void draw(Canvas c){
		if(broken)
			return;

		c.drawBitmap(image, getX(), getY(), p);
	}

	public void breakMe(){
		broken = true;
	}
}