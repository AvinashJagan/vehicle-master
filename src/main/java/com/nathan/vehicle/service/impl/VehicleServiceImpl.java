package com.nathan.vehicle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.vehicle.dao.VehicleDao;
import com.nathan.vehicle.model.Vehicle;
import com.nathan.vehicle.model.exception.VehicleAlreadyExistsException;
import com.nathan.vehicle.model.exception.VehicleNotFoundException;
import com.nathan.vehicle.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired(required = true)
	private VehicleDao vehicleDao;

	@Override
	public Vehicle getVehicle(final String vin) {
		final Vehicle result = vehicleDao.findByVIN(vin);
		if (result.isSet()) {
			return result;
		}
		throw new VehicleNotFoundException("Vehicle not found for vin: " + vin);
	}

	@Override
	public List<Vehicle> getAllVehicles() {
		return vehicleDao.findAll();
	}

	@Override
	public Vehicle createVehicle(final Vehicle vehicle) {
		if (vehicleDao.exists(vehicle.getVin())) {
			throw new VehicleAlreadyExistsException("Vehicle already exists with vin: " + vehicle.getVin());
		}
		return vehicleDao.updateOrSave(vehicle);
	}

	@Override
	public Vehicle updateVehicle(final Vehicle vehicle) {
		final String id = getVehicle(vehicle.getVin()).getId();
		vehicle.identifiedAs(id);
		return vehicleDao.updateOrSave(vehicle);
	}
}