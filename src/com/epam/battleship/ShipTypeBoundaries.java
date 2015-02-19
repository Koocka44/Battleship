package com.epam.battleship;

public class ShipTypeBoundaries {
	
	private final int minX;
	private final int maxX;
	private final int minY;
	private final int maxY;
	
	public ShipTypeBoundaries(int minX, int maxX, int minY, int maxY) {
		super();
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}
	
}
