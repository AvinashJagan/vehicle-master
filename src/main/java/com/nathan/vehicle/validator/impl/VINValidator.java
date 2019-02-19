package com.nathan.vehicle.validator.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nathan.vehicle.validator.Validator;

@Component
public class VINValidator implements Validator<String> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final Integer VIN_LENGTH = 17;

	@Override
	public Boolean isValid(final String vin) {
		logger.debug("Validating VIN.");

		if (isBlank(vin)) {
			logger.warn("Provided VIN is blank.");
			return FALSE;
		}

		if (!VIN_LENGTH.equals(vin.length())) {
			logger.warn("Provided VIN does not match VIN length.");
			return FALSE;
		}

		if (!isAlphanumeric(vin)) {
			logger.warn("Provided VIN is not only alphanumeric.");
			return FALSE;
		}

		logger.debug("Successfully validating VIN.");
		return TRUE;
	}
}