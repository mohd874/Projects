/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonderboy.collide;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.CollisionShape;
import com.wonderboy.game.WBGame;
import com.wonderboy.game.object.Hero;

/**
 *
 * @author mohammad
 */
public class HeroTileCollide extends AdvanceCollisionGroup{

    @Override
    public CollisionShape getCollisionShape1(Sprite hero) {
		    // hero collision offset, cut 6 pixels at each side
			rect1.setBounds(hero.getX() + 6, hero.getY(),
							hero.getWidth() - 12, hero.getHeight());

	        return rect1;
	    }

    @Override
    public boolean isCollide(Sprite hero, Sprite tile,
								 CollisionShape heroShape, CollisionShape tileShape) {
		    boolean ignoreCollision = false;

			if (heroShape.intersects(tileShape)) {
				if (tile.getID() == WBGame.CROSS_TILE) {
					// this is cross tile
					if (((Hero) hero).isShiftDown()) {
						// if robo is shifting down
						// then assume no collision
						return false;
					}

					// we calculate the bottom of our robo (y + height)
					double bottomHero = Math.ceil(hero.getOldY()) + hero.getHeight();

				   	// robo "can" through crosstile type from the bottom
					// no collision if the robo is below the crosstile
					ignoreCollision = (tile.getY() < bottomHero);
				}
			}

			return (ignoreCollision) ? false :
				   super.isCollide(hero, tile, heroShape, tileShape);
		}

    @Override
    public void collided(Sprite hero, Sprite tile) {
			// collision with a tile, stop the robo
			// and revert back to last position
			revertPosition1();

		    if (collisionSide == BOTTOM_TOP_COLLISION) {
			    // robo is on top of the tile, set vertical speed to 0
		    	hero.setVerticalSpeed(0);
		    	((Hero) hero).setJumping(false);

		    } else if (collisionSide == LEFT_RIGHT_COLLISION ||
		    		   collisionSide == RIGHT_LEFT_COLLISION) {
				// robo bump into the tile, set horizontal speed to 0
		    	hero.setHorizontalSpeed(0);
			}
		}

}
