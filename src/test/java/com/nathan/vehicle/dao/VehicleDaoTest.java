package com.nathan.vehicle.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.nathan.vehicle.dao.impl.VehicleDaoImpl;
import com.nathan.vehicle.model.Vehicle;

public class VehicleDaoTest {

	final VehicleDao vehicleDao = new VehicleDaoImpl();

	@Test
	public void testFindByIdWithNullArgument() {
		final Vehicle result = vehicleDao.findByVIN(null);

		assertNotNull("Vehicle result should not be null.", result);
	}

	@Test
	public void testFindByIdWithNoResult() {
		final Vehicle result = vehicleDao.findByVIN("NoResults");

		assertNotNull("Vehicle result should not be null.", result);
	}

	@Test
	public void testFindByIdWithResult() {
		final String VIN = "A9384HA9348FHA394";
		final Vehicle result = vehicleDao.findByVIN(VIN);

		assertEquals(VIN, result.getVin());
	}
}