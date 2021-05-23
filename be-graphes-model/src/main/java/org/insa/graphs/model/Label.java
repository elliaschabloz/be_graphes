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
	
	private int sommet_courant;
	private boolean marque;
	private double cost;
	private int pere;
	private Arc arc_pere;
	
	public Label(int sommet_courant, boolean marque, double cost, int pere, Arc arc_pere) {
		this.sommet_courant = sommet_courant;
		this.marque = marque;
		this.cost = cost;
		this.pere = pere;
		this.arc_pere = arc_pere;
	}
	
	public Label(int sommet_courant, boolean marque, double cost, int pere) {
		this.sommet_courant = sommet_courant;
		this.marque = marque;
		this.cost = cost;
		this.pere = pere;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public Arc getArc() {
		return this.arc_pere;
	}
	
	public int getSommet() {
		return this.sommet_courant;
	}
	
	public int getFather() {
		return this.pere;
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	public void setSommet(int sommet) {
		this.sommet_courant = sommet;
	}
	
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	
	public void setArc(Arc pere) {
		this.arc_pere = pere;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void setPere(int pere) {
		this.pere = pere;
	}
	
	public int compareTo(Label L2) {
		return Double.compare(this.getCost(), L2.getCost());
	}
	
}