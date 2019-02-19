package com.nathan.vehicle.model.exception;

public class InvalidVehicleException extends RuntimeException {

	private static final long serialVersionUID = -3685837019910362598L;

	public InvalidVehicleException(final String message) {
		super(message);
	}

}