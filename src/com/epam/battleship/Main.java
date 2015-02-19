package com.epam.battleship;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args){
		Map<CoordinatePair, TableField> fields = new HashMap<CoordinatePair, TableField>();
		fields.put(new CoordinatePair(1, 1), new TableField(new CoordinatePair(1, 1),FieldType.WATER));
		GameTable table = new GameTable(fields,5,5);
		System.out.println(table.getFieldAtCoordinates(new CoordinatePair(1, 1)).getFieldType());
		table.setFieldTypeAtCoordinates(new CoordinatePair(1, 1), FieldType.SHIP);
		System.out.println(table.getFieldAtCoordinates(new CoordinatePair(1, 1)).getFieldType());
		System.out.println(new MainCannon(table).shoot(1, 1));
	}
}
