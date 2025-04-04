package dat251_gruppe2.hotelifinder.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String website;
	@Embedded
	private Location location;

	@Embedded
	private Address address;

	public Hotel() {
	}

	public Hotel() {
	}

	public Hotel(String name, Location location) {
		this.location = location;
		this.name = name;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPostalCode() {
		return this.address.getPostalCode();
	}

	public void setPostalCode(String postalCode) {
		this.address.setPostalCode(postalCode);
	}

	public String getStreetName() {
		return this.address.getStreetName();
	}

	public void setStreetName(String streetName) {
		this.address.setStreetName(streetName);
	}

	public Integer getStreetNumber() {
		return this.address.getStreetNumber();
	}

	public void setStreetNumber(Integer streetNumber) {
		this.address.setStreetNumber(streetNumber);
	}

	public String getName() {
		return name;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
