package dat251_gruppe2.hotelifinder.domain;

public class Location {
	private double latitude;
	private double longitude;

	private Double longitude;
	private Double latitude;

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

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
