package in.bloomapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import in.bloomapp.exception.DBException;
import in.bloomapp.exception.ServiceException;
import in.bloomapp.exception.ValidFlowerException;
import in.bloomapp.model.Flower;
import in.bloomapp.service.FlowerManager;

/**
 * Servlet implementation class AddFlowerServlet
 */
//@WebServlet("/AddFlowerServlet")
public class AddFlowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		String message = null;
		try {
			boolean isAdded = FlowerManager.addFlower(category, type, amount);
			if (isAdded) {
				message = "true";
			}
		} catch (DBException e) {
			message = "Unable to add new flower";
		} catch (ServiceException | ValidFlowerException e) {
			e.printStackTrace();
			message = e.getMessage();
		} finally {
			PrintWriter out = response.getWriter();
			JsonObject obj = new JsonObject();
			obj.addProperty("IS_ADDED", message);
			out.println(obj);
			out.flush();
		}
	}
}
