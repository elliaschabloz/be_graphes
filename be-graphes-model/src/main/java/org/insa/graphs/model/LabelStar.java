package org.insa.graphs.model;

public class LabelStar extends Label {
	
	double minCost;
	
	public LabelStar(Node currentNode,Node dest, boolean marque, double cost, int pere) {
		super(currentNode, marque, cost, pere);
		this.minCost = currentNode.getPoint().distanceTo(dest.getPoint());
	}
		
	public double getMinCost() {
		return this.minCost;
	}
	
	public double getTotalCost() {
		return this.cost + this.minCost;
	}
}
