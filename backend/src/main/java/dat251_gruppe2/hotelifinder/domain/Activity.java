package dat251_gruppe2.hotelifinder.domain;

public class Activity {

	private String name;
	private Location location; // Location of the activity

	// Constructor, getters, and setters
	public Activity(String name, Location location) {
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}
}
