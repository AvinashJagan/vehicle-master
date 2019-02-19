package com.nathan.vehicle.model.exception;

public class InvalidVINException extends RuntimeException {

	private static final long serialVersionUID = -5611350710923721807L;

	public InvalidVINException(final String message) {
		super(message);
	}
}