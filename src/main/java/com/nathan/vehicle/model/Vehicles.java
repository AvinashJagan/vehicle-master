package com.nathan.vehicle.model;

import static org.apache.commons.lang3.Validate.noNullElements;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "vehicles")
public class Vehicles {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "vehicle")
	private List<Vehicle> vehicles;

	public static Vehicles valueOf(final List<Vehicle> vehicles) {
		notNull(vehicles, "Vehicles cannot be null.");
		noNullElements(vehicles, "Vehicles cannot have null elements.");

		return new Vehicles(vehicles);
	}

	public final List<Vehicle> getVehicles() {
		return vehicles;
	}

	private Vehicles(final List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}