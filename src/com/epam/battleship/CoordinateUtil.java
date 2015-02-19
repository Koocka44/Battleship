package com.epam.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoordinateUtil {

	public static CoordinatePair calculateRealCoordinates(CoordinatePair baseCoordinates, CoordinatePair planCoordinates){
		int x = baseCoordinates.getX() + planCoordinates.getX();
		int y = baseCoordinates.getY() + planCoordinates.getY();
		return new CoordinatePair(x, y);
	}

	public static boolean checkIfCoordinateCanBePlaced(CoordinatePair realCoordinates, GameTable gameTable) {
		if(isCoordinateOrNeighbourAShip(realCoordinates, gameTable) || gameTable.isCoordinateOutOfBounds(realCoordinates)){
			return false;
		}
		return true;
	}
	
	public static boolean isCoordinateOrNeighbourAShip(CoordinatePair realCoordinates, GameTable gameTable){
		List<CoordinatePair> coordinatesToCheck = generatSelfAndNeighboursForCoordinate(realCoordinates);
		for(CoordinatePair currentCoordinate : coordinatesToCheck){
			if(!gameTable.isCoordinateOutOfBounds(currentCoordinate)){
				if(gameTable.hasFieldAtCoordinate(currentCoordinate) && gameTable.getFieldTypeAtCoordinates(currentCoordinate).equals(FieldType.SHIP)){
					return true;
				}
			}
		}
		return false;
	}

	public static CoordinatePair generateRandomBaseCoordinateForShip(Ship ship,
			GameTable gameTable) {
		ShipTypeBoundaries boundaries = ship.getBoundaries();
		Random randomGenerator = new Random();
		int x = randomGenerator.nextInt(gameTable.getWidth() - boundaries.getMaxX()) - boundaries.getMinX();
		int y = randomGenerator.nextInt(gameTable.getWidth() - boundaries.getMaxY()) - boundaries.getMaxX();
		return new CoordinatePair(x, y); 
	}
	
	private static List<CoordinatePair> generatSelfAndNeighboursForCoordinate(CoordinatePair coordinate){
		List<CoordinatePair> coordinateList = new ArrayList<CoordinatePair>();
		int x = coordinate.getX();
		int y = coordinate.getY();
		coordinateList.add(new CoordinatePair(x, y));
		coordinateList.add(new CoordinatePair(x + 1, y));
		coordinateList.add(new CoordinatePair(x - 1, y));
		coordinateList.add(new CoordinatePair(x + 1, y + 1));
		coordinateList.add(new CoordinatePair(x + 1, y - 1));
		coordinateList.add(new CoordinatePair(x - 1, y + 1));
		coordinateList.add(new CoordinatePair(x - 1, y - 1));
		coordinateList.add(new CoordinatePair(x, y + 1));
		coordinateList.add(new CoordinatePair(x, y - 1));
		return coordinateList;
	}
}
