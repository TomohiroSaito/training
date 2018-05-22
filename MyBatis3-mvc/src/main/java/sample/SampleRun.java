package sample;

import org.springframework.web.bind.annotation.RestController;

import sample.biz.domain.Pet;
import sample.dao.PetMapper;
import sample.domain.PetDao;

import java.io.InputStream;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleRun {
	
	@Autowired
	private PetDao petDao;
	
	@RequestMapping("/hello")
	public String hello(Model model) {
		try {
			String resource = "sample/mybatis.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session = sqlMapper.openSession();
			Pet pet = session.selectOne("sample.dao.PetMapper.selectPet", "1");
			session.close();
			
			model.addAttribute("pet", pet);
						
		} catch(Exception e) {
			e.printStackTrace();
		}

		return "showSelect";
	}
	
	@RequestMapping("/insert")
	public String insert(Model model) {
		try {
			String resource = "sample/mybatis.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(inputStream);
			SqlSession session = factory.openSession();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("petId", "10");
			map.put("petName", "タマ");
			map.put("ownerName", "東京太郎");
			map.put("price", "10000");
			map.put("birthDate", new Date());
			int result = session.insert("sample.dao.PetMapper.insertPet", map);
			model.addAttribute("result", result);
			session.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "showInsert";
	}
	
	@RequestMapping("/update")
	public String update(Model model) {
		try {
			String resource = "sample/mybatis.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(inputStream);
			SqlSession session = factory.openSession();
			
			Pet pet = new Pet();
			pet.setPetId("10");
			pet.setPetName("ミケ");
			pet.setOwnerName("板橋太郎");
			pet.setPrice("500000");
			pet.setBirthDate(new Date());
			int result = session.update("sample.dao.PetMapper.updatePet", pet);
			model.addAttribute("result", result);
			session.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "showUpdate";
	}
	
	@RequestMapping("/selectMap")
	public String selectMap(Model model) {
		try {
			String resource = "sample/mybatis.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(inputStream);
			SqlSession session = factory.openSession();
			
			PetMapper mapper = session.getMapper(PetMapper.class);
			Pet pet = mapper.selectPet("10");
			model.addAttribute("pet", pet);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "showSelectMap";
	}
	
	@RequestMapping("/selectAnnotation")
	public String selectAnnotation(Model model) {
		try {
			String resource = "sample/mybatis.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(inputStream);
			SqlSession session = factory.openSession();
			
			PetMapper mapper = session.getMapper(PetMapper.class);
			Pet pet = mapper.selectPetAnnotation("10");
			model.addAttribute("pet", pet);

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "showSelectMap";
	}
	
	@RequestMapping("/sqlsession")
	public String sqlsession(Model model) {
		Pet pet = petDao.findPetById("10");
		model.addAttribute("pet", pet);
		
		return "showSelectMap";
	}
	
}
