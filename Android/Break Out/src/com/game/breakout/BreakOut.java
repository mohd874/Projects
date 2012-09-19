package com.game.breakout;

import com.game.breakout.object.Ball;
import com.game.breakout.object.Bar;
import com.game.breakout.object.Block;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class BreakOut extends ArcadeGame{

	private Context mContext;
	BreakOutGame bog;
	private float tx;
	private float ty;

	Bar bar;
	Ball ball;
	Block[] blocks;

	float height;
	float width;

	boolean gameOver;

	Paint textPaint = new Paint();

	// Refresh rate (ms)
	private static final long UPDATE_DELAY = 40;

	public BreakOut(Context context) {
		super(context);
		mContext = context;
		super.setUpdatePeriod(UPDATE_DELAY);

		height = getHeight();
		width = getWidth();

		init(this);

		gameOver = true;
	}

	public BreakOut(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		super.setUpdatePeriod(UPDATE_DELAY);

		height = getHeight();
		width = getWidth();

		init(this);

		gameOver = true;
	}

	private void init(BreakOut g) {
		//init images
		Bitmap barImage =g.getImage(R.drawable.bar_img);
		Bitmap ballImage =g.getImage(R.drawable.ball_img);

		//init GameObjects
		bar = new Bar((width/2-barImage.getWidth()/2),(height-barImage.getHeight()),barImage,g);
		initBlocks();
//		ball = new Ball(bar.getX()-bar.getImage().getWidth(),bar.getY()-ballImage.getHeight(),ballImage,g, bar, blocks);
		ball = new Ball(this.width/2,this.height/2,ballImage,g, bar, blocks);
	}

	private void initBlocks() {
		Bitmap blockImage = getImage(R.drawable.block_img);
		int numBlocks1 = getWidth()/blockImage.getWidth();
		int numBlocks2 = (getWidth()-(blockImage.getWidth()/3))/blockImage.getWidth();
		int numBlocks = 2*numBlocks1 + numBlocks2;

		blocks = new Block[numBlocks];

		Log.d("", "Screen Width: "+getWidth()+" Block Image Width: "+blockImage.getWidth());
		int row1=0;
		int row2=0;
		int row3=0;
		for(int i=0;i<numBlocks;i++){
			if(i < numBlocks1)
				blocks[i] = new Block((blockImage.getWidth()*row1++), blockImage.getHeight(), blockImage, this);
			else if(i < (numBlocks1+numBlocks2))
				blocks[i] = new Block(blockImage.getWidth()/2+(blockImage.getWidth()*row2++), blockImage.getHeight()*2, blockImage, this);
			else
				blocks[i] = new Block((blockImage.getWidth()*row3++), blockImage.getHeight()*3, blockImage, this);
		}
	}
	@Override
	protected void initialize() {
		bog = new BreakOutGame(this);
		tx = getWidth()/2;
		ty = getHeight()-20;
	}

	@Override
	protected boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected long getScore() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected void updatePhysics() {
		bar.update();
		if(gameOver){
			return;
		}
		ball.update();
		for(Block b : blocks){
			b.update();
		}
	}

	@Override
	protected void onDraw(Canvas c) {
		super.dispatchDraw(c);
//		bog.update();
//		bog.draw(c);
		bar.draw(c);
		if(gameOver){
			textPaint.setColor(Color.WHITE);
			String s = "Game Over";
			c.drawText(s, (c.getWidth()/2 - s.length() * textPaint.getTextSize()/2),
						   c.getHeight()/2-20, textPaint);
			return;
		}
		ball.draw(c);
		for(Block b : blocks){
			b.draw(c);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		tx = event.getX();
		ty = event.getY();

		if(event.getAction() == MotionEvent.ACTION_UP){
			this.startGame();
		}
		return true;
	}

	public float getTouchX() {
		return tx;
	}

	public float getTouchY() {
		return ty;
	}

	public void startGame(){
		if(!gameOver) return;
		gameOver = false;
		init(this);
	}

	public void gameEnd(){
		if(gameOver)return;
		gameOver = true;
	}

}
