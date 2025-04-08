package dat251_gruppe2.hotelifinder.domain;

import jakarta.persistence.*;

@Embeddable
public class Location {

	private double latitude;
	private double longitude;

	// Constructor, getters, and setters
	public Location() {
	}

	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	/**
	 * Calculate the distance between two locations
	 *
	 * @param other
	 * @return distance in meters
	 */

	// haversine formel //trenger ikke
	public double distanceTo(Location other) {
		final int R = 6371; // Radius of the earth in kilometers
		double latDistance = Math.toRadians(other.latitude - this.latitude);
		double lonDistance = Math.toRadians(other.longitude - this.longitude);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.latitude))
						* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // Convert to meters
		return distance;
	}
}
