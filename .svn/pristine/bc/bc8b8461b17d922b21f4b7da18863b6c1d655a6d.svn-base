
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import forms.ActorForm;

@Service
@Transactional
public class ActorService {

	//Managed Repository =============================================================================

	@Autowired
	private ActorRepository	actorRepository;


	//Services
	// ===============================================================================================

	//SCRUDs Methods
	//===============================================================================================

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		Assert.notNull(actor.getUserAccount());

		Actor result;

		result = this.actorRepository.save(actor);

		return result;
	}

	//Other Business Methods =========================================================================

	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		try {
			userAccount = LoginService.getPrincipal();
			Assert.notNull(userAccount);

			result = this.findByUserAccount(userAccount);
			Assert.notNull(result);
		} catch (Throwable oops) {
			result = null;
		}
		return result;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Actor result;

		result = this.actorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Actor findOne(final int actorId) {
		Actor result;

		result = this.actorRepository.findOne(actorId);

		return result;
	}

	public Collection<Actor> findAll() {

		Collection<Actor> result = new HashSet<Actor>();

		result = this.actorRepository.findAll();

		return result;
	}

	public ActorForm reconstructToForm(final Actor actor) {
		final ActorForm actorForm = new ActorForm();

		actorForm.setName(actor.getName());
		actorForm.setSurname(actor.getSurname());
		actorForm.setBirthdate(actor.getBirthdate());
		actorForm.setPhone(actor.getPhone());
		actorForm.setPhone2(actor.getPhone2());
		actorForm.setEmail(actor.getEmail());
		actorForm.setAddress(actor.getAddress());

		return actorForm;

	}

	public Actor reconstruct1(final ActorForm actorForm, final BindingResult binding) {
		Actor result;

		result = this.findByPrincipal();

		return result;
	}

	//Hay dos reconstructs porque por alguna razón aquí se guardan los cambios en la base de datos en este metodo. Así que de esta manera hago un "rollback".
	public Actor reconstruct2(final ActorForm actorForm, final BindingResult binding) {
		Actor result;

		result = this.findByPrincipal();
		result.setName(actorForm.getName());
		result.setSurname(actorForm.getSurname());
		result.setPhone(actorForm.getPhone());
		result.setPhone2(actorForm.getPhone2());
		result.setEmail(actorForm.getEmail());
		result.setAddress(actorForm.getAddress());
		result.setBirthdate(actorForm.getBirthdate());

		return result;
	}

	public boolean isAuthenticated() {
		try {
			Assert.notNull(LoginService.getPrincipal());
		} catch (final Exception e) {
			return false;
		}

		return true;
	}

	public void checkActorIsAuthenticated() {
		Assert.notNull(LoginService.getPrincipal());
	}

	public boolean checkRole(final String role) {
		boolean result;
		Collection<Authority> authorities;

		result = false;
		authorities = LoginService.getPrincipal().getAuthorities();
		for (final Authority a : authorities)
			result = result || a.getAuthority().equals(role);

		return result;
	}

	public boolean isAdmin() {
		boolean result;

		result = this.checkRole(Authority.ADMIN);

		return result;
	}

	public boolean isTeacher() {
		boolean result;

		result = this.checkRole(Authority.TEACHER);

		return result;
	}

	public boolean isParent() {
		boolean result;

		result = this.checkRole(Authority.PARENT);

		return result;
	}

	public boolean isManager() {
		boolean result;

		result = this.checkRole(Authority.MANAGER);

		return result;
	}

}
