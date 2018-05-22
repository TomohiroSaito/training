package java;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import sample.biz.domain.Pet;

public class SampleRunJava {
	public static void main(String[] args) throws Exception {
		String resource = "sample/mybatis.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlMapper.openSession();
		Pet pet = session.selectOne("sample.dao.PetMapper.selectPet", 1);
		session.close();
		System.out.println(pet.getPetName());
		System.out.println(pet.getOwnerName());
		System.out.println(pet.getPrice());
		System.out.println(pet.getBirthDate());
	}
}
