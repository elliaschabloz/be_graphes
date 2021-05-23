package org.insa.graphs.model;

public class LabelStar extends Label {
	
	double minCost;
	Node destination;
	
	public LabelStar(Node currentNode,Node dest, boolean marque, double cost, double minCost, int pere) {
		super(currentNode, marque, cost, pere);
		this.destination = dest;
		this.minCost = currentNode.getPoint().distanceTo(destination.getPoint());
	}
	
	public double getTotalCost() {
		return this.getCost() + this.minCost;
	}
	
	public void setMinCost(double minCost) {
		this.minCost = minCost;
	}
}
