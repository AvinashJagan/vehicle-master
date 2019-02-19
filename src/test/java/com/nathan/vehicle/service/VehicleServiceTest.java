package com.nathan.vehicle.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nathan.vehicle.dao.VehicleDao;
import com.nathan.vehicle.model.Vehicle;
import com.nathan.vehicle.model.exception.VehicleNotFoundException;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleService.class)
public class VehicleServiceTest {

	@Autowired
	private VehicleService vehicleService;

	@MockBean
	private VehicleDao vehicleDao;

	@Test(expected = VehicleNotFoundException.class)
	public void testVehicleServiceWithVINNotFound() {
		final String VIN_NOT_FOUND = "InvalidVinNotFound";
		when(vehicleDao.findByVIN(VIN_NOT_FOUND)).thenReturn(Vehicle.NOT_SET);

		vehicleService.getVehicle(VIN_NOT_FOUND);

		verify(vehicleDao).findByVIN(VIN_NOT_FOUND);
	}

	@Test
	public void testVehicleServiceWithValidVIN() {
		final String VIN_FOUND = "A0384F90W34FS07";
		final Vehicle vehicle = new Vehicle.Builder(VIN_FOUND, "Ford", "Ranger").build();
		when(vehicleDao.findByVIN(VIN_FOUND)).thenReturn(vehicle);

		final Vehicle result = vehicleService.getVehicle(VIN_FOUND);

		verify(vehicleDao).findByVIN(VIN_FOUND);
		assertEquals(vehicle, result);
	}
}