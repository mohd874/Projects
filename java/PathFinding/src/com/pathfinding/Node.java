package com.pathfinding;

public class Node {

	Location location;
	int cost;
	int estemateCostToGoal;
	Node parent;
	boolean isObstacle;
	
	public Node() {
		this(0,0,0);
	}
	
	public Node(int cost, int x, int y){
		this.cost = cost;
		location = new Location(x, y);
		isObstacle = false;
		estemateCostToGoal = 0;
	}
	
	public int calculateEstemateCostToGoal(Node goal){
		int H = (Math.abs(this.location.x - goal.location.x)
				+ Math.abs(this.location.y - goal.location.y));
		
		int res = getCostToStart() + H;
		
		return res;
	}
	
	public int getCostToStart(){
		if(parent == null)
			return cost;
		
		int res = cost + parent.getCostToStart();
		
		return res;
	}
	
	public void setEstemateCostToGoal(int estemateCostToGoal){
		this.estemateCostToGoal = estemateCostToGoal;
	}
}
