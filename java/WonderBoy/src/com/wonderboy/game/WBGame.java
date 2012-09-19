/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonderboy.game;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.background.ParallaxBackground;
import com.golden.gamedev.util.FileUtil;
import com.wonderboy.collide.HeroTileCollide;
import com.wonderboy.game.object.Hero;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;

/**
 *
 * @author Mohamed
 */
public class WBGame extends Game {

    {
        //When game is ready, make this true
        this.distribute = false;
    }
    private Hero hero;
    Background bg;
    SpriteGroup heroGroup;
    SpriteGroup tileGroup;
    PlayField playField;

    public static final int CROSS_TILE = 1;

    public WBGame() {
    }

    @Override
    public void initResources() {
//        // Image Engine
//        BufferedImage image    = getImage("image1.png");
//        BufferedImage[] images = getImages("image2.png", 3, 1);
//
//
//        // File I/O Engine
//        URL url = bsIO.getURL("level.txt");
//
//
//        // Sound Music Engine
//        playMusic("music1.mid");
//
//
//        // Timer Engine
//        setFPS(100);


//        bg = new ImageBackground(getImage("resource/test/waterfall.jpg"));
        // create the parallax background
        // no need to use transparency
        BufferedImage img = getImage("resource/test/waterfall.jpg", false);
        bg = new ParallaxBackground(new Background[]{
                    new Background(59 * 32, 15 * 32),
                    new ImageBackground(img, // the image background can not larger than w*32, h*32
                    (img.getWidth() > 59 * 32) ? 59 * 32 : img.getWidth(),
                    (img.getHeight() > 15 * 32) ? 15 * 32 : img.getHeight())
                });
        playField = new PlayField(bg);

        setHero(new Hero(this,getImages("resource/shion.png", 5, 4), getWidth() / 2, getHeight() / 2));
        getHero().setBackground(bg);
        bg.setToCenter(getHero());
        getHero().setAnimate(true);
        getHero().setLoopAnim(true);
        getHero().setAnimationTimer(new Timer(70));
        getHero().setAnimationFrame(0, 0);
//        playField.add(getHero());
        heroGroup = new SpriteGroup("Hero Group");
        heroGroup.add(hero);
        //create level
        String[] levelContent = FileUtil.fileRead(bsIO.getStream("resource/Level1.txt"));
        for (int i = 0; i < levelContent.length; i++) {
            String string = levelContent[i];
            if (!distribute) {
                System.out.println("levelContent #" + i + ": " + string);
            }
        }
        AnimatedSprite tileset = new AnimatedSprite(getImages("resource/ice_world_tileset.png", 6, 12));
        int TILE_W = 32;
        int TILE_H = 32;
        //////////// reading level dimension ////////////
        StringTokenizer token = new StringTokenizer(levelContent[0], " x ");
        int w = Integer.parseInt(token.nextToken()), // level width
                h = Integer.parseInt(token.nextToken());	// level height

        tileGroup = new SpriteGroup("ground tiles");
        tileGroup.setBackground(bg);
        playField.addGroup(tileGroup);
        playField.addGroup(heroGroup);
        playField.addCollisionGroup(heroGroup, tileGroup, new HeroTileCollide());
        if (!distribute) {
            System.out.println("h: " + h);
            System.out.println("w: " + w);
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

                int tile = 0;
                Sprite tileSprite;
                try {
                    // get tile from file content
                    char c = levelContent[i + 1].charAt(j);

                    switch (c) {
                        case '*':
                            tile = 100;
                            break;  // this is robo position
                        case 'X':
                            tile = 1000;
                            break; // this is the goal
                        default:
                            tile = Character.getNumericValue(c);
                            break;
                    }
                } catch (Exception e) {
                }

                switch (tile) {
                    case 1: // standard tile
                        tileSprite = new Sprite(tileset.getImage(1), j * TILE_W, i * TILE_H);
                        if (!distribute) {
                            System.out.println("j: " + j);
                            System.out.println("tileSprite: x:" + tileSprite.getX() + " y:" + tileSprite.getY());
                        }
                        tileGroup.add(tileSprite);
                        break;

                    case 2: // cross tile
                        tileSprite = new Sprite(tileset.getImage(13), j * TILE_W, i * TILE_H);
                        if (!distribute) {
                            System.out.println("j: " + j);
                            System.out.println("tileSprite: x:" + tileSprite.getX() + " y:" + tileSprite.getY());
                        }
                        tileGroup.add(tileSprite);
                        break;
                }

            }
        }
        if (!distribute) {
            System.out.println("SpriteGroup :" + tileGroup.getSize());
//        int x=0;
//        for (Sprite s : tileGroup.getSprites()) {
//            System.out.println("s #"+x+" x:"+s.getX()+" y:"+s.getY());
//            x++;
//        }
        }
    }

    @Override
    public void update(long timeElapsed) {
        bg.setToCenter(getHero());
        playField.update(timeElapsed);

        tileGroup.update(timeElapsed);
    }

    @Override
    public void render(Graphics2D g2d) {
//        g2d.setColor(Color.GRAY);
//        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        playField.render(g2d);
    }

    /**
     * @return the hero
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * @param hero the hero to set
     */
    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
