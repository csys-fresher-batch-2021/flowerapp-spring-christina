package in.bloomapp.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.bloomapp.exception.DBException;
import in.bloomapp.model.Order;
import in.bloomapp.service.ApprovalManager;

/**
 * Servlet implementation class ToApproveServlet
 */
@WebServlet("/ToApproveServlet")
public class ToApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * handles operations related to approval
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String message;
			String category = request.getParameter("category");
			String type = request.getParameter("type");
			String price = request.getParameter("price");
			String deliveryAddress = request.getParameter("deliveryAddress");
			String deliveryDate = request.getParameter("deliveryDate");
			String deliveryTime = request.getParameter("deliveryTime");
			String userMobile = request.getParameter("UserMobile");
			String orderDate = request.getParameter("OrderDate");
			String deliveryStatus = request.getParameter("deliveryStatus");
			Order order = new Order();
			order.setOrderCategory(category);
			order.setOrderType(type);
			int parsedPrice = Integer.parseInt(price);
			order.setOrderPrice(parsedPrice);
			order.setDeliverAddress(deliveryAddress);
			LocalDate parsedDate = LocalDate.parse(deliveryDate);
			order.setDeliveryDate(parsedDate);
			LocalTime parsedTime = LocalTime.parse(deliveryTime);
			order.setDeliveryTime(parsedTime);
			Long paresedNo = Long.parseLong(userMobile);
			order.setUserMobileNo(paresedNo);
			LocalDate parsedOrderDate = LocalDate.parse(orderDate);
			order.setOrderDate(parsedOrderDate);
			order.setDeliveryStatus(deliveryStatus);
			ApprovalManager.setApprovalSts(order);
			if(deliveryStatus.equals("yetToDeliver")) {
				message = "Approved";
			}
			else {
				message="Rejected";
			}
			RequestDispatcher rd = request.getRequestDispatcher("ToApproveList.jsp?infoMessage=" + message);
			rd.forward(request, response);
		} 
		catch (DBException e) {
			String message = "This flower is already appoved";
			RequestDispatcher rd = request.getRequestDispatcher("ToApproveList.jsp?errorMessage=" + message);
			rd.forward(request, response);
			logger.info(e.getMessage());
		}
		catch(NumberFormatException | IOException e) {
			logger.info(e.getMessage());
		}
	}

}
