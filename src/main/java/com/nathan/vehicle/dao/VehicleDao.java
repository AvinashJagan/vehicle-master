package com.nathan.vehicle.dao;

import java.util.List;

import com.nathan.vehicle.model.Vehicle;

public interface VehicleDao{

	Boolean exists(final String vin);

	Vehicle findByVIN(final String VIN);

	List<Vehicle> findAll();

	Vehicle updateOrSave(final Vehicle vehicle);
}