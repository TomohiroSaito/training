package sample;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import sample.biz.domain.Owner;

import java.io.Reader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleRun {
	
	@RequestMapping("/hello")
	public String hello(Model model) {
		try {
			Reader reader = Resources.getResourceAsReader("sample/sqlMapConfig.xml");
			SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
			
			Owner owner = (Owner)sqlMap.queryForObject("findOwner", "2");
			
			model.addAttribute("owner", owner);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "showMessage";
	}
}
