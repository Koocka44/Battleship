package com.epam.battleship;

public class MainCannon implements ICannon {
	
	private final GameTable targetTable;
	
	public MainCannon(GameTable targetTable) {
		this.targetTable = targetTable;
	}
	
	@Override
	public boolean shoot(int x, int y) {
		return targetTable.isShotAHit(new CoordinatePair(x, y));
	}

}
