package com.nathan.vehicle;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.nathan.vehicle.controller.ControllerTests;
import com.nathan.vehicle.dao.DaoTests;
import com.nathan.vehicle.model.ModelTests;
import com.nathan.vehicle.service.ServiceTests;

@RunWith(Suite.class)
@SuiteClasses(value = { ControllerTests.class, DaoTests.class, ModelTests.class, ServiceTests.class })
public class NonFunctionalTests {
}