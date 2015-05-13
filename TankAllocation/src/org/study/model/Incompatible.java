package org.study.model;

/**
 * Class contain incomplatible of the two cargo
 * 
 * @created 17 / 04 / 2015
 * @author baonc
 *
 */
public class Incompatible {
	private int cargoId1;		// id of cargo 1
	private int cargoId2;		// id of cargo 2
	
	public Incompatible(int cargoId1, int cargoId2) {
		this.cargoId1 = cargoId1;
		this.cargoId2 = cargoId2;
	}
	
	public void setCargoId1(int cargoId1) {
		this.cargoId1 = cargoId1;
	}
	
	public int getCargoId1() {
		return this.cargoId1;
	}
	
	public void setCargoId2(int cargoId2) {
		this.cargoId2 = cargoId2;
	}
	
	public int getCargoId2() {
		return this.cargoId2;
	}
}