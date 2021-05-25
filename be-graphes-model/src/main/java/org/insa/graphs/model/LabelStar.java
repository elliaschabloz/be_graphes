package org.insa.graphs.model;

public class LabelStar extends Label {
	
	private double minCost;
	
	public LabelStar(Node currentNode, double minCost) {
		super(currentNode);
		this.minCost = minCost;
	}
		
	public double getMinCost() {
		return this.minCost;
	}
	
	public double getTotalCost() {
		return super.getCost() + this.minCost;
	}
}
