package in.bloomapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.bloomapp.service.FlowerManager;

/**
 * Servlet implementation class DeleteFlowerServlet
 */
//@WebServlet("/DeleteFlowerServlet")
public class DeleteFlowerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorMessage;
		String category = request.getParameter("category");
		String type = request.getParameter("type");
		try {
			boolean isAdded = FlowerManager.deleteFlower(category, type);
			if (isAdded) {
				errorMessage = "Successfully Deleted Flower";
			} else {
				errorMessage = "Unable to add flower type";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}
		response.sendRedirect("displayFlowers.jsp?errorMessage=" + errorMessage);
	}
}
