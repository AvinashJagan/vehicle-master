package com.nathan.vehicle.model.exception;

public class VehicleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8808185939123376033L;

	public VehicleNotFoundException(final String message) {
		super(message);
	}
}