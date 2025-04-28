package dat251_gruppe2.hotelifinder.domain;

public class Distance {

	private Hotel origin;
	private Venue destination;

	private Integer distanceMeters;

	public Distance() {
	}

	public Hotel getOrigin() {
		return origin;
	}

	public void setOrigin(Hotel origin) {
		this.origin = origin;
	}

	public Venue getDestination() {
		return destination;
	}

	public void setDestination(Venue destination) {
		this.destination = destination;
	}

	public Integer getDistanceMeters() {
		return distanceMeters;
	}

	public void setDistanceMeters(Integer distanceMeters) {
		this.distanceMeters = distanceMeters;
	}

	public Integer getDistanceSeconds() {
		return distanceSeconds;
	}

	public void setDistanceSeconds(Integer distanceSeconds) {
		this.distanceSeconds = distanceSeconds;
	}

	private Integer distanceSeconds;

}
