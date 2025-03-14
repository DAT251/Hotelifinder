package dat251_gruppe2.hotelifinder.domain;

import java.util.ArrayList;

/**
 * Cluster
 */
public class Cluster<Location> {
	private String name;
	private Location center;
	private ArrayList<Location> location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getCenter() {
		return center;
	}

	public void setCenter(Location center) {
		this.center = center;
	}

	public ArrayList<Location> getLocation() {
		return location;
	}

	public void setLocation(ArrayList<Location> location) {
		this.location = location;
	}

}
