package org.study.model;

import java.util.Random;

/**
 * Class containt population of GA algorithm
 * 
 * @created 7 / 5 / 2015
 * @author baonc
 *
 */
public class Population {
	private int x[];			// variable containt one solution <=> a chromose in GA
	
	/**
	 * Constructor to construct a population
	 * 
	 * @param numberOfTank		: Number of tank in problem
	 * @param numberOfCargos	: Number of cargos in problem
	 */
	public Population(int numberOfTank, int numberOfCargos) {
		Random r = new Random();
		
		this.x = new int[numberOfTank];
		
		for(int i = 0; i < this.x.length; i++) {
			this.x[i] = 1 + r.nextInt(numberOfCargos);
		}
	}
	
	/**
	 * Constructor to construct a population with parameter x
	 * 
	 * @param x	: x will be assignment to x
	 */
	public Population(int x[]) {
		this.x = x;
	}
	
	/**
	 * Setter for x
	 * 
	 * @param x	: this will be assignment for x
	 */
	public void setX(int x[]) {
		this.x = x;
	}
	
	/**
	 * Getter for x
	 * 
	 * @return	: x variable
	 */
	public int[] getX() {
		return this.x;
	}
}