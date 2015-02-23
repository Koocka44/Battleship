package com.epam.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args){
		GameTable table = new GameTable(20,20);
		List<CoordinatePair> pointsToShootAt = new ArrayList<CoordinatePair>();
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				pointsToShootAt.add(new CoordinatePair(i, j));
			}
		}
		List<Ship> ships = ShipGeneratorUtil.loadShipsFromFile("ships.txt");
		ShipGeneratorUtil.putShipsDown(ships, table);
		System.out.println(table);
		ICannon cannon = new MainCannon(table);
	}
}
