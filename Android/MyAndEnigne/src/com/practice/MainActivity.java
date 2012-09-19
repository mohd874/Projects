package com.practice;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends BaseGameActivity implements IOnSceneTouchListener{
    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//    }

	// ===========================================================
	// Constants
	// ===========================================================

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 750;

	private static final int BULLET_SPEED = -200;
	// ===========================================================
	// Fields
	// ===========================================================

	private Camera mCamera;

	private Texture mTexture;

	private TextureRegion mShipTextureRegion;
	private TiledTextureRegion mFireTextureRegion;
	private TextureRegion mBulletsTextureRegion;

	private Bullet[] bullets;
	private Ship ship;

	private ColorBackground bg;

	@Override
	public Engine onLoadEngine() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		IResolutionPolicy rrp = new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT);
		ScreenOrientation so = ScreenOrientation.PORTRAIT;
		EngineOptions engOpt = new EngineOptions(true, so , rrp , this.mCamera);
		return new Engine(engOpt);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onLoadResources() {
		this.mTexture = new Texture(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		TextureRegionFactory.setAssetBasePath("gfx/");
		this.mShipTextureRegion = TextureRegionFactory.createFromAsset(this.mTexture, this, "sb_ship.png", 0, 0);
		this.mFireTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTexture, this, "sb_fire.png", 50, 200, 2, 1);
		this.mBulletsTextureRegion = TextureRegionFactory.createFromAsset(this.mTexture, this, "sb_bullet.png", 0,200);

		this.getEngine().getTextureManager().loadTexture(mTexture);
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene(1);
		bg = new ColorBackground(0.09804f, 0.6274f, 0.8784f);
		scene.setBackground(bg);

		ship = new Ship(100, 50, this.mShipTextureRegion);
		scene.getTopLayer().addEntity(ship);
		scene.registerTouchArea(ship);
		scene.setTouchAreaBindingEnabled(true);

		final AnimatedSprite fire = new AnimatedSprite(ship.getX()+45, ship.getY()+39, this.mFireTextureRegion);
		fire.animate(100);
		scene.getTopLayer().addEntity(fire);

		ship.setFire(fire);

		bullets = new Bullet[10];
		for(int i=0;i<bullets.length;i++){
			bullets[i] = new Bullet(0, -8, mBulletsTextureRegion);
			scene.getTopLayer().addEntity(bullets[i]);
		}

		scene.setOnSceneTouchListener(this);
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onGameResumed() {
		// TODO Auto-generated method stub
		super.onGameResumed();
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
			this.runOnUpdateThread(new Runnable() {
				@Override
				public void run() {
					for(int i=0;i<bullets.length;i++){
						Bullet b = bullets[i];
						if(!b.isAlive()){
							b.setAlive(true);
							b.setPosition(ship.getX(),ship.getY());
							b.setVelocity(0, BULLET_SPEED);
							return;
						}
					}
				}
			});
		}
		return true;
	}






}