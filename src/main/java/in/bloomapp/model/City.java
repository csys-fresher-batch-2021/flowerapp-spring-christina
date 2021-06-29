package in.bloomapp.model;

/**
 * Defines eligible fields for city
 * @author chri2631
 *
 */
public class City {
	String cityName;
	int districtCode;
	int delivaryCharge;
	int status=1;

	public String getCity() {
		return cityName;
	}
	public void setCity(String city) {
		this.cityName = city;
	}
	public int getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}
	public int getDelivaryCharge() {
		return delivaryCharge;
	}
	public void setDelivaryCharge(int delivaryCharge) {
		this.delivaryCharge = delivaryCharge;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "City [city=" + cityName + ", districtCode=" + districtCode + ", delivaryCharge=" + delivaryCharge
				+ ", status=" + status + "]";
	}
}
