package com.epam.battleship;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class TestMainCannon {
	
	private static final int TEST_X_COORDINATE = 1;
	private static final int TEST_Y_COORDINATE = 1;
	
	private ICannon underTest;
	private GameTable targetTableMock;
	private CoordinatePair testCoordinates;
	
	@Before
	public void setUp(){
		this.targetTableMock = EasyMock.createMock(GameTable.class);
		this.underTest = new MainCannon(targetTableMock);
		this.testCoordinates = new CoordinatePair(TEST_X_COORDINATE, TEST_Y_COORDINATE);
		
	}
	
	@Test
	public void testShootShouldReturnTrue() {
		EasyMock.expect(targetTableMock.isShotAHit(testCoordinates)).andReturn(true);
		EasyMock.replay(targetTableMock);
		boolean hit = underTest.shoot(testCoordinates.getX(), testCoordinates.getY());
		assertTrue(hit);
	}
	
	@Test
	public void testShootShouldReturnFalse() {
		EasyMock.expect(targetTableMock.isShotAHit(testCoordinates)).andReturn(false);
		EasyMock.replay(targetTableMock);
		boolean hit = underTest.shoot(testCoordinates.getX(), testCoordinates.getY());
		assertFalse(hit);
	}
}
