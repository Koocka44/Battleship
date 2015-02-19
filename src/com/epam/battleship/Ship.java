package com.epam.battleship;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Ship {
	
	private ShipType shipType;
	private CoordinatePair baseCoordinates;
	private boolean isOnTable = false;
	private Map<CoordinatePair, TableField> realFields;
	
	public Ship(ShipType shipType){
		this.realFields = new HashMap<CoordinatePair, TableField>();
		this.shipType = shipType;
	}
	
	public void putShipDown(CoordinatePair baseCoordinates) {
		setBaseCoordinates(baseCoordinates);
		setIsOnTable(true);
	}
	
	public boolean isOnTable(){
		return this.isOnTable;
	}
	
	public void setIsOnTable(boolean isOnTable){
		this.isOnTable = isOnTable;
	}
	
	public CoordinatePair getBaseCoordinates(){
		return this.baseCoordinates;
	}
	
	public void setBaseCoordinates(CoordinatePair baseCoordinates){
		this.baseCoordinates = baseCoordinates;
	}
	
	public Collection<TableField> getFieldsFromShipType(){
		return shipType.getShipTypeFields();
	}
	
	public Collection<TableField> getRealFields(){
		return this.realFields.values();
	}

	public void setRealFields(Map<CoordinatePair, TableField> realFields){
		this.realFields = new HashMap<CoordinatePair, TableField>(realFields);
	}
	
	public boolean destroyPieceAtCoordinateAndReturnIfDead(CoordinatePair coordinates){
		this.realFields.get(coordinates).setFieldType(FieldType.DESTROYED);
		return this.isDead();
	}
	
	private boolean isDead(){
		for(TableField field : realFields.values()){
			if(field.isTypeOf(FieldType.SHIP)){
				return false;
			}
		}
		return true;
	}
	
	public ShipTypeBoundaries getBoundaries(){
		return shipType.getBoundaries();
	}

}
