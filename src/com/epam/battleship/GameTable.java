package com.epam.battleship;

import java.util.HashMap;
import java.util.Map;

public class GameTable {

	private Map<CoordinatePair, TableField> fields;
	private final int width;
	private final int height;

	public GameTable(Map<CoordinatePair, TableField> fields, int width,
			int height) {
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public TableField getFieldAtCoordinates(CoordinatePair coordinates) {
		return fields.get(coordinates);
	}

	public void setFieldTypeAtCoordinates(CoordinatePair coordinates,
			FieldType fieldType) {
		TableField field = new TableField(coordinates, fieldType);
		fields.put(coordinates,field);
	}

	public FieldType getFieldTypeAtCoordinates(CoordinatePair coordinates) {
		if (isCoordinateOutOfBounds(coordinates)) {
			throw new IllegalArgumentException(
					"Given coordinates are out of bounds.");
		}
		return fields.get(coordinates).getFieldType();
	}

	public boolean isShotAHit(CoordinatePair coordinates) {
		if (isCoordinateOutOfBounds(coordinates)) {
			throw new IllegalArgumentException(
					"Given coordinates are out of bounds.");
		}
		return fields.get(coordinates).isTypeOf(FieldType.SHIP);
	}
	
	public boolean hasFieldAtCoordinate(CoordinatePair coordinates){
		return fields.containsKey(coordinates);
	}

	public void addFieldToTable(TableField field) {
		fields.put(field.getCoordinates(), field);
	}

	public boolean isCoordinateOutOfBounds(CoordinatePair coordinates) {
		int x = coordinates.getX();
		int y = coordinates.getY();
		return (x >= width || y >= height || x < 0 || y < 0);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(TableField field : fields.values()){
			sb.append("\nThere is a ship at:" + field.getCoordinates());
		}
		sb.append("\n");
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				CoordinatePair coordinatePair = new CoordinatePair(i, j);
				if(this.hasFieldAtCoordinate(coordinatePair) && this.getFieldTypeAtCoordinates(coordinatePair).equals(FieldType.SHIP)){
					sb.append(" X ");
				}
				else {
					sb.append(" ~ ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
