package in.bloomapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import in.bloomapp.exception.DBException;
import in.bloomapp.exception.InvalidInputException;
import in.bloomapp.exception.UserValidationException;
import in.bloomapp.model.User;
import in.bloomapp.userservice.UserManager;


/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(this.getClass().getName());
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String errorMessage=null;
		  try {	  
			String name = request.getParameter("name");
	        String password = request.getParameter("password");
	        String email= request.getParameter("email");
	        String mobileNo= request.getParameter("mobileNo");
	        String address = request.getParameter("address");
	        Long parsedMobileNo = null;
			parsedMobileNo = Long.parseLong(mobileNo);  
	        User user=new User();
	        user.setName(name);
	        user.setPassword(password);
	        user.setEmail(email);
	        user.setMobileNo(parsedMobileNo);
	        user.setAddress(address);    
	        PrintWriter out = response.getWriter();	      
			boolean isAdded = UserManager.addUser(user);
			if (isAdded) {
				String message="Registration successfull";
				JsonObject obj = new JsonObject(); 
				obj.addProperty("IS_ADDED", message);
				out.println(obj);
				out.flush();
	        }
		  }
	        catch( DBException | InvalidInputException |UserValidationException e){
	        	errorMessage=e.getMessage();
	        	PrintWriter out = response.getWriter();
				JsonObject obj = new JsonObject();
				obj.addProperty("IS_ADDED",errorMessage);
				out.println(obj);
				out.flush();
	        } 
		  catch (NumberFormatException e) {
			  logger.info(e.getMessage());
		  }        
	}
}