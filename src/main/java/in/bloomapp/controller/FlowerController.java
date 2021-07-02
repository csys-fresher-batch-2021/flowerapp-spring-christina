package in.bloomapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bloomapp.DTO.Message;
import in.bloomapp.exception.DBException;
import in.bloomapp.exception.ServiceException;
import in.bloomapp.exception.ValidFlowerException;
import in.bloomapp.model.Flower;
import in.bloomapp.service.FlowerManager;

@RestController
public class FlowerController {

	@PostMapping("/AddFlowerServlet")
	public ResponseEntity<?> addFlower(HttpServletRequest request, HttpServletResponse response) {
		String category = request.getParameter("category");
		String type = request.getParameter("type");
		String price = request.getParameter("price");
		int amount = 0;
		try {
			amount = Integer.parseInt(price);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		Flower flower = new Flower();
		flower.setCategory(category);
		flower.setType(type);
		flower.setPrice(amount);
		Message message = new Message();
		try {
			FlowerManager.addFlower(category, type, amount);
			message.setInfoMessage("Successfiully added");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (ValidFlowerException | ServiceException | DBException e) {
			message.setErrorMessage("Unable to add flower");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/DeleteFlowerServlet")
	public ResponseEntity<?> deleteFlower(HttpServletRequest request, HttpServletResponse response) {
		String category = request.getParameter("category");
		String type = request.getParameter("type");
		Message message = new Message();
		try {
			FlowerManager.deleteFlower(category, type);
			message.setInfoMessage("Successfiully added");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (ServiceException e) {
			message.setErrorMessage("Unable to add flower");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}