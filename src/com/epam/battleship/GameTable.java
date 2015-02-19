package com.epam.battleship;

import java.util.HashMap;
import java.util.Map;

public class GameTable {

	private Map<CoordinatePair, TableField> fields;
	private int width;
	private int height;

	public GameTable(Map<CoordinatePair, TableField> fields, int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.fields = new HashMap<CoordinatePair, TableField>(fields);
	}
	
	public GameTable(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.fields = new HashMap<CoordinatePair, TableField>();
	}

	public TableField getFieldAtCoordinates(CoordinatePair coordinates) {
		return fields.get(coordinates);
	}

	public void setFieldTypeAtCoordinates(CoordinatePair coordinates,
			FieldType fieldType) {
		fields.get(coordinates).setFieldType(fieldType);
	}
	
	public FieldType getFieldTypeAtCoordinates(CoordinatePair coordinates) {
		if(isCoordinateOutOfBounds(coordinates)){
			throw new IllegalArgumentException("Given coordinates are out of bounds.");
		}
		return fields.get(coordinates).getFieldType();
	}

	public boolean isShotAHit(CoordinatePair coordinates) {
		if(isCoordinateOutOfBounds(coordinates)){
			throw new IllegalArgumentException("Given coordinates are out of bounds.");
		}
		return fields.get(coordinates).isTypeOf(FieldType.SHIP);
	}
	
	private boolean isCoordinateOutOfBounds(CoordinatePair coordinates){
		int x = coordinates.getX();
		int y = coordinates.getY();
		return x > width || y > height;
	}
}
