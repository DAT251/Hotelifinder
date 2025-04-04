package dat251_gruppe2.hotelifinder.domain;

import java.util.ArrayList;
import java.util.List;

public class Venue {

	private String name;
	// private String website;
	private String imageURL;
	private List<String> tags = new ArrayList<>();
	private String postalCode;
	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	private String streetName;
	private Integer streetNumber;

	public Venue() {
	}

	/*
	 * public String getWebsite() {
	 * return website;
	 * }
	 * 
	 * public void setWebsite(String website) {
	 * this.website = website;
	 * }
	 */

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public void addTag(String tag) {
		this.tags.add(tag);
	}

	public void removeTag(String tag) {
		this.tags.remove(tag);
	}

}
