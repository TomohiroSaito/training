package jp.tuyano.spring.mvc1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	
	@RequestMapping("/msg")
	public String index(Model model) {
		model.addAttribute("msg", "this is message from controller!");
		return "showMessage";
	}
}
