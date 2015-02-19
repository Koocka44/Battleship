package com.epam.battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCoordinateUtil {

	@Test
	public void testCalculateRealCoordinates() {
		CoordinatePair baseCoordinates = new CoordinatePair(1, 1);
		CoordinatePair planCoordinates = new CoordinatePair(2, 3);
		CoordinatePair resultCoordinates = CoordinateUtil.calculateRealCoordinates(baseCoordinates, planCoordinates);
		CoordinatePair expectedCoordinates = new CoordinatePair(3, 4);
		assertEquals(expectedCoordinates, resultCoordinates);
	}

}
