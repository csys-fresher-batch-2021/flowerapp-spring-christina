package in.bloomapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	//@WebServlet("/RegisterServlet")
	@PostMapping("/RegisterServlet")
	public String register(@RequestParam("mobileNo") Long mobileNo,@RequestParam("password") String password) {
		
		System.out.println("Register method - mobileNo-"+mobileNo+"password-"+password);
		if(mobileNo.toString().length()!=10) {
			return "redirect:index.jsp?errormessage=Ivalid mobile NO";
		}
		return "redirect:login.jsp";
	}	
}