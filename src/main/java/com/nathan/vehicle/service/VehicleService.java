package com.nathan.vehicle.service;

import java.util.List;

import com.nathan.vehicle.model.Vehicle;

public interface VehicleService {

	Vehicle getVehicle(final String vin);

	List<Vehicle> getAllVehicles();

	Vehicle createVehicle(final Vehicle vehicle);

	Vehicle updateVehicle(final Vehicle vehicle);
}