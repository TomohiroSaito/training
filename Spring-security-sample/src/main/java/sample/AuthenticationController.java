package sample;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springbook.dataaccess.AccountUserDetails;

@Controller
public class AuthenticationController {
	
	
	@RequestMapping(path = "/login")
	public String viewLoginForm() {
		return "loginForm";
	}
	
//	@RequestMapping(path = "/")
//	public String rootAccess() {
//		return "/menu";
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String view(
			@AuthenticationPrincipal AccountUserDetails userDetails,
			Model model) {
		model.addAttribute(userDetails.getAccount());
		return "profile";
	}
}
