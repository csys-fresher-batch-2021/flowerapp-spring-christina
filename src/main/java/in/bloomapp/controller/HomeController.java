package in.bloomapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	//http://localhost:9000/=> open index.jsp
	@GetMapping
	public String home() {
		return "index.jsp";
	}

}
