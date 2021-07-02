package in.bloomapp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.bloomapp.DTO.Message;
import in.bloomapp.adminservice.AdminLogin;
import in.bloomapp.exception.DBException;
import in.bloomapp.exception.InvalidInputException;
import in.bloomapp.exception.UserValidationException;
import in.bloomapp.model.User;
import in.bloomapp.userservice.UserManager;

@Controller

public class AuthController {

	@PostMapping("LoginServlet")
	public String login(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = null;
		System.out.println(username + password);
		if (AdminLogin.login(username, password)) {
			role = "ADMIN";
		} else {
			try {
				if (UserManager.loginUser(username, password)) {
					role = "USER";
				} else {
					String msg = "Please register to login or Enter valid login credentials";
					RequestDispatcher rd = request.getRequestDispatcher("Login.jsp?errorMessage=" + msg);
					rd.forward(request, response);
				}
			} catch (Exception e) {
				String msg = "Data base server is low";
				return "Login.jsp?errorMessage=" + msg;
			}
		}
		System.out.println("down");
		if (role != null) {
			session.setAttribute("LOGGED_IN_USER", username);
			session.setAttribute("ROLE", role);
		}
		return "displayFlowers.jsp";
	}

	@PostMapping("/RegisterUserServlet")
	public ResponseEntity<?> register(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String mobileNo = request.getParameter("mobileNo");
		String address = request.getParameter("address");
		Long parsedMobileNo = null;
		parsedMobileNo = Long.parseLong(mobileNo);
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setMobileNo(parsedMobileNo);
		user.setAddress(address);
		Message message = new Message();
		try {
			UserManager.addUser(user);
			message.setInfoMessage("Successfully Registered");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (DBException e) {
			message.setErrorMessage("Unable to Register");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} catch (InvalidInputException e) {
			message.setErrorMessage(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} catch (UserValidationException e) {
			message.setErrorMessage(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/LogoutServlet")
	public String logout(HttpServletRequest request, HttpSession session) {
		session.removeAttribute("LOGGED_IN_USER");
		return "Login.jsp";

	}
}
