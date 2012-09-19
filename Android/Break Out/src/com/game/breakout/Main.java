package com.game.breakout;

import com.game.breakout.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class Main extends Activity {
	private View view;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater factory = LayoutInflater.from(this);

        // Set game layout
        view = factory.inflate(R.layout.main, null);
        setContentView(view);


        // Enable view key events
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
    }

    @Override
    protected void onStop() {
    	super.onStop();
    	((ArcadeGame)view).halt();
    }

    @Override
    protected void onPause() {
    	super.onPause();
    	onStop();
    }

    @Override
    protected void onRestart() {
    	super.onRestart();
    	((ArcadeGame)view).resume();
    }
}