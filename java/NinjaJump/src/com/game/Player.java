package com.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

/*
 * Sonic Sprite from: http://www.spriters-resource.com/gameboy_advance/sonicadv3/sheet/7143
 * 					  http://www.spriters-resource.com/gameboy_advance/sonicadv3/index.html
 */
public class Player extends Actor{
	public Animation animationRunRight;
	public Animation animationRunLeft;
	public Animation animationJump;
	public Animation animationDead;
	public boolean isJumping;
	public float jumpSpeed;
	public boolean isOnLeftSide;
	
	public Player(Image[] framesRight, Image[] framesLeft, Image[] jumpFrames, Image[] deadFrames, BasicGame game) {
		this.animationRunRight = new Animation(framesRight, 100);
		this.animationRunLeft = new Animation(framesLeft, 100);
		this.animationJump = new Animation(jumpFrames, 75);
		this.animationDead = new Animation(deadFrames, 75);
		border = new Circle(x+animationRunRight.getWidth()/2, y+animationRunRight.getHeight()/2, animationRunRight.getWidth()/2);
		isOnLeftSide = true;
		isAlive = true;
		this.game = game;
	}

	public Player(SpriteSheet runSheetLeft, SpriteSheet runSheetRight, SpriteSheet deadSheet, SpriteSheet jumpSheet, BasicGame game){
		this.animationRunLeft = new Animation(runSheetLeft, 100);
		this.animationRunRight = new Animation(runSheetRight, 100);
		this.animationJump = new Animation(jumpSheet, 75);
		this.animationDead = new Animation(deadSheet, 150);
		this.animationDead.setLooping(false);
		this.animationDead.setCurrentFrame(0);
		
		border = new Circle(x+animationRunRight.getWidth()/2, y+animationRunRight.getHeight()/2, animationRunRight.getWidth()/2);
		isOnLeftSide = true;
		isAlive = true;
		
		this.game = game;
		
	}
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		NinjaJump g = (NinjaJump) game;
		
		Input input = gc.getInput();
		border.setX(x);
		border.setY(y);
		
		if(g.playerObstacleCollsion.checkCollsion()){
			isAlive = false;
			jumpSpeed = -9F;
		}
		
		if (isJumping) {
			if ((isOnLeftSide && g.playerPlatformRCollsion.checkCollsion())
					|| (!isOnLeftSide && g.playerPlatformLCollsion.checkCollsion())) {
				isJumping = false;
				jumpSpeed = 0F;
				isOnLeftSide = !isOnLeftSide;
				if(isOnLeftSide){
					x = g.getLeftWallObstaclePosition();
				}else{
					x = gc.getWidth()-g.wallRight.getBorder().getWidth()-getBorder().getWidth();
				}
			}
		}

		if((input.isKeyPressed(Input.KEY_SPACE) || input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) && !isJumping){
			isJumping = true;
			if(x <= 33){
				jumpSpeed = 5F;//old 0.4F
			}else{
				jumpSpeed = -5F;
			}
		}
		x += jumpSpeed;
	}

	@Override
	public void render(GameContainer gc, Graphics g2d) throws SlickException {
				if(!isAlive){
					animationDead.draw(x, y);
				}else if(isJumping){
					animationJump.draw(x, y);
				}else{
					if(x < 34){
						animationRunLeft.draw(x, y);
					}else{
						animationRunRight.draw(x, y);
					}
				}
	}
}