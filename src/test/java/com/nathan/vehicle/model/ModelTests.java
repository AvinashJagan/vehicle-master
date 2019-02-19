package com.nathan.vehicle.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { ColorTest.class, VehicleTest.class })
public class ModelTests {
}