package jp.tuyano.spring.mvc1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	@Autowired
	MyMemoDataRepository repository;
	
	@RequestMapping("/msg")
	public String index(Model model) {
		model.addAttribute("msg", "please type memo data.");
		return "showMessage";
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public ModelAndView postForm(@RequestParam("title") String title,
			@RequestParam("content") String content) {
		MyMemoData memo = new MyMemoData(title, content);
		repository.saveAndFlush(memo);
		ModelAndView model = new ModelAndView("showMessage");
		model.addObject("msg", "add memo<br>" + memo);
		return model;
	}
	
	@RequestMapping("/memo")
	public String memo(Model model) {
		List<MyMemoData> list = repository.findAll();
		String result = "<pre>";
		for(MyMemoData memo : list) {
			result += memo.toString() + "\n";
		}
		result += "</pre>";
		model.addAttribute("msg", result);
		return "showMessage";
	}
}
