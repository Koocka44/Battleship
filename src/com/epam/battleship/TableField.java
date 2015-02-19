package com.epam.battleship;

public class TableField {
	
	private FieldType fieldType;
	private final CoordinatePair coordinates;
	
	public TableField(CoordinatePair coordinates, FieldType fieldType){
		super();
		this.coordinates = coordinates;
		this.fieldType = fieldType;
	}
	
	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	public CoordinatePair getCoordinates() {
		return coordinates;
	}
	
	public boolean isTypeOf(FieldType fieldType) {
		return this.fieldType.equals(fieldType);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinates == null) ? 0 : coordinates.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableField other = (TableField) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		return true;
	}
	
	
}
