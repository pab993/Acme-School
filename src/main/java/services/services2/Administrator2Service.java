
package services.services2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.repositories2.Administrator2Repository;
import security.LoginService;
import security.UserAccount;
import domain.Admin;

@Service
@Transactional
public class Administrator2Service {

	//Managed Repository =============================================================================

	@Autowired
	private Administrator2Repository	administratorRepository;


	//Other Business Methods =========================================================================

	public Admin findByPrincipal() {
		Admin result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Admin findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Admin result;

//		result = this.administratorRepository.findByUserAccountId(userAccount.getId());

		return null;
	}

}
