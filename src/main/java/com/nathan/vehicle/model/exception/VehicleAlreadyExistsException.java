package com.nathan.vehicle.model.exception;

public class VehicleAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 5624799466791013243L;

	public VehicleAlreadyExistsException(final String message) {
		super(message);
	}
}