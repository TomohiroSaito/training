package sample;

import org.springframework.web.bind.annotation.RestController;

import java.io.Reader;

import org.apache.taglibs.standard.resources.Resources;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class SampleRun {
	
	@RequestMapping("/hello")
	public String hello() {
		try {
			Reader reader = Resources.getResourcesAsReader("sample/sqlMapConfig.xml");
			SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
			
			Owner owner = (Owner)sqlMap.queryForObject("findOwner", "1");
			System.out.println("OwnerId = " + owner.getOwnerId() + " :OwnerName = " + owner.getOwnerName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showMessage";
	}
}
