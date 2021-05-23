package org.insa.graphs.model;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;

/**
 * <p>
 * Main label class.
 * </p>
 * 
 * <p>
 * This class is about labels.
 * </p>
 *
 */

public class Label implements Comparable<Label>{
	
	//private int sommet_courant;
	Node currentNode;
	boolean marque;
	double cost;
	int pere;
	
	public Label(Node currentNode, boolean marque, double cost, int pere) {
		this.currentNode = currentNode;
		this.marque = marque;
		this.cost = cost;
		this.pere = pere;
	}
	
	/////////////////////////////
	////////// SETTERS //////////
	/////////////////////////////
	
	public void setNode(Node node) {
		this.currentNode = node;
	}
	
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void setPere(int pere) {
		this.pere = pere;
	}
		
	/////////////////////////////
	////////// GETTERS //////////
	/////////////////////////////
	
	public Node getNode() {
		return this.currentNode;
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public double getTotalCost() {
		return this.cost;
	}
	
	public int getFather() {
		return this.pere;
	}
	
	/////////////////////////////
	
	public int compareTo(Label L2) {
		return Double.compare(this.getTotalCost(), L2.getTotalCost());
	}
	
}