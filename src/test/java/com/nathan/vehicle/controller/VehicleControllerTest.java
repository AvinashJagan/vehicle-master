package com.nathan.vehicle.controller;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.apache.commons.lang3.StringUtils.endsWith;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.nathan.vehicle.model.Vehicle;
import com.nathan.vehicle.model.exception.VehicleNotFoundException;
import com.nathan.vehicle.service.VehicleService;
import com.nathan.vehicle.validator.impl.VINValidator;
import com.nathan.vehicle.validator.impl.VehicleValidator;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

	private static final String GET_VEHICLE_ROOT = "/vehicle/{vin}";
	private static final String GET_ALL_VEHICLES = "/vehicle/all";

	private static final String VIN_INVALID = "InvalidVIN";
	private static final String VIN_NOT_FOUND = "4FH9ALH974AF47QC89";

	private static final String VIN_VALID = "A3907F409A74F0A4F";
	private static final String MAKE_VALID = "Honda";
	private static final String MODEL_VALID = "Ridgeline";

	private static final Vehicle VEHICLE = new Vehicle.Builder(VIN_VALID, MAKE_VALID, MODEL_VALID).build();
	private static final List<Vehicle> VEHICLES = Arrays.asList(VEHICLE);

	@Autowired
	private MockMvc mvc;

	@MockBean
	private VehicleService vehicleService;

	@MockBean
	private VINValidator vinValidator;

	@MockBean
	private VehicleValidator vehicleValidator;

	@Test
	public void testGetVehicleWithInvalidVIN() throws Exception {
		when(vinValidator.isValid(VIN_INVALID)).thenReturn(FALSE);

		mvc.perform(get(GET_VEHICLE_ROOT, VIN_INVALID)).andExpect(status().isBadRequest());

		verify(vinValidator).isValid(VIN_INVALID);
	}

	@Test
	public void testGetVehicleNotFound() throws Exception {
		when(vinValidator.isValid(VIN_NOT_FOUND)).thenReturn(TRUE);

		when(vehicleService.getVehicle(VIN_NOT_FOUND))
			.thenThrow(new VehicleNotFoundException("Vehicle not found for vin " + VIN_NOT_FOUND));

		mvc.perform(get(GET_VEHICLE_ROOT, VIN_NOT_FOUND)).andExpect(status().isNotFound());

		verify(vinValidator).isValid(VIN_NOT_FOUND);
		verify(vehicleService).getVehicle(VIN_NOT_FOUND);
	}

	@Test
	public void testGetVehicleValidWithDefaultJsonResponse() throws Exception {
		when(vinValidator.isValid(VIN_VALID)).thenReturn(TRUE);
		when(vehicleService.getVehicle(VIN_VALID)).thenReturn(VEHICLE);

		final MvcResult result = mvc.perform(get(GET_VEHICLE_ROOT, VIN_VALID)).andExpect(status().isOk()).andReturn();

		verify(vehicleService).getVehicle(VIN_VALID);
		assertTrue(isValidJson(result.getResponse().getContentAsString()));
	}

	@Test
	public void testGetVehicleWithXMLResponse() throws Exception {
		when(vinValidator.isValid(VIN_VALID)).thenReturn(TRUE);
		when(vehicleService.getVehicle(VIN_VALID)).thenReturn(VEHICLE);

		final MvcResult result = mvc.perform(get(GET_VEHICLE_ROOT, VIN_VALID).accept(MediaType.APPLICATION_XML))
			.andExpect(status().isOk()).andReturn();

		verify(vehicleService).getVehicle(VIN_VALID);
		assertTrue(isValidXML(result.getResponse().getContentAsString()));
	}

	@Test
	public void testGetAllVehiclesWithJsonResponse() throws Exception {
		when(vehicleService.getAllVehicles()).thenReturn(VEHICLES);

		final MvcResult result = mvc.perform(get(GET_ALL_VEHICLES)).andExpect(status().isOk()).andReturn();

		verify(vehicleService).getAllVehicles();
		assertTrue(isValidJson(result.getResponse().getContentAsString()));
	}
	
	@Test
	public void testGetAllVehiclesWithXMLResponse() throws Exception {
		
	}

	private boolean isValidJson(final String json) {
		try {
			new JSONObject(json);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	private boolean isValidXML(final String xml) {
		if (startsWith(xml, "<Vehicle>") && endsWith(xml, "</Vehicle>")) {
			return true;
		}
		return false;
	}
}