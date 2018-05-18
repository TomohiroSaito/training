package jp.tuyano.spring.mvc1;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AppController {
	
	private JdbcTemplate jdbcTemplate;
	private ApplicationContext context;
	
	@RequestMapping("/msg")
	public String message(Model model) {
		context = new ClassPathXmlApplicationContext("classpath:/spring/application-config.xml");
		jdbcTemplate = context.getBean(JdbcTemplate.class);
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from mypersondata");
		model.addAttribute("msg", list.get(0));
		
		return "showMessage";
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public ModelAndView postForm(@RequestParam("text1") String text1) {
		ModelAndView mv = new ModelAndView("showMessage");
		mv.addObject("msg", "you write '" + text1 + "'!!!");
		return mv;
	}
}
