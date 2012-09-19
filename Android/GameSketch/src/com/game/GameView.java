package com.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

/**
 * View that draws, takes keystrokes, etc. for a simple LunarLander game.
 *
 * Has a mode which RUNNING, PAUSED, etc. Has a x, y, dx, dy, ... capturing the
 * current ship physics. All x/y etc. are measured with (0,0) at the lower left.
 * updatePhysics() advances the physics based on realtime. draw() renders the
 * ship, and does an invalidate() to prompt another draw() as soon as possible
 * by the system.
 */
class GameView extends SurfaceView implements SurfaceHolder.Callback,SensorEventListener {
    class GameThread extends Thread {
        /*
         * State-tracking constants
         */
        public static final int STATE_LOSE = 1;
        public static final int STATE_PAUSE = 2;
        public static final int STATE_READY = 3;
        public static final int STATE_RUNNING = 4;
        public static final int STATE_WIN = 5;

        private float x;
        private float y;

        private float xTouch;
        private float yTouch;

        private static final int SPEED = 100;
        private boolean dRight;
        private boolean dLeft;
        private boolean dUp;
        private boolean dDown;

        private int mCanvasWidth;
        private int mCanvasHeight;

        private long mLastTime;
        private Bitmap mSnowflake;

         /** Message handler used by thread to post stuff back to the GameView */
        private Handler mHandler;

         /** The state of the game. One of READY, RUNNING, PAUSE, LOSE, or WIN */
        private int mMode;
        /** Indicate whether the surface has been created & is ready to draw */
        private boolean mRun = false;
        /** Handle to the surface manager object we interact with */
        private SurfaceHolder mSurfaceHolder;
        
        public GameThread(SurfaceHolder surfaceHolder, Context context,
                Handler handler) {
            // get handles to some important objects
            mSurfaceHolder = surfaceHolder;
            mHandler = handler;
            mContext = context;

            x = 50;
            y = 50;

        	mSnowflake = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ship2_1);
        }

        /**
         * Starts the game, setting parameters for the current difficulty.
         */
        public void doStart() {
            synchronized (mSurfaceHolder) {
            	// Initialize game here!

                x = 50;
                y = 50;

                mLastTime = System.currentTimeMillis() + 100;
                setState(STATE_RUNNING);
            }
        }

        /**
         * Pauses the physics update & animation.
         */
        public void pause() {
            synchronized (mSurfaceHolder) {
                if (mMode == STATE_RUNNING)
                	setState(STATE_PAUSE);
            }
        }

