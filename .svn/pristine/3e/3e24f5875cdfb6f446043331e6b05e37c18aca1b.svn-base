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

import repositories.ParentRepository;
import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Parent;
import forms.ActorRegisterForm;

@Service
@Transactional
public class ParentService {
	
	@Autowired
	private ParentRepository parentRepository ;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private UserAccountRepository	userAccountRepository;
	
	public ParentService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public Parent create() {
		Parent res = new Parent();
		Authority autho= new Authority();
		autho.setAuthority(Authority.PARENT);
		res.getUserAccount().getAuthorities().add(autho);
		
		return res;
	}

	public Parent findOne(int parentId) {
		return parentRepository.findOne(parentId);
	}

	public Collection<Parent> findAll(){
		return parentRepository.findAll();
	}
		
	public Parent save(Parent parent) {
		Assert.notNull(parent);
		return parentRepository.save(parent);
	}

	public Parent saveNewUser(Parent parent) {
		Assert.notNull(parent);
		Parent res =parentRepository.save(parent);
		
		folderService.saveCreate(folderService.newFolderInbox(res));
		folderService.saveCreate(folderService.newFolderOutbox(res));
		folderService.saveCreate(folderService.newFolderTrashbox(res));
		
		return res;
	}
	
	public void delete(Parent parent) {
		Assert.notNull(parent);
		Assert.isTrue(parentRepository.exists(parent.getId()));
		
		parentRepository.delete(parent);
		
		Assert.isTrue(!parentRepository.exists(parent.getId()));
	}

	public Parent reconstruct(final ActorRegisterForm actorRegisterForm, final BindingResult binding) {

		Parent result;

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