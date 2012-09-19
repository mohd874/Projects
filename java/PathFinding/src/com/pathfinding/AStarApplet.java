package com.pathfinding;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class AStarApplet extends Applet implements MouseListener,
		MouseMotionListener {

	Node[][] map = new Node[10][10];
	List<Node> openList;
	List<Node> closedList;
	int openListIndex;
	int closedListIndex;
	Node start;
	Node goal;

	private final int MOUSE_LEFT_BUTTON = 1;
	private final int MOUSE_MIDDLE_BUTTON = 2;
	private final int MOUSE_RIGHT_BUTTON = 3;
	private boolean pathComplete;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		setSize(200, 200);

		for (int i = 0; i < map.length; i++) {
			Node[] n = map[i];
			for (int j = 0; j < n.length; j++) {
				n[j] = new Node(1, i, j);
			}
		}

		start = map[5][0];
		goal = map[9][9];

		map[5][2].isObstacle = true;

		computePath();

		this.addMouseListener(this);
	}

	private void computePath() {
		if (null == openList)
			openList = new ArrayList<Node>();
		else
			openList.removeAll(openList);
		if (null == closedList)
			closedList = new ArrayList<Node>();
		else
			closedList.removeAll(closedList);
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].parent=null;
			}
		}
		openList.add(start);
		pathComplete = false;
		
		System.out.println("Computing the path");
		System.out.print("Start: ");
		printLocation(start);
		System.out.print("Goal: ");
		printLocation(goal);
		
		Node current = null;
		while(!openList.isEmpty()){
			current = getLowestCostNodeFromOpenlist();
			System.out.print("Current: ");
			printLocation(current);
			if(compareNodesLocation(current, goal)){
				// path complete
				pathComplete = true;
				openList.removeAll(openList);
				break;
			}else{
				closedList.add(current);
				List<Node> adjacent = getNaighbors(current);
				System.out.println("There are "+adjacent.size()+" adjacents");
				for(Node n : adjacent){
					if(!openList.contains(n) && !closedList.contains(n) && !n.isObstacle){
						openList.add(n);
						n.parent = current;
						n.setEstemateCostToGoal(n.calculateEstemateCostToGoal(goal));
					}
					System.out.print("Adjacent #"+adjacent.indexOf(n)+" ");
					printLocation(n);
				}
				openList.remove(current);
			}
		}
		
	}

	private void printLocation(Node n) {
		System.out.println(n.location.x+","+n.location.y+" cost: "+n.estemateCostToGoal);
	}

	private boolean compareNodesLocation(Node n1, Node n2) {
		if(n1.location.x == n2.location.x && n1.location.y == n2.location.y)
			return true;
		else
			return false;
	}

	private Node getLowestCostNodeFromOpenlist() {
		Node res = null;
		for(Node n : openList){
			if(res == null)
				res = n;
			else{
				n.setEstemateCostToGoal(n.calculateEstemateCostToGoal(goal));
				res.setEstemateCostToGoal(res.calculateEstemateCostToGoal(goal));
				if(n.estemateCostToGoal <= res.estemateCostToGoal && !closedList.contains(n))
					res = n;
			}
		}
		return res;
	}

	private List<Node> getNaighbors(Node n) {
		List<Node> naighbors = new ArrayList<Node>();

		if (n.location.y > 0 && !map[n.location.x][n.location.y - 1].isObstacle 
				&& !openList.contains(map[n.location.x][n.location.y - 1]) && !closedList.contains(map[n.location.x][n.location.y - 1])) { // check upper naighbor
			naighbors.add(map[n.location.x][n.location.y - 1]);
		}
		if (n.location.y < map[n.location.x].length - 1	&& !map[n.location.x][n.location.y + 1].isObstacle 
				&& !openList.contains(map[n.location.x][n.location.y + 1]) && !closedList.contains(map[n.location.x][n.location.y + 1])) { // check lower naighbor
			naighbors.add(map[n.location.x][n.location.y + 1]);
		}
		if (n.location.x > 0 && !map[n.location.x - 1][n.location.y].isObstacle 
				&& !openList.contains(map[n.location.x-1][n.location.y]) && !closedList.contains(map[n.location.x-1][n.location.y])) { // check left naighbor
			naighbors.add(map[n.location.x - 1][n.location.y]);
		}
		if (n.location.x < map.length - 1 && !map[n.location.x + 1][n.location.y].isObstacle 
				&& !openList.contains(map[n.location.x+1][n.location.y]) && !closedList.contains(map[n.location.x+1][n.location.y])) { // check right naighbor
			naighbors.add(map[n.location.x + 1][n.location.y]);
		}

		return naighbors;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		paint(getGraphics());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		drawAll(g);
	}

	private void drawAll(Graphics g) {
		g.setColor(Color.WHITE);
		drawMap(g);
	}

	private void drawMap(Graphics g) {
		for (int i = 0; i < map.length; i++) {
			Node[] n = map[i];
			for (int j = 0; j < n.length; j++) {
				if (map[i][j].isObstacle)
					g.fillRect(i * 20, j * 20, 20, 20);
				else
					g.drawRect(i * 20, j * 20, 20, 20);
			}
		}

		
//		for (Node n : closedList) {
//			if(n != null)
//			g.fillRect(n.location.x * 20, n.location.y * 20, 20, 20);
//		}
		
		
		g.setColor(Color.LIGHT_GRAY);
		if(pathComplete)
			drawPath(g, goal.parent);
		
		g.setColor(Color.GREEN);
		g.fillRect(start.location.x * 20, start.location.y * 20, 20, 20);
		g.setColor(Color.RED);
		g.fillRect(goal.location.x * 20, goal.location.y * 20, 20, 20);
		
	}

	private void drawPath(Graphics g, Node n) {
		drawNode(g, n);
		if(n.parent != null)
			drawPath(g, n.parent);
	}

	
	private void drawNode(Graphics g, Node n) {
		g.fillRect(n.location.x * 20, n.location.y * 20, 20, 20);
	}

	private boolean isLocationInsideNode(Node n, int x, int y) {
		if ((x >= n.location.x * map.length * 2 && x < n.location.x
				* map.length * 2 + 20)
				&& (y >= n.location.y * map.length * 2 && y < n.location.y
						* map.length * 2 + 20)) {
			return true;
		}
		return false;
	}

	private Node getClickedNode(int x, int y) {
		Node result = null;
		for (Node[] list : map) {
			for (Node n : list) {
				if (isLocationInsideNode(n, x, y)) {
					result = n;
					break;
				}
			}
			if (result != null)
				break;
		}

		return result;
	}

	public void mouseClicked(MouseEvent evnt) {
		int x = evnt.getX();
		int y = evnt.getY();

		int clickedButton = evnt.getButton();

		Node n = getClickedNode(x, y);
		if (n == null) {
			System.out.println("Cannot Find Clicked Node!");
			return;
		} else {

			if (clickedButton == MOUSE_LEFT_BUTTON) {
				// Set Goal
				goal = n;
			} else if (clickedButton == MOUSE_RIGHT_BUTTON) {
				// Set Start
				start = n;
			} else if (clickedButton == MOUSE_MIDDLE_BUTTON) { 
				// Add/Remove obstacle
				n.isObstacle = !n.isObstacle;
			}

			computePath();
			paint(getGraphics());
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent evnt) {

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
