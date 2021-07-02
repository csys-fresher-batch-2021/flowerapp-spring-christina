package in.bloomapp.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import in.bloomapp.adminservice.AdminLogin;
import in.bloomapp.userservice.UserManager;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = null;
		if (AdminLogin.login(username, password)) {
			role = "ADMIN";
		} else  {
			try {
				if(UserManager.loginUser(username, password)) {
					role = "USER";
				}
				else {
					String msg = "Please register to login or Enter valid login credentials";
					RequestDispatcher rd = request.getRequestDispatcher("Login.jsp?errorMessage=" + msg);
					rd.forward(request, response);
				}	
			} catch (Exception e) {
				String msg = "Data base server is low";
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp?errorMessage=" + msg);
				rd.forward(request, response);
				e.printStackTrace();
			}
		}
		if(role!=null) {
		HttpSession session = request.getSession();
		session.setAttribute("LOGGED_IN_USER", username);
		session.setAttribute("ROLE", role);
		response.sendRedirect("displayFlowers.jsp");
	}
}
}