package sample.dataaccess;

import sample.biz.domain.Owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import sample.domain.FriendlyDao;

@Component
public class SqlMapFriendlyDao implements FriendlyDao {
	
	@Autowired
	@SuppressWarnings("deprecation")
	private SqlMapClientOperations operation;
	
	@SuppressWarnings("deprecation")
	public Owner getOwner(String id) {
		Owner cond = new Owner();
		cond.setOwnerId(id);
		return (Owner) operation.queryForObject("findOwner", cond);
	}
	
	@SuppressWarnings("deprecation")
	public void insertOwner(Owner owner) {
		operation.update("insertOwner", owner);
	}
	
	@SuppressWarnings("deprecation")
	public void updateOwner(Owner owner) {
		operation.update("updateOwner", owner);
	}
}
