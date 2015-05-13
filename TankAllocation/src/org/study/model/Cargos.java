package org.study.model;

/**
 * Class contain information of each cargo of a tank
 * 
 * @created 17 / 4 / 2015
 * @author baonc
 *
 */
public class Cargos {
	private int id;			// id of the cargo
	private String name;	// name of the cargo
	private int volume;		// volume of the cargo
	
	/**
	 * Constructor create new cargo instance with: </br>
	 * 
	 * @param id		: id of the cargo
	 * @param name		: name of the cargo
	 * @param volume	: volume of the car go
	 */
	public Cargos(int id, String name, int volume) {
		this.id = id;
		this.name = name;
		this.volume = volume;
	}
	
	/**
	 * Setter id for id of the cargo
	 * 
	 * @param id	: id will set for id instance
	 */
	public void setID(int id) {
		this.id = id;
	}
	
	/**
	 * Getter id of currence cargo
	 * 
	 * @return	: currence id of the cargo
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Setter name of the cargo
	 * 
	 * @param name	: name will be seted for name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter name of the currence cargo
	 * 
	 * @return	: name of the currence cargo
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter volume of the currence cargo
	 * 
	 * @param volume	: volume will be set for currence cargo
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	/**
	 * Getter volume of the currence cargo
	 * 
	 * @return	: Volume of the currence cargo
	 */
	public int getVolume() {
		return this.volume;
	}
}