package com.nathan.vehicle.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.nathan.vehicle.model.vehicle.Color;
import com.nathan.vehicle.model.vehicle.Condition;

public class VehicleTest {

	final String TEST_VIN = "ASDF098SDF089W0E487";
	final String TEST_MAKE = "FORD";
	final String TEST_MODEL = "RANGER";
	final Integer TEST_YEAR = Integer.valueOf(2019);
	final Integer TEST_MILES = Integer.valueOf(23);
	final Color TEST_COLOR = Color.BLUE;
	final Condition TEST_CONDITION = Condition.FAIR;

	private Vehicle TEST_VEHICLE;

	@Before
	public void setup() {
		TEST_VEHICLE = new Vehicle.Builder(TEST_VIN, TEST_MAKE, TEST_MODEL).withYear(TEST_YEAR).withMiles(TEST_MILES)
			.withColor(TEST_COLOR).withCondition(TEST_CONDITION).build();
	}

	@Test(expected = NullPointerException.class)
	public void testBuilderWithNullVIN() {
		new Vehicle.Builder(null, TEST_MAKE, TEST_MODEL);
	}

	@Test(expected = NullPointerException.class)
	public void testBuilderWithNullMake() {
		new Vehicle.Builder(TEST_VIN, null, TEST_MODEL);
	}

	@Test(expected = NullPointerException.class)
	public void testBuilderWithNullModel() {
		new Vehicle.Builder(TEST_VIN, TEST_MAKE, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderWithBlankVIN() {
		new Vehicle.Builder(" ", TEST_MAKE, TEST_MODEL);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderWithBlankMake() {
		new Vehicle.Builder(TEST_VIN, " ", TEST_MODEL);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderWithBlankModel() {
		new Vehicle.Builder(TEST_VIN, TEST_MAKE, " ");
	}

	@Test(expected = NullPointerException.class)
	public void testBuilderWithNullYear() {
		new Vehicle.Builder(TEST_VIN, TEST_MAKE, TEST_MODEL).withYear(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderWithNegativeYear() {
		new Vehicle.Builder(TEST_VIN, TEST_MAKE, TEST_MODEL).withYear(-123);
	}

	@Test(expected = NullPointerException.class)
	public void testBuilderWithNullMiles() {
		new Vehicle.Builder(TEST_VIN, TEST_MAKE, TEST_MODEL).withMiles(null);
	}

	@Test(expected = NullPointerException.class)
	public void testBuilderWithNullColor() {
		new Vehicle.Builder(TEST_VIN, TEST_MAKE, TEST_MODEL).withColor(null);
	}

	@Test(expected = NullPointerException.class)
	public void testBuilderWithNullCondition() {
		new Vehicle.Builder(TEST_VIN, TEST_MAKE, TEST_MODEL).withCondition(null);
	}

	@Test
	public void testBuilderDefaults() {
		final Vehicle vehicle = new Vehicle.Builder(TEST_VIN, TEST_MAKE, TEST_MODEL).build();
		final Integer ZERO = Integer.valueOf(0);
		final String EMPTY = "";

		assertEquals(ZERO, vehicle.getYear());
		assertEquals(ZERO, vehicle.getMiles());
		assertFalse(vehicle.getColor().isSet());
		assertFalse(vehicle.getCondition().isSet());
	}

	@Test
	public void testBuilder() {
		final Vehicle vehicle = new Vehicle.Builder(TEST_VIN, TEST_MAKE, TEST_MODEL).build();
		assertEquals(TEST_VIN, vehicle.getVin());
	}

	@Test
	public void testBuilderWithParams() {
		assertNotNull(TEST_VEHICLE);

		assertEquals(TEST_MAKE, TEST_VEHICLE.getMake());
		assertEquals(TEST_MODEL, TEST_VEHICLE.getModel());
		assertEquals(TEST_YEAR, TEST_VEHICLE.getYear());
		assertEquals(TEST_MILES, TEST_VEHICLE.getMiles());
		assertEquals(TEST_COLOR, TEST_VEHICLE.getColor());
		assertEquals(TEST_CONDITION, TEST_VEHICLE.getCondition());
	}

	@Test
	public void testIsSetOnNotSetVehicle() {
		assertFalse(Vehicle.NOT_SET.isSet());
	}

	@Test
	public void testIsSetOnSetVehicle() {
		assertTrue(TEST_VEHICLE.isSet());
	}

	@Test
	public void testToString() {
		assertNotNull(TEST_VEHICLE.toString());
	}

	@Test
	public void testEqualsWithSameValues() {
		assertTrue(TEST_VEHICLE.equals(TEST_VEHICLE));
	}

	@Test
	public void testEqualsWithSameVINButDifferentValues() {
		final Vehicle vehicleOne = new Vehicle.Builder("SD789FS0D79FSDFA087SDF", "HONDA", "ACCORD").build();
		final Vehicle vehicleTwo = new Vehicle.Builder("SD789FS0D79FSDFA087SDF", "FORD", "RANGER").build();

		assertTrue(vehicleOne.equals(vehicleTwo));
	}

	@Test
	public void testEqualsWithNull() {
		assertFalse(TEST_VEHICLE.equals(null));
	}

	@Test
	public void testEqualsWithMisMatchedClass() {
		assertFalse(TEST_VEHICLE.equals(Color.BLUE));
	}

	@Test
	public void testEqualsWithTwoMisMatchingVINs() {
		final Vehicle vehicleOne = new Vehicle.Builder("SD789FS0D79FSDFA087SDF", "HONDA", "ACCORD").build();
		final Vehicle vehicleTwo = new Vehicle.Builder("LKIJ234L2KJ3423L4KJ23L", "HONDA", "ACCORD").build();

		assertFalse(vehicleOne.equals(vehicleTwo));
	}

	@Test
	public void testHashWithTwoMatchingVINs() {
		final String VIN = "A98H4F9L3874HFL93478AZDSJK";
		final Vehicle vehicleOne = new Vehicle.Builder(VIN,"FORD","RANGER").withYear(2000).build();
		final Vehicle vehicleTwo = new Vehicle.Builder(VIN,"CHEVY","COLORADO").withYear(1991).withColor(Color.RED).build();

		assertEquals(vehicleOne.hashCode(), vehicleTwo.hashCode());
	}

	@Test
	public void testHashWithTwoMisMatchingVINs() {
		final Vehicle vehicleOne = new Vehicle.Builder("LOIN43FLALFUH4UI34HF","FORD","RANGER").withMiles(123).build();
		final Vehicle vehicleTwo = new Vehicle.Builder("A0384HFA0384HFJW0489","FORD","RANGER").withMiles(123).build();

		assertNotEquals(vehicleOne.hashCode(), vehicleTwo.hashCode());
	}
}