package in.bloomapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import in.bloomapp.exception.ServiceException;
import in.bloomapp.model.Order;
import in.bloomapp.service.SummaryManager;
//import in.bloomapp.util1.LocalDateAdapter;
//import in.bloomapp.util1.LocalDateTimeAdapter;

/**
 * Servlet implementation class TodaysOrderServlet
 */
@WebServlet("/TodaysOrderServlet")
public class TodaysOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		try {
			List<Order> orders = SummaryManager.getOrders();
			Gson gson = new Gson();
					/*Builder().setPrettyPrinting()
					.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
					.registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();*/
			String json = gson.toJson(orders);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
