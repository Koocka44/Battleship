package com.epam.battleship;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args){
		GameTable table = new GameTable(20,20);
		List<Ship> ships = ShipGeneratorUtil.loadShipsFromFile("ships.txt");
		ShipGeneratorUtil.putShipsDown(ships, table);
		System.out.println(table);
	}
}
