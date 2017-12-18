package services;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.ManagerRepository;
import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Manager;
import forms.ActorRegisterForm;

@Service
@Transactional
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository ;
	
	@Autowired
	private UserAccountRepository	userAccountRepository;
	
	@Autowired
	private FolderService folderService;
	
	public ManagerService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public Manager create() {
		Manager res = new Manager();
		Authority autho= new Authority();
		autho.setAuthority(Authority.MANAGER);
		res.getUserAccount().getAuthorities().add(autho);
		
		return res;
	}

	public Manager findOne(int managerId) {
		return managerRepository.findOne(managerId);
	}

	public Collection<Manager> findAll(){
		return managerRepository.findAll();
	}
		
	public Manager save(Manager manager) {
		Assert.notNull(manager);
		return managerRepository.save(manager);
	}

	public Manager saveNewUser(Manager manager) {
		Assert.notNull(manager);
		Manager res =managerRepository.save(manager);
		
		folderService.saveCreate(folderService.newFolderInbox(res));
		folderService.saveCreate(folderService.newFolderOutbox(res));
		folderService.saveCreate(folderService.newFolderTrashbox(res));
		
		return res;
	}
	
	public void delete(Manager manager) {
		Assert.notNull(manager);
		Assert.isTrue(managerRepository.exists(manager.getId()));
		
		managerRepository.delete(manager);
		
		Assert.isTrue(!managerRepository.exists(manager.getId()));
	}

	public Manager reconstruct(final ActorRegisterForm actorRegisterForm, final BindingResult binding) {

		Manager result;

		result = this.create();
		result.getUserAccount().setUsername(actorRegisterForm.getUsername());
		result.setName(actorRegisterForm.getName());
		result.setSurname(actorRegisterForm.getSurname());
		result.setPhone(actorRegisterForm.getPhone());
		result.setPhone2(actorRegisterForm.getPhone2());
		result.setEmail(actorRegisterForm.getEmail());
		result.setAddress(actorRegisterForm.getAddress());
		result.setBirthdate(actorRegisterForm.getBirthdate());
		result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(actorRegisterForm.getPassword(), null));

		//		comprobarPhone(actorRegisterForm.getPhone(), binding);
		this.comprobarContrasena(actorRegisterForm.getPassword(), actorRegisterForm.getRepeatPassword(), binding);
		this.comprobarUsuarioUnico(actorRegisterForm.getUsername(), binding);

		return result;
	}

	private boolean comprobarUsuarioUnico(final String username, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result = false;

		final UserAccount userAccount = this.userAccountRepository.findByUsername(username);

		if (userAccount == null)
			result = true;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "actorRegister.username.unique";
			//			error = new FieldError("cadidateForm", "password", "actorRegister.paswword.mismatch");
			error = new FieldError("actorRegisterForm", "username", username, false, codigos, null, "");
			binding.addError(error);
		}
		return result;
	}

	private boolean comprobarContrasena(final String password, final String passwordRepeat, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(passwordRepeat))
			result = password.equals(passwordRepeat);
		else
			result = false;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "actorRegister.password.mismatch";
			//			error = new FieldError("cadidateForm", "password", "actorRegister.paswword.mismatch");
			error = new FieldError("actorRegisterForm", "password", password, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

	
}