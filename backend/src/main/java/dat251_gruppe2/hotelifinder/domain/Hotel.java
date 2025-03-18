package dat251_gruppe2.hotelifinder.domain;

public class Hotel  {

	private String name;
	private Address address;
	private String website;
	private Location location;
	private int  totalTravelTime;

	public Hotel(String name, Location location) {
		this.location = location;
		this.name = name;
		this.totalTravelTime = 0;
	}

	public String getName() {
		return name;
	}

	public int getTotalTravelTime() {
		return totalTravelTime;
	}

	public void setTotalTravelTime(int totalTravelTime) {
		this.totalTravelTime = totalTravelTime;
	}

	public Location getLocation() {
		return location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}



}
