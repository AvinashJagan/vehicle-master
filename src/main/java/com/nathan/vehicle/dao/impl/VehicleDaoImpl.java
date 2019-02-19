package com.nathan.vehicle.dao.impl;

import static java.util.Collections.emptyList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.nathan.vehicle.dao.VehicleDao;
import com.nathan.vehicle.model.Vehicle;

@Repository
public class VehicleDaoImpl implements VehicleDao {

	@Autowired(required = true)
	private MongoTemplate template;

	@Override
	public Boolean exists(final String vin) {
		Query query = new Query();
		query.addCriteria(Criteria.where("vin").is(vin));

		return template.exists(query, Vehicle.class);
	}

	@Override
	public Vehicle findByVIN(final String vin) {
		Query query = new Query();
		query.addCriteria(Criteria.where("vin").is(vin));

		final Vehicle result = template.findOne(query, Vehicle.class);
		if (result != null) {
			return result;
		}
		return Vehicle.NOT_SET;
	}

	@Override
	public List<Vehicle> findAll() {
		final List<Vehicle> results = template.findAll(Vehicle.class);
		if (results != null) {
			return results;
		}
		return emptyList();
	}

	@Override
	public Vehicle updateOrSave(final Vehicle vehicle) {
		template.save(vehicle);
		return vehicle;
	}
}