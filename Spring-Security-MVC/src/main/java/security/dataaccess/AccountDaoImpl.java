package security.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import security.domain.Account;
import security.domain.AccountMapper;

@Repository
public class AccountDaoImpl implements AccountDao {
	@Autowired
	private AccountMapper accountMapper;
	
	public Account findAccountById(String id) {
		
		Account account = accountMapper.selectAccount(id);
		
		return account;
	}
}
