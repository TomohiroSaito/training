package sample;

import org.springframework.web.bind.annotation.RestController;

import sample.biz.domain.Pet;
import sample.dao.PetMapper;
import sample.domain.PetDao;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;
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
	
	@RequestMapping("/velocity")
	public String velocity(Model model) {
		 Properties p = new Properties();
		    p.setProperty("input.encoding", "UTF-8");
		    p.setProperty("output.encoding", "UTF-8");
		    p.setProperty("resource.loader", "file");
		    p.setProperty("file.resource.loader.path", "classpath:/template"); // ※このプロパティの値を空にしたいが、設定ファイルだと上手く空にならない。。。このためPropertiesで設定する
		    p.setProperty("file.resource.loader.class", FileResourceLoader.class.getTypeName());
		    p.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
		    p.setProperty("runtime.log.logsystem.log4j.category", "velocity");
		    p.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
		    		    
		Pet pet = petDao.findPetById("10");
		try {
			//Velocityの初期化
			Velocity.init(p);
			
			//Velocityコンテキストに値を設定
			VelocityContext context = new VelocityContext();
			context.put("pet", pet);

			StringWriter sw = new StringWriter();
			//テンプレートの作成
			Template template = Velocity.getTemplate("select.vm");
			//テンプレートとマージ
			template.merge(context, sw);
			//マージしたデータはWriterオブジェクトであるswが持っているのでそれを文字列として出力
			model.addAttribute("sw", sw);

		} catch(ResourceNotFoundException e) {
			System.out.println("テンプレートが見つかりません");
		} catch(ParseErrorException e) {
			System.out.println("構文にエラーがあります");
		} catch(MethodInvocationException e) {
			System.out.println("テンプレートのどこかにエラーがあります");
		} catch(Exception e) {
			System.out.println("その他のエラーです");
		}
		
		return "showSelectVelocity";
	}
}
