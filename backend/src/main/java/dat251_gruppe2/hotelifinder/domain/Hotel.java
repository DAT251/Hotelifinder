package dat251_gruppe2.hotelifinder.domain;

public class Hotel  {

	private String name;
	private String website;
	private Location location;
	private int  totalTravelTime;
	private Integer postalCode;
	private String streetName;
	private Integer streetNumber;


	public Hotel(String name, Location location) {
		this.location = location;
		this.name = name;
		this.totalTravelTime = 0;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
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


	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}



}
