package org.insa.graphs.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	private Arc pere;
	
	
	public Label(int sommet_courant, boolean marque, double cost, Arc pere) {
		this.sommet_courant = sommet_courant;
		this.marque = marque;
		this.cost = cost;
		this.pere = pere;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public int getSommet() {
		return this.sommet_courant;
	}
	
	public void setSommet(int sommet) {
		this.sommet_courant = sommet;
	}
	
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void setPere(Arc pere) {
		this.pere = pere;
	}
	
	public int compareTo(Label L2) {
		return Double.compare(this.getCost(), L2.getCost());
	}
	
}