package in.bloomapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import in.bloomapp.exception.DBException;
import in.bloomapp.exception.InvalidInputException;
import in.bloomapp.model.Order;
import in.bloomapp.service.OrderProcedureManager;

/**
 * Servlet implementation class OrderProcedureServlet
 */
@WebServlet("/OrderProcedureServlet")
public class OrderProcedureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(this.getClass().getName());	
	/**
	 * Initiates an order
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String isAdded="IS_ADDED";
		String errorMessage;
		try {
			String deliveryAddress = request.getParameter("delivaryAddress");
			String deliverCity = request.getParameter("delivaryCity");
			String deliverDate = request.getParameter("deliveryDate");
			String deliveryTime = request.getParameter("deliveryTime");
			String userName = request.getParameter("userName");
			Order order = new Order();
			order.setDeliverAddress(deliveryAddress);
			order.setDeliveryCity(deliverCity);
			LocalDate parsedDate = LocalDate.parse(deliverDate);
			order.setDeliveryDate(parsedDate);
			LocalTime parsedTime = LocalTime.parse(deliveryTime);
			order.setDeliveryTime(parsedTime);
			order.setUserName(userName);
			OrderProcedureManager.addOrder(order);
			String message = "Order Initiated";
			JsonObject obj = new JsonObject();
			obj.addProperty(isAdded, message);
			out.println(obj);
			out.flush();
		} catch (DBException e) {
			errorMessage = e.getMessage();
			JsonObject obj = new JsonObject();
			obj.addProperty(isAdded, errorMessage);
			out.println(obj);
			out.flush();
			logger.info(e.getMessage());
		} catch (InvalidInputException | NumberFormatException e) {
			errorMessage = "Wrong input";
			JsonObject obj = new JsonObject();
			obj.addProperty(isAdded, errorMessage);
			out.println(obj);
			out.flush();
		}
	}
}
