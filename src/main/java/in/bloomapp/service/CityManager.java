package in.bloomapp.service;

import java.util.List;
import in.bloomapp.dao.CityManagerDAO;
import in.bloomapp.exception.DBException;
import in.bloomapp.exception.InvalidInputException;
import in.bloomapp.exception.ServiceException;
import in.bloomapp.exception.ValidCityException;
import in.bloomapp.model.City;
import in.bloomapp.util.BasicValidator;
import in.bloomapp.validator.CityValidator;

public class CityManager {

	private CityManager() {
	}

	/**
	 * Add a new city to the data base
	 * 
	 * @param city
	 * @throws ServiceException
	 */
	public static void addCity(City city) throws ServiceException {
		CityManagerDAO cityManagerDAO = new CityManagerDAO();
		try {
			BasicValidator.isValidString(city.getCity());
			BasicValidator.isCharAllowed(city.getCity());
			CityValidator.validCityCode(city.getDistrictCode());
			cityManagerDAO.save(city);
		} catch (DBException | ValidCityException | InvalidInputException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Update the delivery charge of given city from the database
	 * 
	 * @param city
	 * @throws ServiceException
	 */
	public static void updateCharge(City city) throws ServiceException {
		CityManagerDAO cityManagerDAO = new CityManagerDAO();
		try {
			cityManagerDAO.update(city);
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Delete the city from the data base
	 * 
	 * @param city
	 * @throws ServiceException
	 */
	public static void deleteCity(City city) throws ServiceException {
		CityManagerDAO cityManagerDAO = new CityManagerDAO();
		try {
			cityManagerDAO.delete(city);
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Gets the available cities from the data base
	 * 
	 * @return
	 */
	public static List<City> getCity() {
		CityManagerDAO cityManagerDAO = new CityManagerDAO();
		List<City> cities = null;
		try {
			cities = cityManagerDAO.getCity();
		} catch (DBException e) {
			e.printStackTrace();
		}
		return cities;
	}

	/**
	 * 
	 * @param order
	 * @return
	 * @throws DBException
	 */
	public static int getDeliveryCharge(String deliveryCity) throws DBException {
		int deliveryCharge = 0;
		CityManagerDAO cityManagerDAO = new CityManagerDAO();
		List<City> cities = cityManagerDAO.getCity();
		for (City city : cities) {
			if (deliveryCity.equalsIgnoreCase(city.getCity())) {
				deliveryCharge = city.getDelivaryCharge();
			}
		}
		return deliveryCharge;
	}
}
