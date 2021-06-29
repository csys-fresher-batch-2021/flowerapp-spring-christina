package in.bloomapp.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import in.bloomapp.exception.DBException;
import in.bloomapp.model.Flower;
import in.bloomapp.service.CartManager;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
/**
 * gets item from the display and adds it to cart
 * 
 * @author chri2631
 *
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final Logger logger = Logger.getLogger(this.getClass().getName());
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String category = request.getParameter("category");
			String type = request.getParameter("type");
			String price = request.getParameter("price");
			String userName = request.getParameter("username");
			int parsedPrice = Integer.parseInt(price);
			Flower newOrder = new Flower();
			newOrder.setCategory(category);
			newOrder.setType(type);
			newOrder.setPrice(parsedPrice);
			newOrder.setQuantity(1);
			newOrder.setBuyer(userName);
			CartManager.addToCart(newOrder);
		} catch (DBException e) {
			logger.info(e.getMessage());
		}
		response.sendRedirect("displayFlowers.jsp");
	}
}