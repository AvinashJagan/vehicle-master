package com.nathan.vehicle.model.vehicle;

public enum Color {

	NOT_SET, BLUE, GREEN, RED, SILVER, BLACK, ORANGE;

	public final Boolean isSet() {
		return !NOT_SET.equals(this);
	}

	public final Boolean isBlue() {
		return BLUE.equals(this);
	}

	public final Boolean isGreen() {
		return GREEN.equals(this);
	}

	public final Boolean isRed() {
		return RED.equals(this);
	}
	
	public final Boolean isSilver() {
		return SILVER.equals(this);
	}
	
	public final Boolean isBlack() {
		return BLACK.equals(this);
	}
}