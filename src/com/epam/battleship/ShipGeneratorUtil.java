package com.epam.battleship;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShipGeneratorUtil {
	
	public static void putShipsDown(List<Ship> ships, GameTable gameTable){
		for(Ship ship : ships){
			putShipDown(ship, gameTable);
		}
	}
	
	public static void putShipDown(Ship ship, GameTable gameTable) {
		while (!ship.isOnTable()) {
			CoordinatePair baseCoordinates = CoordinateUtil.generateRandomBaseCoordinateForShip(ship, gameTable);
			if (checkIfShipFits(ship, baseCoordinates, gameTable)) {
				ship.putShipDown(baseCoordinates);
				putShipOnTable(ship, baseCoordinates, gameTable);
			}
		}
	}
	
	private static void putShipOnTable(Ship ship, CoordinatePair baseCoordinates, GameTable gameTable){
		for(TableField field : ship.getFieldsFromShipType()){
			if(field.isTypeOf(FieldType.SHIP)){
				CoordinatePair realCoordinates = CoordinateUtil.calculateRealCoordinates(baseCoordinates, field.getCoordinates());
				gameTable.setFieldTypeAtCoordinates(realCoordinates, FieldType.SHIP);
			}
		}
	}

	private static boolean checkIfShipFits(Ship ship, CoordinatePair baseCoordinates, GameTable gameTable) {
		for (TableField field : ship.getFieldsFromShipType()) {
			if (field.isTypeOf(FieldType.SHIP)) {
				CoordinatePair realCoordinates = CoordinateUtil.calculateRealCoordinates(baseCoordinates,
						field.getCoordinates());
				if (!CoordinateUtil.checkIfCoordinateCanBePlaced(realCoordinates, gameTable)) {
					return false;
				}
			}
		}
		return true;
	}

	public static List<Ship> loadShipsFromFile(String filename) {
		List<Ship> ships = new ArrayList<Ship>();
		Map<CoordinatePair, TableField> fields = new HashMap<CoordinatePair, TableField>();
		try {
			List<String> linesInFile = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
			int rowCounter = 0;
			for (String line : linesInFile) {
				if (rowCounter == 4) {
					int numberOfThisType = Integer.parseInt(line);
					ShipType shipType = new ShipType(fields);
					for(int i = 0; i < numberOfThisType; i++){
						ships.add(new Ship(shipType));
					}
					fields.clear();
					rowCounter = 0;
				} else {
					for (int columnCounter = 0; columnCounter < line.length(); columnCounter++) {
						CoordinatePair currentCoordinate = new CoordinatePair(rowCounter, columnCounter);
						FieldType fieldType = FieldType.WATER;
						if (Character.toLowerCase(line.charAt(columnCounter)) == 'x') {
							fieldType = FieldType.SHIP;
						}
						fields.put(currentCoordinate, new TableField(currentCoordinate, fieldType));
					}
					rowCounter++;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ships;
	}
}
