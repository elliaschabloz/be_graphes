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

public final class Label {
	
	private int sommet_courant;
	private boolean marque;
	private int cost;
	private Arc pere;
	
	public Label(int sommet_courant, boolean marque, int cost, Arc pere) {
		this.sommet_courant = sommet_courant;
		this.marque = marque;
		this.cost = cost;
		this.pere = pere;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public void setSommet(int sommet) {
		this.sommet_courant = sommet;
	}
	
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setPere(Arc pere) {
		this.pere = pere;
	}
	
	
}