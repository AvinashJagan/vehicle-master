package com.nathan.vehicle.controller.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nathan.vehicle.model.exception.InvalidVINException;
import com.nathan.vehicle.model.exception.InvalidVehicleException;
import com.nathan.vehicle.model.exception.VehicleAlreadyExistsException;
import com.nathan.vehicle.model.exception.VehicleNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler({ InvalidVINException.class, InvalidVehicleException.class, VehicleAlreadyExistsException.class })
	protected ResponseEntity<Object> handleInvalidVIN(final Exception exception, final WebRequest request) {
		logger.warn(exception.getMessage());

		return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
			request);
	}

	@ExceptionHandler(VehicleNotFoundException.class)
	protected ResponseEntity<Object> handleVehicleNotFound(final VehicleNotFoundException exception,
		final WebRequest request) {
		logger.warn(exception.getMessage());

		return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND,
			request);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> unexpectedException(final Exception exception, final WebRequest request) {
		logger.error(exception.getMessage());

		return handleExceptionInternal(exception, "Internal Server Error", new HttpHeaders(),
			HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}