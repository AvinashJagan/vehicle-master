package com.nathan.vehicle.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.vehicle.model.Vehicle;
import com.nathan.vehicle.model.Vehicles;
import com.nathan.vehicle.model.exception.InvalidVINException;
import com.nathan.vehicle.model.exception.InvalidVehicleException;

@RestController
@RequestMapping("/vehicle")
public class VehicleController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping(path = "/{vin}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Vehicle getVehicleByVIN(@PathVariable final String vin) {
		if (!vinValidator.isValid(vin)) {
			throw new InvalidVINException("Invalid VIN.");
		}
		logger.debug("Received request to get vehicle for vin: {}", vin);

		return vehicleService.getVehicle(vin);
	}

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Vehicles getAllVehicles() {
		logger.debug("Received request to get all vehicles.");

		final List<Vehicle> vehicles = vehicleService.getAllVehicles();

		return Vehicles.valueOf(vehicles);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Vehicle createVehicle(@RequestBody final Vehicle vehicle) {
		if (!vehicleValidator.isValid(vehicle)) {
			logger.warn("Invalid request to create vehicle.");
			throw new InvalidVehicleException("Invalid vehicle to create.");
		}
		logger.debug("Received request to create vehicle: {}", vehicle);

		return vehicleService.createVehicle(vehicle);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Vehicle updateVehicle(@RequestBody final Vehicle vehicle) {
		if (!vehicleValidator.isValid(vehicle)) {
			logger.warn("Invalid request to update vehicle.");
			throw new InvalidVehicleException("Invalid vehicle to update.");
		}
		logger.debug("Received request to update vehicle: {}", vehicle);

		return vehicleService.updateVehicle(vehicle);
	}
}