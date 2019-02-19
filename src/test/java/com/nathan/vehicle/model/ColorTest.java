package com.nathan.vehicle.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.nathan.vehicle.model.vehicle.Color;

public class ColorTest {

	@Test
	public void testIsSet() {
		assertTrue(Color.BLUE.isSet());
		assertFalse(Color.NOT_SET.isSet());
	}

	@Test
	public void testIsBlue() {
		assertTrue(Color.BLUE.isBlue());
		assertFalse(Color.RED.isBlue());
	}

	@Test
	public void testIsRed() {
		assertTrue(Color.RED.isRed());
		assertFalse(Color.GREEN.isRed());
	}

	@Test
	public void testIsGreen() {
		assertTrue(Color.GREEN.isGreen());
		assertFalse(Color.BLUE.isGreen());
	}
}