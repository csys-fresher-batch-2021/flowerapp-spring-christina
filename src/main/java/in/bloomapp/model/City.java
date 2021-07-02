package in.bloomapp.model;

import lombok.Data;

/**
 * Defines eligible fields for city
 * 
 * @author chri2631
 *
 */

@Data
public class City {
	String cityName;
	int districtCode;
	int delivaryCharge;
	int status = 1;
}
