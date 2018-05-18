package sample;

import sample.biz.domain.Owner;
import sample.domain.FriendlyDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleRun {
	
	@Autowired
	private FriendlyDao friendlyDao;
	
	@RequestMapping("/hello")
	public String hello(Model model) {
		Owner owner = friendlyDao.getOwner(2);
		model.addAttribute("owner", owner);
		
		return "showMessage";
	}
}
