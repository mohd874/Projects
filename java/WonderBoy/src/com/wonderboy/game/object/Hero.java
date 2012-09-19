/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonderboy.game.object;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.ImageUtil;
import com.wonderboy.game.WBGame;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mohamed
 */
public class Hero extends AnimatedSprite {

    private WBGame game;
    private boolean shiftDown;
    private boolean jumping;
    private Timer jumpTimer;
    private boolean facing; //false is left, true is right
    private Timer attackCooldown;
    private boolean attaking;

    public Hero(WBGame game, BufferedImage[] images, double x, double y) {
        super(images, x, y);
        this.game = game;
        shiftDown = false;
        jumping = false;
        jumpTimer = new Timer(50);
        facing = true;
        attackCooldown = new Timer(70*3);
        attaking = false;
    }

    @Override
    public void update(long elapsedTime) {
        if (jumpTimer.isActive() && jumpTimer.action(elapsedTime)) {
            // incremental jump time has been used up
            jumpTimer.setActive(false);
        }

        if (attackCooldown.isActive() && attackCooldown.action(elapsedTime)) {
            // incremental jump time has been used up
            attackCooldown.setActive(false);
        }

        manageUserInput(elapsedTime);
        super.update(elapsedTime);
    }

    public void manageUserInput(long timeElapsed) {
        ///////// check for left or right movement /////////
        // maximum horizontal speed is 0.2
        // but when flying the speed is limited to 0.05
        double maxSpeed = 0.5;
        jumping = false;
        if(attackCooldown.isActive()==false){
            attaking=false;
        }
        if (!attaking) {
            if (game.keyDown(KeyEvent.VK_LEFT)) {
                // moving left
                // momentum speed :-)
                addHorizontalSpeed(timeElapsed, -0.002, -maxSpeed);
//			setDirection(LEFT);
//			setStatus(WALKING);
                if (facing != false) {
                    BufferedImage[] flippedImages = getImages();
                    facing = false;
                    for (int i = 0; i < flippedImages.length; i++) {
                        flippedImages[i] = ImageUtil.flip(flippedImages[i]);
                    }
                    this.setImages(flippedImages);
                }
                setAnimationFrame(5, 9);
            } else if (game.keyDown(KeyEvent.VK_RIGHT)) {
                // moving right
                addHorizontalSpeed(timeElapsed, 0.002, maxSpeed);
//			setDirection(RIGHT);
//			setStatus(WALKING);
                if (facing != true) {
                    BufferedImage[] flippedImages = getImages();
                    facing = true;
                    for (int i = 0; i < flippedImages.length; i++) {
                        flippedImages[i] = ImageUtil.flip(flippedImages[i]);
                    }
                    this.setImages(flippedImages);
                }
                setAnimationFrame(5, 9);
            } else { // left and right arrow not pressed
                // gradually stop robo horizontal speed
                if (getHorizontalSpeed() > 0) {
                    addHorizontalSpeed(timeElapsed, -0.05, 0);

                } else if (getHorizontalSpeed() < 0) {
                    addHorizontalSpeed(timeElapsed, 0.05, 0);

                } else { // getHorizontalSpeed() == 0
                    // robo is not moving, set status as standing
//				setStatus(STAND);
                    setAnimationFrame(0, 0);
                }
            }
        }

        ///////// check for jumping /////////
        if (game.keyPressed(KeyEvent.VK_UP)) {
            // debugging initial jump
//			System.out.println("pressing up key!! "+
//							   "flying = "+flying+
//							   ", jumping = "+jumping+
//							   ", vspeed = "+getVerticalSpeed());

            // use keyPressed(..) instead keyDown(..), to avoid repetitive jump
            if (!jumping && getVerticalSpeed() == 0) {
                // jump robo jump!
                jumping = true;
                setVerticalSpeed(-0.65);
                setAnimationFrame(9, 9);
                // activate incremental jump
                jumpTimer.setActive(true);
            }
        }
        if (game.keyPressed(KeyEvent.VK_A)) {
//            System.out.println("Attaack!!"+attackCooldown.isActive()+" time:"+attackCooldown.getCurrentTick());
            if (attackCooldown.isActive() == false) {
                if (getHorizontalSpeed() > 0) {
                    addHorizontalSpeed(timeElapsed, -0.05, 0);
                } else if (getHorizontalSpeed() < 0) {
                    addHorizontalSpeed(timeElapsed, 0.05, 0);
                } else {
                    setAnimationFrame(1, 3);
                }
                attackCooldown.setActive(true);
                attaking = true;
            } else {
                attackCooldown.action(timeElapsed);

            }
        }
//        else if (game.keyDown(controls[UP_KEY])) {
//			if (flying) {
//				// max vertical speed when flying
//				setVerticalSpeed(-maxSpeed);
//
//			} else if (jumping && getVerticalSpeed() < 0 &&
//					   jumpTimer.isActive()) {
//				// pressing up key while jumping
//				// increase jump speed
//				addVerticalSpeed(timeElapsed, -0.002, -1);
//			}
//
//		} else if (game.keyDown(controls[DOWN_KEY])) {
//			if (flying) {
//				setVerticalSpeed(maxSpeed);
//
//			} else if (getVerticalSpeed() == 0 &&
//					   game.keyDown(controls[SHIFT_KEY])) {
//				// shift down
//				shiftDown = true;
//			}
//
//		} else {
//			if (flying && getHorizontalSpeed() == 0) {
//				// when flying but not moving,
//				// the robo will slowly fall down
//				setVerticalSpeed(maxSpeed/2);
//			}
//		}


        // if not flying, robo will always fall down
        // this is called.... gravity :-)
        if (!jumping) {
            addVerticalSpeed(timeElapsed, 0.002, 0.5);
        }


    ///////// check for firing /////////
//		if (game.keyPressed(controls[FIRE_KEY]) &&
//			fireTimer.isActive() == false) {
//			// refresh refire timer
//			fireTimer.setActive(true);
//
//			game.playSound("sounds/Fire.wav");
//
//			// add projectile and fire animation
//			BufferedImage image = (getDirection() == LEFT) ?
//								  projectileImage[0] : projectileImage[1];
//
//			game.PROJECTILE.add(new Projectile(this));
//			game.playfield.add(new FireAnimation(this));
//		}
    }
//    public void manageUserInput(long timeElapsed){
//        if (game.keyPressed(KeyEvent.VK_ESCAPE)) {
//            game.finish();
//        }
//        if (game.keyDown(KeyEvent.VK_LEFT)) {
//            if(this.getX() != 0)
//            this.moveX(-timeElapsed * 0.1);
//        }
//        if (game.keyDown(KeyEvent.VK_RIGHT)) {
//            if(this.getX() != game.getWidth()-this.getWidth())
//            this.moveX(timeElapsed * 0.1);
//        }
//        if (game.keyDown(KeyEvent.VK_UP)) {
//            if(this.getY() != 0)
//            this.moveY(-timeElapsed * 0.1);
//        }
//        if (game.keyDown(KeyEvent.VK_DOWN)) {
//            if(this.getY() != game.getHeight()-this.getHeight())
//            this.moveY(timeElapsed * 0.1);
//        }
//        if (game.keyDown(KeyEvent.VK_SHIFT)) {
//            this.setShiftDown(true);
//        }else{
//            this.setShiftDown(false);
//        }
//    }

    /**
     * @return the shiftDown
     */
    public boolean isShiftDown() {
        return shiftDown;
    }

    /**
     * @param shiftDown the shiftDown to set
     */
    public void setShiftDown(boolean shiftDown) {
        this.shiftDown = shiftDown;
    }

    /**
     * @return the jumping
     */
    public boolean isJumping() {
        return jumping;
    }

    /**
     * @param jumping the jumping to set
     */
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    /**
     * @return the facing
     */
    public boolean isFacing() {
        return facing;
    }

    /**
     * @param facing the facing to set
     */
    public void setFacing(boolean facing) {
        this.facing = facing;
    }
}
