package com.main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Test extends BasicGame{

	private static final String RESOURCES = "com/resources/";

	public Test(String title) {
		super(title);
	}

	Image[] wallTiles;
	Animation player;
	@Override
	public void init(GameContainer gc) throws SlickException {
		SpriteSheet runSheet = new SpriteSheet(new Image(RESOURCES+"player-run-sheet.png"), 51, 63);
		player = new Animation(runSheet, 200);
		player.setLooping(true);
		player.setAutoUpdate(true);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		player.update(delta);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		player.draw(gc.getWidth()/2, gc.getHeight()/2);
	}

	public static void main(String[] args) {
		AppGameContainer app;
		try {
			app = new AppGameContainer(new Test("Testing Stuff"));
			app.setDisplayMode(300, 600, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
