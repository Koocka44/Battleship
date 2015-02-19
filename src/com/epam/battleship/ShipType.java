package com.epam.battleship;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShipType {

	private Map<CoordinatePair, TableField> fields;
	private ShipTypeBoundaries boundaries;

	public ShipType(Map<CoordinatePair, TableField> fields) {
		setBoundariesFromFields(fields);
		this.fields = new HashMap<CoordinatePair, TableField>(fields);
	}

	private void setBoundariesFromFields(Map<CoordinatePair, TableField> fields) {
		int minX = 3;
		int maxX = 0;
		int minY = 3;
		int maxY = 0;
		for (TableField field : fields.values()) {
			int x = field.getX();
			int y = field.getY();
			if (x < minX) {
				minX = x;
			}
			if (x > maxX) {
				maxX = x;
			}
			if (y < minY) {
				minY = y;
			}
			if (y > maxY) {
				maxY = y;
			}
		}
		this.boundaries = new ShipTypeBoundaries(minX, maxX, minY, maxY);
	}

	public Collection<TableField> getShipTypeFields() {
		return fields.values();
	}

	public ShipTypeBoundaries getBoundaries() {
		return this.boundaries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
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
		ShipType other = (ShipType) obj;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		return true;
	}
}