        @Override
        public void run() {
            while (mRun) {
                Canvas c = null;
                try {
                    c = mSurfaceHolder.lockCanvas(null);
                    synchronized (mSurfaceHolder) {
                        if (mMode == STATE_RUNNING)
                        	updateGame();
                        doDraw(c);
                    }
                } finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        /**
         * Used to signal the thread whether it should be running or not.
         * Passing true allows the thread to run; passing false will shut it
         * down if it's already running. Calling start() after this was most
         * recently called with false will result in an immediate shutdown.
         *
         * @param b true to run, false to shut down
         */
        public void setRunning(boolean b) {
            mRun = b;
        }

        /**
         * Sets the game mode. That is, whether we are running, paused, in the
         * failure state, in the victory state, etc.
         *
         * @see #setState(int, CharSequence)
         * @param mode one of the STATE_* constants
         */
        public void setState(int mode) {
            synchronized (mSurfaceHolder) {
                setState(mode, null);
            }
        }

        /**
         * Sets the game mode. That is, whether we are running, paused, in the
         * failure state, in the victory state, etc.
         *
         * @param mode one of the STATE_* constants
         * @param message string to add to screen or null
         */
        public void setState(int mode, CharSequence message) {
            synchronized (mSurfaceHolder) {
                mMode = mode;
            }
        }

        /* Callback invoked when the surface dimensions change. */
        public void setSurfaceSize(int width, int height) {
            // synchronized to make sure these all change atomically
            synchronized (mSurfaceHolder) {
                mCanvasWidth = width;
                mCanvasHeight = height;
            }
        }

        /**
         * Resumes from a pause.
         */
        public void unpause() {
            // Move the real time clock up to now
            synchronized (mSurfaceHolder) {
                mLastTime = System.currentTimeMillis() + 100;
            }
            setState(STATE_RUNNING);
        }

        /**
         * Handles a key-down event.
         *
         * @param keyCode the key that was pressed
         * @param msg the original event object
         * @return true
         */
        boolean doKeyDown(int keyCode, KeyEvent msg) {
        	boolean handled = false;
            synchronized (mSurfaceHolder) {
            	if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            		dRight = true;
            		handled = true;
            	}
            	if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
            		dLeft = true;
            		handled = true;
            	}
            	if (keyCode == KeyEvent.KEYCODE_DPAD_UP){
            		dUp = true;
            		handled = true;
            	}
            	if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
            		dDown = true;
            		handled = true;
            	}
                return handled;
            }
        }

        /**
         * Handles a key-up event.
         *
         * @param keyCode the key that was pressed
         * @param msg the original event object
         * @return true if the key was handled and consumed, or else false
         */
        boolean doKeyUp(int keyCode, KeyEvent msg) {
        	boolean handled = false;
            synchronized (mSurfaceHolder) {
            	if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            		dRight = false;
            		handled = true;
            	}
            	if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
            		dLeft = false;
            		handled = true;
            	}
            	if (keyCode == KeyEvent.KEYCODE_DPAD_UP){
            		dUp = false;
            		handled = true;
            	}
            	if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
            		dDown = false;
            		handled = true;
            	}
            	if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
            		unpause();
            	}
                return handled;
            }
        }

        /**
         * Handles screen touch event.
         *
         * @param msg the original event object
         * @return true if the key was handled and consumed, or else false
         */
        private boolean onTouch(MotionEvent event){
        	xTouch = event.getX();
        	yTouch = event.getY();
        	return true;
        }

        /**
         * Draws the ship, fuel/speed bars, and background to the provided
         * Canvas.
         */
        private void doDraw(Canvas canvas) {
        	// empty canvas
        	canvas.drawARGB(255, 0, 0, 0);

//        	canvas.drawBitmap(mSnowflake, x, y, new Paint());
        	Paint p = new Paint();
        	p.setColor(Color.WHITE);
        	canvas.drawOval(new RectF(x, y, x+20, y+20), p);

        	p.setColor(Color.RED);
        	canvas.drawOval(new RectF(xTouch, yTouch, xTouch+10, yTouch+10), p);
//        	String tag = "Motion Sensor";
//        	SensorManager sm = (SensorManager)mContext.getSystemService(mContext.SENSOR_SERVICE);
//        	sm.
//        	Log.d(tag,"");
        }

        /**
         * Updates the game.
         */
        private void updateGame() {
        	//// <DoNotRemove>
            long now = System.currentTimeMillis();
            // Do nothing if mLastTime is in the future.
            // This allows the game-start to delay the start of the physics
            // by 100ms or whatever.
            if (mLastTime > now)
            	return;
            double elapsed = (now - mLastTime) / 1000.0;
            mLastTime = now;
            //// </DoNotRemove>

            /*
             * Why use mLastTime, now and elapsed?
             * Well, because the frame rate isn't always constant, it could happen your normal frame rate is 25fps
             * then your char will walk at a steady pace, but when your frame rate drops to say 12fps, without elapsed
             * your character will only walk half as fast as at the 25fps frame rate. Elapsed lets you manage the slowdowns
             * and speedups!
             */

            if (dUp)
            	y -= elapsed * SPEED;
            if (dDown)
            	y += elapsed * SPEED;
            if (y < 0)
            	y = 0;
            else if (y >= mCanvasHeight - mSnowflake.getHeight())
            	y = mCanvasHeight - mSnowflake.getHeight();
            if (dLeft)
            	x -= elapsed * SPEED;
            if (dRight)
            	x += elapsed * SPEED;
            if (x < 0)
            	x = 0;
            else if (x >= mCanvasWidth - mSnowflake.getWidth())
            	x = mCanvasWidth - mSnowflake.getWidth();


        }

		public void onSensorChanges(SensorEvent event) {
			//// <DoNotRemove>
            long now = System.currentTimeMillis();
            // Do nothing if mLastTime is in the future.
            // This allows the game-start to delay the start of the physics
            // by 100ms or whatever.
            if (mLastTime > now)
            	return;
            double elapsed = (now - mLastTime) / 1000.0;
            mLastTime = now;
            //// </DoNotRemove>

			synchronized (this) {
		        switch (event.sensor.getType()){
			        case Sensor.TYPE_ORIENTATION:
			        	x+= -0.1  * event.values[1];
			        	y+= 0.1  * event.values[2];
			        	break;
		        }
		    }
		}
    }

    /** Handle to the application context, used to e.g. fetch Drawables. */
    private Context mContext;

    /** The thread that actually draws the animation */
    private GameThread thread;

    /** Reader Device Orientation **/
    private SensorManager mSensorManager;
    
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // register our interest in hearing about changes to our surface
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        // create thread only; it's started in surfaceCreated()
        thread = new GameThread(holder, context, new Handler() {
            @Override
            public void handleMessage(Message m) {
            	// Use for pushing back messages.
            }
        });

        mSensorManager = (SensorManager) mContext.getSystemService(mContext.SENSOR_SERVICE);
        setFocusable(true); // make sure we get key events
        
    }

    /**
     * Fetches the animation thread corresponding to this LunarView.
     *
     * @return the animation thread
     */
    public GameThread getThread() {
        return thread;
    }

    /**
     * Standard override to get key-press events.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        return thread.doKeyDown(keyCode, msg);
    }

    /**
     * Standard override for key-up. We actually care about these, so we can
     * turn off the engine or stop rotating.
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent msg) {
        return thread.doKeyUp(keyCode, msg);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	return thread.onTouch(event);
    }
    /**
     * Standard window-focus override. Notice focus lost so we can pause on
     * focus lost. e.g. user switches to take a call.
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus)
        	thread.pause();
    }

    /* Callback invoked when the surface dimensions change. */
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        thread.setSurfaceSize(width, height);
    }

    /*
     * Callback invoked when the Surface has been created and is ready to be
     * used.
     */
    public void surfaceCreated(SurfaceHolder holder) {
        // start the thread here so that we don't busy-wait in run()
        // waiting for the surface to be created
        thread.setRunning(true);
        thread.start();
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), mSensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), mSensorManager.SENSOR_DELAY_GAME);
    }

    /*
     * Callback invoked when the Surface has been destroyed and must no longer
     * be touched. WARNING: after this method returns, the Surface/Canvas must
     * never be touched again!
     */
    public void surfaceDestroyed(SurfaceHolder holder) {
        // we have to tell thread to shut down & wait for it to finish, or else
        // it might touch the Surface after we return and explode
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
//        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
    }

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent event) {
    	thread.onSensorChanges(event);
	}
}
