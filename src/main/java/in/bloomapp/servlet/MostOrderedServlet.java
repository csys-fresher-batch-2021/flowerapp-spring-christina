package in.bloomapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.bloomapp.exception.ServiceException;
import in.bloomapp.model.Flower;
import in.bloomapp.service.SummaryManager;

/**
 * Servlet implementation class SummaryAdminServlet
 */
@WebServlet("/MostOrderedServlet")
public class MostOrderedServlet extends HttpServlet {
	final Logger logger = Logger.getLogger(this.getClass().getName());

	private static final long serialVersionUID = 1L;
	/**
	 * helps to get the order items respective of flowers
	 */
     @Override  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String summaryDate = request.getParameter("summaryDate");
		LocalDate parsedDateSummaryDate=LocalDate.parse(summaryDate);
		try {
			List<Flower> totalSummary=SummaryManager. orderSummary(parsedDateSummaryDate) ;
			Gson gson = new Gson();
			String json = gson.toJson(totalSummary);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		}catch(ServiceException | IOException e) {
			logger.info(e.getMessage());
		}
	}
}
