package com.game;


import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Platform extends Actor{
	public Tile[] tiles;
	
	public Platform(Tile[] tiles, BasicGame game) {
		// TODO Auto-generated constructor stub
		this.tiles = tiles;
		border = new Rectangle(x, y, 
				tiles[0].image.getWidth(), 
				tiles[0].image.getHeight()*tiles.length);
		this.game = game;
	}
	
	public void resetTilesLocations() {
		for (int i = 0; i < tiles.length; i++) {
			Tile t = tiles[i];
			t.x = border.getX();
			t.y = (i-1)*t.image.getHeight();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		for (int i = 0; i < tiles.length; i++) {
			Tile t = tiles[i];
			t.y += 3F;
			if(t.y > gc.getHeight()+20){
				t.y = -t.image.getHeight(); 
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g2d) throws SlickException {
		for (int i = 0; i < tiles.length; i++) {
			Tile tile = tiles[i];
			tile.x = this.x;
			tile.draw();
		}
	}
}
