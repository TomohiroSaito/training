package security.dataaccess;

import security.domain.Account;

public interface AccountDao {

	Account findAccountById(String username);

}
