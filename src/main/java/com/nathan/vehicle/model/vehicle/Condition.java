package com.nathan.vehicle.model.vehicle;

public enum Condition {

	NOT_SET, GOOD, FAIR, POOR;

	public final Boolean isSet() {
		return !NOT_SET.equals(this);
	}

	public final Boolean isGood() {
		return GOOD.equals(this);
	}

	public final Boolean isFair() {
		return FAIR.equals(this);
	}

	public final Boolean isPoor() {
		return POOR.equals(this);
	}
}