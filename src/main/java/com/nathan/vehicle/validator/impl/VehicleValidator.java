package com.nathan.vehicle.validator.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nathan.vehicle.model.Vehicle;
import com.nathan.vehicle.validator.Validator;

@Component
public class VehicleValidator implements Validator<Vehicle> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired(required = true)
	private VINValidator vinValidator;

	@Override
	public Boolean isValid(final Vehicle vehicle) {
		logger.debug("Validating vehicle.");

		if (vehicle == null || !vehicle.isSet()) {
			logger.warn("Vehicle is null or not set.");
			return FALSE;
		}

		if (!areAllRequiredFieldsProvided(vehicle)) {
			logger.warn("All required fields for vehicle are not provided.");
			return FALSE;
		}

		if (!vinValidator.isValid(vehicle.getVin())) {
			logger.warn("Vehicle VIN is not valid.");
			return FALSE;
		}

		logger.debug("Successfully validating vehicle.");
		return TRUE;
	}

	private Boolean areAllRequiredFieldsProvided(final Vehicle vehicle) {
		if (isBlank(vehicle.getVin()) || isBlank(vehicle.getMake()) || isBlank(vehicle.getModel())) {
			return FALSE;
		}
		return TRUE;
	}
}