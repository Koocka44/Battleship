package com.epam.battleship;

public class CoordinateUtil {

	public static CoordinatePair calculateRealCoordinates(CoordinatePair baseCoordinates, CoordinatePair planCoordinates){
		int x = baseCoordinates.getX() + planCoordinates.getX();
		int y = baseCoordinates.getY() + planCoordinates.getY();
		return new CoordinatePair(x, y);
	}
}
