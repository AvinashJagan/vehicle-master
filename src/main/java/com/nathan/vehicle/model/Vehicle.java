package com.nathan.vehicle.model;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.nathan.vehicle.model.vehicle.Color;
import com.nathan.vehicle.model.vehicle.Condition;

@Document(collection = "vehicle")
@JacksonXmlRootElement(localName = "vehicle")
public final class Vehicle {

	@Id
	private String id;
	private String vin;
	private String make;
	private String model;
	private Integer year;
	private Integer miles;
	private Color color;
	private Condition condition;

	public static final Vehicle NOT_SET = new Vehicle();

	@JsonIgnore
	public final String getId() {
		return id;
	}

	public final String getVin() {
		return vin;
	}

	public final String getMake() {
		return make;
	}

	public final String getModel() {
		return model;
	}

	public final Integer getYear() {
		return year;
	}

	public final Integer getMiles() {
		return miles;
	}

	public final Color getColor() {
		return color;
	}

	public final Condition getCondition() {
		return condition;
	}

	public void identifiedAs(final String id) {
		notBlank(id, "Id cannot be blank.");
		this.id = id;
	}

	@JsonIgnore
	public final Boolean isSet() {
		return !NOT_SET.equals(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Vehicle other = (Vehicle) obj;
		return Objects.equals(vin, other.vin);
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Vehicle [vin=").append(vin).append(", make=").append(make).append(", model=").append(model)
			.append(", year=").append(year).append(", miles=").append(miles).append(", color=").append(color)
			.append(", condition=").append(condition).append("]");
		return builder2.toString();
	}

	public static class Builder {

		private final String vin;
		private final String make;
		private final String model;
		private String id;
		private Integer year;
		private Integer miles;
		private Color color;
		private Condition condition;

		public Builder(final String vin, final String make, final String model) {
			notBlank(vin, "Vin cannot be blank.");
			notBlank(make, "Make cannot be blank.");
			notBlank(model, "Model cannot be blank.");

			this.vin = vin;
			this.make = make;
			this.model = model;
			id = EMPTY;
			year = 0;
			miles = 0;
			color = Color.NOT_SET;
			condition = Condition.NOT_SET;
		}

		public final Builder withId(final String id) {
			notBlank(id, "Id cannot be blank.");

			this.id = id;
			return this;
		}

		public final Builder withYear(final Integer year) {
			notNull(year, "Year cannot be null.");
			isTrue(year > 0, "Year must be greater than zero.");

			this.year = year;
			return this;
		}

		public final Builder withMiles(final Integer miles) {
			notNull(miles, "Miles cannot be null.");
			isTrue(miles >= 0, "Miles must be greater than or equal to zero.");

			this.miles = miles;
			return this;
		}

		public final Builder withColor(final Color color) {
			notNull(color, "Color cannot be null.");

			this.color = color;
			return this;
		}

		public final Builder withCondition(final Condition condition) {
			notNull(condition, "Condition cannot be null.");

			this.condition = condition;
			return this;
		}

		public final Vehicle build() {
			return new Vehicle(this);
		}
	}

	private Vehicle() {
		id = EMPTY;
		vin = EMPTY;
		make = EMPTY;
		model = EMPTY;
		year = 0;
		miles = 0;
		color = Color.NOT_SET;
		condition = Condition.NOT_SET;
	}

	private Vehicle(final Builder builder) {
		this.id = builder.id;
		this.vin = builder.vin;
		this.make = builder.make;
		this.model = builder.model;
		this.year = builder.year;
		this.miles = builder.miles;
		this.color = builder.color;
		this.condition = builder.condition;
	}
}