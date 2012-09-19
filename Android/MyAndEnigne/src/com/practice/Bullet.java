package com.practice;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class Bullet extends Sprite {

	private boolean isAlive;

	public Bullet(float pX, float pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
		isAlive = false;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		if(getY() < -getHeight()){
			setAlive(false);
			setVelocity(0, 0);
		}
	}
}
