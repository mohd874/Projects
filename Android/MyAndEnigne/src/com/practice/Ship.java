package com.practice;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class Ship extends Sprite{

	private AnimatedSprite fire;

	public Ship(float pX, float pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
	}

	public Ship(float pX, float pY, TextureRegion pTextureRegion, AnimatedSprite fire) {
		super(pX, pY, pTextureRegion);
		this.fire = fire;
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
		fire.setPosition(getX()+45, getY()+39);

		return true;
	}

	public void setFire(AnimatedSprite fire){
		this.fire = fire;
	}

}
