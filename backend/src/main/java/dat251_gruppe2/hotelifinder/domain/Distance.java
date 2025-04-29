package dat251_gruppe2.hotelifinder.domain;

public class Distance {

	private Hotel origin;
	private Venue destination;

	private String distanceCaption;
	private String durationCaption;

	private Integer distance;
	private Integer duration;

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

	public String getDistanceCaption() {
		return distanceCaption;
	}

	public void setDistanceCaption(String distanceCaption) {
		this.distanceCaption = distanceCaption;
	}

	public String getDurationCaption() {
		return durationCaption;
	}

	public void setDurationCaption(String durationCaption) {
		this.durationCaption = durationCaption;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distanceMeters) {
		this.distance = distanceMeters;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer distanceSeconds) {
		this.duration = distanceSeconds;
	}
}
