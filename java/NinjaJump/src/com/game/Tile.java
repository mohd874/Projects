package com.game;

import org.newdawn.slick.Image;

public class Tile {

	public Image image;
	public float x;
	public float y;
	
	public Tile(Image image) {
		this.image = image;
	}

	public void draw() {
		image.draw(this.x, this.y);
	}
}
