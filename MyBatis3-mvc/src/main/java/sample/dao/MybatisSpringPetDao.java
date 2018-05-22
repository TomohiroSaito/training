package sample.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sample.biz.domain.Pet;
import sample.domain.PetDao;

@Repository
public class MybatisSpringPetDao implements PetDao {
	
	@Autowired
	private SqlSession session;
	
	public Pet findPetById(String id) {
		return session.selectOne("sample.dao.PetMapper.selectPet", id);
	}
}
