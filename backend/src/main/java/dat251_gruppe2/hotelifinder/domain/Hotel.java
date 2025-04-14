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

	public Hotel(String name, Location location, Address address) {
		this.location = location;
		this.name = name;
		this.address = address;
	}

	public void setLocation(Location location) {
		this.location = location;
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
