package in.bloomapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.bloomapp.exception.ServiceException;
import in.bloomapp.service.FlowerManager;

@Controller
public class DeleteFlowerController {
	@GetMapping("/DeleteFlowerServlet")
	public String deleteFlower(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String category = request.getParameter("category");
		String type = request.getParameter("type");
		System.out.println(category + type);
		// Message message = new Message();
		try {
			System.out.println("deleted");
			FlowerManager.deleteFlower(category.trim(), type);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "displayFlowers.jsp";
	}
}
