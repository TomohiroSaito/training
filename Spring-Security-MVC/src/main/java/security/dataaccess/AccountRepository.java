package security.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import security.domain.Account;

@Repository
public class AccountRepository {
	@Autowired
	private AccountDao accountDao;
	
	public Account findOne(String username) {
		Account account = accountDao.findAccountById(username);
		return account;
	}
}
