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

import repositories.TeacherRepository;
import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;
import domain.School;
import domain.SchoolClass;
import domain.Teacher;
import forms.ActorRegisterForm;

@Service
@Transactional
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository ;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private FolderService folderService;
	
	public TeacherService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public Teacher create() {
		Teacher res = new Teacher();
		
		Authority autho= new Authority();
		autho.setAuthority(Authority.TEACHER);
		res.getUserAccount().getAuthorities().add(autho);
		
		return res;
	}

	public Teacher findOne(int teacherId) {
		return teacherRepository.findOne(teacherId);
	}

	public Collection<Teacher> findAll(){
		return teacherRepository.findAll();
	}
		
	public Teacher save(Teacher teacher) {
		Assert.notNull(teacher);
		return teacherRepository.save(teacher);
	}

	public Teacher saveNewUser(Teacher teacher) {
		Assert.notNull(teacher);
		Assert.isTrue(teacher.getSchool().getManager().equals(actorService.findByPrincipal()));
		Teacher res =teacherRepository.save(teacher);
		
		folderService.saveCreate(folderService.newFolderInbox(res));
		folderService.saveCreate(folderService.newFolderOutbox(res));
		folderService.saveCreate(folderService.newFolderTrashbox(res));
		
		return res;
	}
	
	public void delete(Teacher teacher) {
		Assert.notNull(teacher);
		Assert.isTrue(teacherRepository.exists(teacher.getId()));
		
		teacherRepository.delete(teacher);
		
		Assert.isTrue(!teacherRepository.exists(teacher.getId()));
	}

	public Teacher reconstruct(final ActorRegisterForm actorRegisterForm, final BindingResult binding, final School school) {
		Assert.notNull(actorRegisterForm);
		Assert.isTrue(school.getManager().equals(actorService.findByPrincipal()));
		
		Teacher result;

		result = this.create();
		result.setSchool(school);
		
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
	
	public void archivar(int id) {
		Teacher teacher = this.teacherRepository.findOne(id);
		Assert.notNull(teacher);
		Assert.isTrue(teacher.getSchool().getManager().equals(actorService.findByPrincipal()));
		Assert.isTrue(!teacher.getIsArchive());
		teacher.setIsArchive(true);
		this.save(teacher);
	}

	public void desarchivar(int id) {
		Teacher teacher = this.teacherRepository.findOne(id);
		Assert.notNull(teacher);
		Assert.isTrue(teacher.getSchool().getManager().equals(actorService.findByPrincipal()));
		Assert.isTrue(teacher.getIsArchive());
		teacher.setIsArchive(false);
		this.save(teacher);
	}

	public Collection<Teacher> buscarProfesoresDeUnColegioNoAsignadosAUnaClase(
			SchoolClass schoolClass) {
		return this.teacherRepository.buscarProfesoresDeUnColegioNoAsignadosAUnaClase(schoolClass.getSchool().getId(), schoolClass.getId());
	}
	
}