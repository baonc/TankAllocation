package org.study.model;

import java.util.ArrayList;

/**
 * Class contain information of tank of the vessel
 * 
 * @created 17 / 4 / 2015
 * @author baonc
 *
 */
public class Tanks {
	private int id;										// id of the tank	
	private int capa;									// capacity of the tank
	private int x;										// x dimension of the tank
	private int y;										// y dimension of the tank
	private int w;										// w dimension of the tank
	private int h;										// h dimension of the tank
	private ArrayList<Integer> imposibleCargos;			// id of the cargo cannot place inside the tank
	private ArrayList<Integer> neighbour;				// id of the tank which neighbour of the tank
	
	/**
	 * Constructor to create a Tank with: </br>
	 * 
	 * @param id					: id of tank
	 * @param capa					: capacity of tank
	 * @param x						: location of tank on the vessel
	 * @param y						: ''
	 * @param w
	 * @param h
	 * @param impossibleCargos		: id of the cargo cannot place inside of tank
	 * @param neighbour				: id of the tank which neighbour of the tank
	 */
	public Tanks(int id, int capa, int x, int y, int w, int h, ArrayList<Integer> impossibleCargos, ArrayList<Integer> neighbour) {
		this.id = id;
		this.capa = capa;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.imposibleCargos = impossibleCargos;
		this.neighbour = neighbour;
	}
	
	/**
	 * Setter of id
	 * 
	 * @param id	: id will be set for id
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Getter of the id
	 * 
	 * @return	: id of the tank
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Setter of capa
	 * 
	 * @param capa	: capa will be set for capa 
	 */
	public void setCapa(int capa) {
		this.capa = capa;
	}
	
	/**
	 * Getter of capa
	 * @return	: capa of the tank
	 */
	public int getCapa() {
		return this.capa;
	}
	
	/**
	 * Setter of x dimension of tank
	 * 
	 * @param x	: x dimension will be set for x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Getter of x dimension
	 * 
	 * @return	: x dimension
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Setter of y dimension
	 * 
	 * @param y	: y will be set for y dimension
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Getter of y dimension
	 * 
	 * @return	: y dimension
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Setter of w dimension
	 * 
	 * @param w	: w dimension will be set for w
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * Getter of w dimension
	 * 
	 * @return	: w dimension
	 */
	public int getW() {
		return this.w;
	}
	
	/**
	 * Setter of h dimension
	 * 
	 * @param h	: h dimension will be set for h
	 */
	public void setH(int h) {
		this.h = h;
	}
	
	/**
	 * Getter of h dimension
	 * 
	 * @return	: h diemsion
	 */
	public int getH() {
		return this.h;
	}
	
	/**
	 * Setter of imposibleCargos
	 * 
	 * @param imposibleCargos	: List of imposible cargos will be setted
	 */
	public void setImposibleCargos(ArrayList<Integer> imposibleCargos) {
		this.imposibleCargos = imposibleCargos;
	}
	
	/**
	 * Getter of imposibleCargos
	 * 
	 * @return	: imposibleCargos
	 */
	public ArrayList<Integer> getImposibleCargos() {
		return this.imposibleCargos;
	}
	
	/**
	 * Setter of neighbour
	 * 
	 * @param neighbour	: List of neighbour of tank will be setted
	 */
	public void setNeighbour(ArrayList<Integer> neighbour) {
		this.neighbour = neighbour;
	}

	/**
	 * Getter of neighbour
	 * 
	 * @return	: List of neighbour of this tank
	 */
	public ArrayList<Integer> getNeighbour() {
		return this.neighbour;
	}
}