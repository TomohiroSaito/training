package sample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sample.biz.domain.Owner;
import sample.domain.OwnerDao;

@Repository
public class OwnerDaoImpl implements OwnerDao {
	
	@Autowired
	private OwnerMapper ownerMapper;
	
	public Owner findOwnerById(String id) {
		
		Owner owner = ownerMapper.selectOwner(id);
		
		return owner;
	}
}
