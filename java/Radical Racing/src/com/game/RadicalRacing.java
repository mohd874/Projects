package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RadicalRacing extends JFrame{

	final int WIDTH = 900, HEIGHT = 650;
	public Rectangle[] track;
	public Rectangle[] obstecales;
	private Rectangle finishLine;
	private Rectangle[] startLines;
	public Car[] players;
	public int laps;
	public String winner;
	public boolean weHaveWinner;
	public boolean running;

	public RadicalRacing() {
		super("Radical Racing");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		//init track
		track = new Rectangle[5];
		track[0] = new Rectangle(0,0,WIDTH/9,HEIGHT);//left
		track[1] = new Rectangle((WIDTH/9)*8,0,WIDTH/9,HEIGHT);//right
		track[2] = new Rectangle(0,0,WIDTH,HEIGHT/9);//top
		track[3] = new Rectangle(0,(HEIGHT/9)*8,WIDTH,HEIGHT/9);//bottom
		track[4] = new Rectangle((int)((WIDTH/9)*2.5),(int)((HEIGHT/9)*2.5),(int)((WIDTH/9)*5),(HEIGHT/9)*4);//center

		//init obstecales
		obstecales = new Rectangle[5];
		obstecales[0] = new Rectangle(WIDTH/2,(int)((HEIGHT/9)*7.5),WIDTH/10,HEIGHT/9);
		obstecales[1] = new Rectangle(WIDTH/3,(int)((HEIGHT/9)*5),WIDTH/10,HEIGHT/4);
		obstecales[2] = new Rectangle(2*(WIDTH/3),(int)((HEIGHT/9)*5),WIDTH/10,HEIGHT/4);
		obstecales[3] = new Rectangle(WIDTH/3,(int)((HEIGHT/9)*9),WIDTH/30,HEIGHT/9);
		obstecales[4] = new Rectangle(WIDTH/2,(int)((HEIGHT/9)*2),WIDTH/30,HEIGHT/4);

		//init finishLine
		finishLine = new Rectangle(WIDTH/9, (HEIGHT/2)-HEIGHT/9, (int)((WIDTH/9)*1.5), WIDTH/70);

		//init startLines
		startLines = new Rectangle[2];
		startLines[0] = new Rectangle(WIDTH/9, HEIGHT/2, (int)((WIDTH/9)*1.5)/2, HEIGHT/140);
		startLines[1] = new Rectangle(WIDTH/9+((int)((WIDTH/9)*1.5)/2),(HEIGHT/2)+(HEIGHT/10),(int)((WIDTH/9)*1.5)/2,HEIGHT/140);

		laps = 3;
		running = true;
		weHaveWinner = false;

		//get the cars images urls. if it failed, the game should exit.
		URL car1UrlUp,car1UrlDown,car1UrlLeft,car1UrlRight, car2UrlUp,car2UrlDown,car2UrlLeft,car2UrlRight;
		try{
			car1UrlUp = this.getClass().getResource("car1up.png");
			car1UrlDown = this.getClass().getResource("car1down.png");
			car1UrlLeft = this.getClass().getResource("car1left.png");
			car1UrlRight = this.getClass().getResource("car1right.png");
			car2UrlUp = this.getClass().getResource("car2up.png");
			car2UrlDown = this.getClass().getResource("car2down.png");
			car2UrlLeft = this.getClass().getResource("car2left.png");
			car2UrlRight = this.getClass().getResource("car2right.png");
//			Rectangle p1Rec = new Rectangle(WIDTH/9,HEIGHT/2,WIDTH/30,HEIGHT/30);
			Rectangle p1Rec = new Rectangle(WIDTH/9,HEIGHT/2,40,40);
			Image[] p1Icons = new Image[4];
			p1Icons[0] = Toolkit.getDefaultToolkit().getImage(car1UrlUp);
			p1Icons[1] = Toolkit.getDefaultToolkit().getImage(car1UrlLeft);
			p1Icons[2] = Toolkit.getDefaultToolkit().getImage(car1UrlDown);
			p1Icons[3] = Toolkit.getDefaultToolkit().getImage(car1UrlRight);

//			Rectangle p2Rec = new Rectangle((WIDTH/9)+((int)((WIDTH/9)*1.5)/2),HEIGHT/2+(HEIGHT/10),WIDTH/30,HEIGHT/30);
			Rectangle p2Rec = new Rectangle((WIDTH/9)+((int)((WIDTH/9)*1.5)/2),HEIGHT/2+(HEIGHT/10),40,40);
			Image[] p2Icons = new Image[4];
			p2Icons[0] = Toolkit.getDefaultToolkit().getImage(car2UrlUp);
			p2Icons[1] = Toolkit.getDefaultToolkit().getImage(car2UrlLeft);
			p2Icons[2] = Toolkit.getDefaultToolkit().getImage(car2UrlDown);
			p2Icons[3] = Toolkit.getDefaultToolkit().getImage(car2UrlRight);

			players = new Car[2];
			players[0] = new Car("Red Car", p1Icons, p1Rec, Color.BLUE, Car.WASD, this);
			players[1] = new Car("Yellow Car", p2Icons, p2Rec, Color.RED, Car.IJKL, this);

		}catch(Exception e){
			e.printStackTrace();
		}

		//display a welcome dialog (JOptionPane) that includes the rules
		JOptionPane.showMessageDialog(null,"WELCOME TO RADICAL RACING!\n\n"
				+"Game: 2 player racing\n"
				+"Goal: Complete 3 full laps before your opponent!\n"
				+"Controls:\n"+"Player1:\n"+"(BLUE CAR) WASD directional, speed is automatic\n"
				+" Player 2:\n"+" (RED CAR) IJKL directional, speed is automatic\n"
				+"Also, be sure to avoid the green grass. It�s slick\n"
				+"and might make you spin out!\n\n"
				+"Click OK to start");
		players[0].start();
		players[1].start();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		//draw the background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		//draw the track
		g.setColor(Color.GREEN);
		for(Rectangle r : track){
			g.fillRect(r.x, r.y, r.width, r.height);
		}

		g.setColor(Color.CYAN);
		for(Rectangle r : obstecales){
			g.fillRect(r.x, r.y, r.width, r.height);
		}

		//draw finish line
		g.setColor(Color.YELLOW);
		g.fillRect(finishLine.x, finishLine.y, finishLine.width, finishLine.height);

		//draw start Lines
		g.setColor(Color.WHITE);
		for(Rectangle r : startLines){
			g.fillRect(r.x, r.y, r.width, r.height);
		}

		//draw cars
		for(Car c : players){
			g.setColor(c.color);
			g.drawImage(c.icons[c.dir], c.rec.x, c.rec.y, this);
		}

	}

	public void annouceWinner(){
		weHaveWinner = true;

		JOptionPane.showMessageDialog(null, "The winner is "+winner+"\n" +
				"Proess OK tp play again");
		restart();
	}

	public void restart(){
		winner = "";

		players[0].rec.setLocation(WIDTH/9,HEIGHT/2);
		players[0].dir = Car.UP;
		players[0].speed = 0;
		players[0].currentLap = 0;

		players[1].rec.setLocation((WIDTH/9)+((int)((WIDTH/9)*1.5)/2),HEIGHT/2+(HEIGHT/10));
		players[1].dir = Car.UP;
		players[1].speed = 0;
		players[1].currentLap = 0;

		weHaveWinner = false;
	}

	private class Car extends Thread implements KeyListener{
		private double speed;
		private double maxSpeed;
		private double acceleration;
		public Image[] icons;
		public Rectangle rec;
		public Color color;
		private int dir;
		private RadicalRacing race;
		private int controlType;
		public String name;

		public static final int WASD = 0;
		public static final int IJKL = 1;
		public static final int AROW = 2;

		private static final int UP    = 0;
		private static final int LEFT  = 1;
		private static final int DOWN  = 2;
		private static final int RIGHT = 3;

		public int currentLap;

		public Car(String n, Image[] i, Rectangle s, Color c,int ctl, RadicalRacing r) {
			speed = 0.5;
			maxSpeed = 5;
			acceleration = 0.2;
			icons = i;
			color = c;
			dir = UP;
			race = r;
			rec = s;
			controlType = ctl;
			currentLap = 0;
			name = n;
			addKeyListener(this);
		}
		@Override
		public void run() {
			while(true){
				try{
					repaint();

					if(!weHaveWinner){
						checkCollision();

						if(speed < maxSpeed)speed += acceleration;

						switch(dir){
						case UP:
							rec.y -= speed;break;
						case LEFT:
							rec.x -= speed;break;
						case DOWN:
							rec.y += speed;break;
						case RIGHT:
							rec.x += speed;break;
						}
					}
					Thread.sleep(75);
				}catch(Exception e){
					break;
				}
			}
		}
		private void checkCollision() {
			for(Rectangle r : race.obstecales){
				if(rec.intersects(r))speed = -4;
			}
			for(Rectangle r : race.track){
				if(rec.intersects(r))speed = -2.5;
			}
			for(Car c : race.players){
				if(rec.intersects(c.rec) && !c.equals(this))speed = -2.5;
			}
			//if(rec.intersects(race.finishLine) && dir == UP)currentLap++;

			if(currentLap == race.laps){
				race.winner = name;
				race.weHaveWinner = true;
				annouceWinner();
			}
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(speed <= 0)return;

			switch(controlType){
			case WASD:
				if(e.getKeyCode() == KeyEvent.VK_W)
					dir = UP;
				else if(e.getKeyCode() == KeyEvent.VK_A)
					dir = LEFT;
				else if(e.getKeyCode() == KeyEvent.VK_S)
					dir = DOWN;
				else if(e.getKeyCode() == KeyEvent.VK_D)
					dir = RIGHT;
				break;
			case IJKL:
				if(e.getKeyCode() == KeyEvent.VK_I)
					dir = UP;
				else if(e.getKeyCode() == KeyEvent.VK_J)
					dir = LEFT;
				else if(e.getKeyCode() == KeyEvent.VK_K)
					dir = DOWN;
				else if(e.getKeyCode() == KeyEvent.VK_L)
					dir = RIGHT;
				break;
			case AROW:
				if(e.getKeyCode() == KeyEvent.VK_UP)
					dir = UP;
				else if(e.getKeyCode() == KeyEvent.VK_LEFT)
					dir = LEFT;
				else if(e.getKeyCode() == KeyEvent.VK_DOWN)
					dir = DOWN;
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					dir = RIGHT;
				break;
			}

		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void keyTyped(KeyEvent e) {

		}
	}
}

