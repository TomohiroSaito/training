package sample.dataaccess;

import sample.biz.domain.Owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import sample.domain.FriendlyDao;

@Component
public class SqlMapFriendlyDao implements FriendlyDao {
	
	@Autowired
	private SqlMapClientTemplate template;
	
	public Owner getOwner(int id) {
		Owner cond = new Owner();
		cond.setOwnerId(id);
		return (Owner) template.queryForObject("findOwner", cond);
	}
}
