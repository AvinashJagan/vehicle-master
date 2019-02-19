package com.nathan.vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.nathan.vehicle.service.VehicleService;
import com.nathan.vehicle.validator.impl.VINValidator;
import com.nathan.vehicle.validator.impl.VehicleValidator;

public abstract class BaseController {

	@Autowired(required = true)
	protected VehicleService vehicleService;

	@Autowired(required = true)
	protected VINValidator vinValidator;

	@Autowired(required = true)
	protected VehicleValidator vehicleValidator;
}