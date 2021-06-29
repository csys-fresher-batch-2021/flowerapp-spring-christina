package in.bloomapp.validator;

import in.bloomapp.exception.ValidCityException;

public class CityValidator {

	private CityValidator() {
	}
	/**
	 * Checks the city code is valid or not
	 * @param code
	 * @throws ValidCityException
	 */
	public static void validCityCode(int code) throws ValidCityException {
		if(code<=00 && code>=45) {
			throw new ValidCityException("City code not valid");
		}
	}
}
