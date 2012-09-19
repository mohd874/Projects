
public class BreakOut {
	Bar bar;
	Ball ball;
	Block[] blocks;

	float height;
	float width;

	BreakOut(){
		barImage = getImage(R.drawable.barImage);
		bar = new Bar((width/2-barImage.getWidth()/2),(height-barImage.getHeight()),barImage);
		
	}

	public void update(){
		
	}

	public void draw(){
		
	}

}

abstract class GameObject{
	float x;
	float y;
	Bitmap image;

	GameObject(int x, int y, Bitmap image){
		this.x = x;
		this.y = y;
		this.image = image; 
	}

	public abstract void update();

	public abstract void draw();
}

class Bar extends GameObject{

	Bar(int x, int y, Bitmap image){
		super(x,y,image);
	}

	public void update(){
		
	}

	public void draw(){
		
	}
}

class Ball extends GameObject{

	Ball(int x, int y, Bitmap image){
		super(x,y,image);
	}

	public void update(){
		
	}

	public void draw(){
		
	}
}

class Block extends GameObject{

	Block(int x, int y, Bitmap image){
		super(x,y,image);
	}

	public void update(){
		
	}

	public void draw(){
		
	}
}