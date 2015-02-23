package com.epam.battleship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class TestGameTable {
	
	private GameTable underTest;
	private CoordinatePair testCoordinates;
	private TableField testFieldMock;
	private Map<CoordinatePair, TableField> fields;
	
	@Before
	public void setUp(){
		testFieldMock = EasyMock.createMock(TableField.class);
		testCoordinates = new CoordinatePair(1, 1);
		fields.put(testCoordinates, testFieldMock);
		underTest = new GameTable(5, 5);
	}
	
	@Test
	public void testSetFieldTypeAtCoordinates() {
		FieldType newFieldType = FieldType.SHIP;
		testFieldMock.setFieldType(newFieldType);
		EasyMock.expectLastCall();
		EasyMock.expect(testFieldMock.getFieldType()).andReturn(newFieldType);
		EasyMock.replay(testFieldMock);

		underTest.setFieldTypeAtCoordinates(testCoordinates, newFieldType);
		FieldType fieldTypeFromClass = underTest.getFieldTypeAtCoordinates(testCoordinates);
		assertEquals(newFieldType, fieldTypeFromClass);
	}
	
	@Test
	public void testGetFieldTypeAtCoordinates() {
		FieldType fieldType = FieldType.SHIP;
		EasyMock.expect(testFieldMock.getFieldType()).andReturn(fieldType);
		EasyMock.replay(testFieldMock);

		FieldType fieldTypeFromClass = underTest.getFieldTypeAtCoordinates(testCoordinates);
		assertEquals(fieldType, fieldTypeFromClass);
	}

	@Test
	public void testIsShotAHitShouldReturnTrue() {
		EasyMock.expect(testFieldMock.isTypeOf(FieldType.SHIP)).andReturn(true);
		EasyMock.replay(testFieldMock);

		boolean isHit = underTest.isShotAHit(testCoordinates);
		assertTrue(isHit);
	}
	
	@Test
	public void testIsShotAHitShouldReturnFalse() {
		EasyMock.expect(testFieldMock.isTypeOf(FieldType.SHIP)).andReturn(false);
		EasyMock.replay(testFieldMock);
		
		boolean isHit = underTest.isShotAHit(testCoordinates);
		assertFalse(isHit);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIsShotAHitShouldThrowException() {
		CoordinatePair badCoordinates = new CoordinatePair(6, 6);
		underTest.isShotAHit(badCoordinates);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetFieldTypeAtCoordinatesShouldThrowException() {
		CoordinatePair badCoordinates = new CoordinatePair(6, 6);
		underTest.getFieldTypeAtCoordinates(badCoordinates);
	}
}
