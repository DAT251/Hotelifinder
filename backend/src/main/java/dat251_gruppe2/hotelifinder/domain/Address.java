package dat251_gruppe2.hotelifinder.domain;
import jakarta.persistence.*;

@Embeddable
public class Address {

	private String postalCode;
	private String streetName;
	private Integer streetNumber;

	public Address() {
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Integer getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

}
