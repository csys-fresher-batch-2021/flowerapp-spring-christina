package in.bloomapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bloomapp.exception.ServiceException;
import in.bloomapp.model.City;
import in.bloomapp.service.CityManager;

@RestController
public class OrderController {

	@GetMapping("/DisplayCitiesServlet")
	public List<City> getCities() {
		List<City> cities = null;
		try {
			cities = CityManager.getCity();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return cities;
	}
}
