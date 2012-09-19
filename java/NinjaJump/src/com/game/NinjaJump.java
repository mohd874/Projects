package com.game;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.Log;

public class NinjaJump extends BasicGame {

	public NinjaJump(String title) {
		super(title);
	}
	public final String RESOURCES = "com/resources/";

	private Player player;

	public Platform wallRight;
	public Platform wallLeft;

	public Obstacal[] obs;
	private final int NUM_OF_OBSTACLES = 12;

	public boolean showScore;
	public int score;
	
	public Image background;
	
	public Collsion playerPlatformLCollsion;
	public Collsion playerPlatformRCollsion;
	public Collsion playerObstacleCollsion;
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		showScore = false;
		score = 0;
		
		float  initPosition;
		
		initWalls(gc);
		initObstacles(gc);
		setObstaclesPattern(gc);
		initPosition = getLeftWallObstaclePosition();
		initPlayer(gc, initPosition);
		initCollsions();
		background = new Image(RESOURCES+"background3.png");
	}


	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (player.isAlive) {
			updateScore();
			updateObstecales(gc, delta);
			player.update(gc, delta);
			updateWalls(gc, delta);
		}else{
			updatePlayerDead(gc, delta);
		}
		updateScoreBoard(gc);
	}

	private void updateScore() {
		score++;
	}
	
	public void render(GameContainer gc, Graphics g2d) throws SlickException {
		// TODO Auto-generated method stub
		g2d.setColor(Color.black);
		
		background.draw(0, 0);
		wallRight.render(gc, g2d);
		wallLeft.render(gc, g2d);
		player.render(gc, g2d);
		for (int i = 0; i < obs.length; i++) {
			obs[i].render(gc, g2d);
		}
		g2d.drawString(String.valueOf(score), 10, 30);
		if(showScore){
			String gameOver = "   Game Over";
			String scoreStr = " Your Score is: "+score;
			String retry = " Press Space or\nMouse Left Button\n to play again";
			g2d.drawString(gameOver+"\n"+scoreStr+"\n"+retry, gc.getWidth()/2-gameOver.length()*6, gc.getHeight()/2-20);
//			g2d.drawString(score, gc.getWidth()/2-gameOver.length()*4, gc.getHeight()/2);
//			g2d.drawString(retry, gc.getWidth()/2-gameOver.length()*4, gc.getHeight()/2+20);
		}
	
		//Debugging
//		debug(g2d);
	}
	
	private void initWalls(GameContainer gc) throws SlickException {
		wallRight = initWall(gc, false);
		wallLeft = initWall(gc, true);
	}

	private void initObstacles(GameContainer gc) throws SlickException {
		obs = new Obstacal[NUM_OF_OBSTACLES];
		for (int i = 0; i < obs.length; i++) {
			Obstacal o;
			if(i < Obstacal.SHORT_OBSTACLE){
				o = new Obstacal(new Image(RESOURCES + "wall_brick.png"), this);
			}else{
				o = new Obstacal(new Image(RESOURCES + "wall_brick_small.png"), this);
			}
//			o.setLocation(-1, -o.image.getHeight() - (i + 1) * 300);
			o.setLocation(-1, gc.getHeight());
			obs[i] = o;
		}
	}

	private Platform initWall(GameContainer gc, boolean isLeft) throws SlickException {
		Tile[] tiles = new Tile[22];
		for (int i = 0; i < tiles.length; i++) {
			Tile tile = new Tile(new Image(RESOURCES + "wallTile.png"));
			if(isLeft)
				tile.image = tile.image.getFlippedCopy(true, false);
			
			tiles[i] = tile;
		}
		
		Platform wall = new Platform(tiles, this);
		
		if(isLeft)
			wall.x = 0;
		else
			wall.x= gc.getWidth() - wall.getBorder().getWidth();
		
		wall.y = 0;
		wall.getBorder().setX(wall.getX());
		wall.getBorder().setY(wall.getY());
		wall.resetTilesLocations();
		
		return wall;
	}

	private void initPlayer(GameContainer gc, float initPosition) throws SlickException {
		Image img;
		
		Image[] runFramesLeft = new Image[6];
		for (int i = 0; i < runFramesLeft.length; i++) {
			img = new Image(RESOURCES + "p-run" + i + ".png");
			img.setRotation(90);
			runFramesLeft[i] = img;
		}

		Image[] runFramesRight = new Image[6];
		for (int i = 0; i < runFramesRight.length; i++) {
			img = new Image(RESOURCES + "p-run" + i + ".png");
			img = img.getFlippedCopy(true, false);
			img.setRotation(-90);
			runFramesRight[i] = img;
		}

		Image[] jumpFrames = new Image[5];
		for (int i = 0; i < jumpFrames.length; i++) {
			img = new Image(RESOURCES + "p-dead" + i + ".png");
			img.setRotation(-90);
			jumpFrames[i] = img;
		}
		
		Image[] deadFrames = new Image[5];
		for (int i = 0; i < jumpFrames.length; i++) {
			img = new Image(RESOURCES + "p-dead" + i + ".png");
			deadFrames[i] = img;
		}
		
//		SpriteSheet runSheetLeft = new SpriteSheet(new Image(RESOURCES+"player-run-sheet-left.png"), 63, 51);
//		SpriteSheet runSheetRight = new SpriteSheet(new Image(RESOURCES+"player-run-sheet-right.png"), 63, 51);
//		SpriteSheet deadSheet = new SpriteSheet(new Image(RESOURCES+"player-dead-sheet.png"), 32, 32);
//		SpriteSheet jumpSheet = new SpriteSheet(new Image(RESOURCES+"player-jump-sheet.png"), 48, 62);
		
		player = new Player(runFramesRight, runFramesLeft, jumpFrames, deadFrames, this);
//		player = new Player(runSheetLeft, runSheetRight, deadSheet, jumpSheet, this);
//		player.animationRunLeft = new Animation(runSheetLeft, 100);
//		player.animationRunRight = new Animation(runSheetRight, 100);
//		player.animationJump = new Animation(jumpSheet, 80);
//		player.animationDead = new Animation(deadSheet, 200);
//		player.animationDead.setLooping(false);
//		player.animationDead.setCurrentFrame(0);
		player.x = initPosition;
		player.y = gc.getHeight() - player.animationRunRight.getHeight()*2;
		player.isJumping = false;
		player.jumpSpeed = 0F;

	}


	private void initCollsions() {
		playerPlatformLCollsion = new PlayerPlatformCollsion(player.getBorder(), wallLeft.getBorder());
		playerPlatformRCollsion = new PlayerPlatformCollsion(player.getBorder(), wallRight.getBorder());
		Shape[] obsShapes = new Shape[obs.length];
		for (int i = 0; i < obsShapes.length; i++) {
			obsShapes[i] = obs[i].getBorder();
		}
		playerObstacleCollsion = new PlayerObstacleCollsion(player.getBorder(), obsShapes);
	}
	private void updateScoreBoard(GameContainer gc) throws SlickException {
		Input input = gc.getInput();
		if(showScore && (input.isKeyPressed(Input.KEY_SPACE) || input.isMousePressed(Input.MOUSE_LEFT_BUTTON))){
			init(gc);
		}
	}

	private void updatePlayerDead(GameContainer gc, int delta) {
		if(player.y < (gc.getHeight()+200)){
			player.y += player.jumpSpeed;
			player.jumpSpeed += 0.5F;
		}else{
			showScore = true;
		}
	}

	private void updateWalls(GameContainer gc, int delta) throws SlickException {
		wallLeft.update(gc, delta);
		wallRight.update(gc, delta);
	}

	private void updateObstecales(GameContainer gc, int delta) throws SlickException {
		if(isAllObstaclesOut(gc)){
			setObstaclesPattern(gc);
		}else{
			for (int i = 0; i < obs.length; i++) {
				obs[i].update(gc, delta);
			}
		}
	}

	private void setObstaclesPattern(GameContainer gc) {
		int i = (int) (Math.random()*4);
//		int i = 3;
		boolean isRight = true;
		int spaceBetweenWalls = 270;
		int numOfWalls = 1;
		
		switch(i){
			case Obstacal.ZIGZAG:
				numOfWalls = 1;
				for (int j = Obstacal.TALL_OBSTACLE; j < Obstacal.TALL_OBSTACLE+6; j++) {
					numOfWalls = doZigzagPattern(gc, spaceBetweenWalls,
							numOfWalls, j);
				}
			break;
			case Obstacal.PROJECTILE:
				isRight = new Random().nextBoolean();
				numOfWalls = 1;
				for (int j = Obstacal.SHORT_OBSTACLE; j < Obstacal.SHORT_OBSTACLE+6; j++) {
					numOfWalls = doProjectilePattern(gc, spaceBetweenWalls,
							numOfWalls, j);
				}
				
			break;
			case Obstacal.SYMMETRICAL:
				numOfWalls = 1;
				for (int x = Obstacal.SHORT_OBSTACLE; x < Obstacal.SHORT_OBSTACLE+6; x=x+2) {
					numOfWalls = doSymmetricalPattern(gc, spaceBetweenWalls,
							numOfWalls, x);
				}
			break;
			case Obstacal.MIX:
				numOfWalls = 1;
				int k = 0;
				for (int x = 0; x < obs.length; x++) {
					k = (int) (Math.random()*3);
					Log.info("k: "+k);
					isRight = new Random().nextBoolean();
					switch(k){
					case Obstacal.ZIGZAG:
							numOfWalls = doZigzagPattern(gc, spaceBetweenWalls,
									numOfWalls, x);
					break;
					case Obstacal.PROJECTILE:
							numOfWalls = doProjectilePattern(gc, spaceBetweenWalls,
									numOfWalls, x);
					break;
					case Obstacal.SYMMETRICAL:
						numOfWalls = doSymmetricalPattern2(gc, spaceBetweenWalls,
									numOfWalls, x);
					break;
					}
				}
			break;
		}
	}


	private int doSymmetricalPattern(GameContainer gc, int spaceBetweenWalls,
			int numOfWalls, int x) {
		Obstacal o1 = obs[x];
		Obstacal o2 = obs[x+1];
		
		numOfWalls++;
		
		o1.setX(getRightWallObstaclePosition(gc, obs[Obstacal.SHORT_OBSTACLE]));
		o2.setX(getLeftWallObstaclePosition());
		o1.setX(getRightWallObstaclePosition(gc, obs[Obstacal.SHORT_OBSTACLE]));
		o2.setX(getLeftWallObstaclePosition());
		
		o1.setY(-(spaceBetweenWalls * numOfWalls));
		o2.setY(-(spaceBetweenWalls * numOfWalls));
		return numOfWalls;
	}

	private int doSymmetricalPattern2(GameContainer gc, int spaceBetweenWalls,
			int numOfWalls, int x) {
		Obstacal o1 = obs[x];
		
		numOfWalls++;
		
		o1.setX(getRightWallObstaclePosition(gc, obs[Obstacal.SHORT_OBSTACLE]));
		o1.setX(getRightWallObstaclePosition(gc, obs[Obstacal.SHORT_OBSTACLE]));
		
		o1.setY(-(spaceBetweenWalls * numOfWalls));
		return numOfWalls;
	}
	

	private int doProjectilePattern(GameContainer gc, int spaceBetweenWalls,
			int numOfWalls, int j) {
		boolean isRight;
		Obstacal o = obs[j];
		
		isRight = new Random().nextBoolean();
		numOfWalls++;
		
		if(isRight){
//			o.setX(getRightWallObstaclePosition(gc, obs[Obstacal.SHORT_OBSTACLE]));
			o.setXSpeed(-0.7F);
		}else{
//			o.setX(getLeftWallObstaclePosition());
			o.setXSpeed(0.7F);
		}
		
		o.setX(new Random().nextFloat()*gc.getWidth());
		o.setY(-(spaceBetweenWalls * numOfWalls));
		
//		Log.info(j+"");
		return numOfWalls;
	}


	private int doZigzagPattern(GameContainer gc, int spaceBetweenWalls,
			int numOfWalls, int j) {
		boolean isRight;
		Obstacal o = obs[j];
		
		isRight = new Random().nextBoolean();
		numOfWalls++;
		
		if(isRight){
			if(j >= Obstacal.SHORT_OBSTACLE)
				o.setX(getRightWallObstaclePosition(gc, obs[Obstacal.SHORT_OBSTACLE]));
			else
				o.setX(getRightWallObstaclePosition(gc, obs[Obstacal.TALL_OBSTACLE]));
		}else{
			o.setX(getLeftWallObstaclePosition());
		}
		
		o.setY(-(spaceBetweenWalls * numOfWalls));
//		Log.info(j+"");
		return numOfWalls;
	}

	public float getLeftWallObstaclePosition() {
		return wallLeft.getBorder().getWidth();
	}

	public float getRightWallObstaclePosition(GameContainer gc) {
		return getRightWallObstaclePosition(gc, obs[0]);
	}

	public float getRightWallObstaclePosition(GameContainer gc, Obstacal obs) {
		return gc.getWidth() - wallRight.getBorder().getWidth() - obs.getBorder().getWidth();
	}
	
	private boolean isAllObstaclesOut(GameContainer gc) {
		for (int i = 0; i < obs.length; i++) {
			Obstacal o = obs[i];
			if(o.y < gc.getHeight())
				return false;
		}
		return true;
	}


	private void debug(Graphics g2d) {
		g2d.setColor(Color.red);
		g2d.draw(wallRight.getBorder());
		g2d.draw(wallLeft.getBorder());
		g2d.draw(player.getBorder());
		for (Obstacal o : obs) {
			g2d.draw(o.getBorder());
		}
	}

}
