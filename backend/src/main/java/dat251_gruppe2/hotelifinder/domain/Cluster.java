package dat251_gruppe2.hotelifinder.domain;

import java.util.ArrayList;


/**
 * Cluster
 */
public class Cluster {
	private String name;
	private Location center;
	private ArrayList<Location> locations;

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
		return locations;
	}

	public void setLocation(ArrayList<Location> location) {
		this.locations = location;
	}


	public Cluster groupLocations(Location location) {
		if (center == null) {
			throw new IllegalStateException("Center location is not set.");
		}

		if (center.distanceTo(location) <= 1000) { // hvorfor klarer den ikke å hente ut?
			if (locations == null) {
				locations = new ArrayList<>();
			}
			locations.add(location);
		}
		return this;
	}

	}


