package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SchoolRepository;
import domain.Manager;
import domain.School;

@Service
@Transactional
public class SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private ActorService actorService;

	public SchoolService() {
		super();
	}

	// CRUD methods
	// --------------------------------------------------------------------------------
	public School create() {
		Assert.isTrue(actorService.isManager());
		School res = new School();
		res.setManager((Manager) actorService.findByPrincipal());

		return res;
	}

	public School findOne(int schoolId) {
		School school = schoolRepository.findOne(schoolId);
		Assert.notNull(school);
		Assert.isTrue((!school.getIsArchive() && !school.getIsCancel()) 
				|| actorService.isAdmin() 
				|| school.getManager().equals(actorService.findByPrincipal()));
		return school;
	}

	public Collection<School> findAll() {
		return schoolRepository.findAll();
	}

	public School save(School school) {
		Assert.notNull(school);
		return schoolRepository.save(school);
	}

	public void delete(School school) {
		Assert.notNull(school);
		Assert.isTrue(schoolRepository.exists(school.getId()));

		schoolRepository.delete(school);

		Assert.isTrue(!schoolRepository.exists(school.getId()));
	}

	public School saveNewEdit(School school) {
		Assert.notNull(school);
		Assert.isTrue(school.getManager()
				.equals(actorService.findByPrincipal()));

		return this.save(school);
	}

	public Collection<School> findAllActive() {
		return this.schoolRepository.findAllActive();
	}
	
	public Collection<School> findAllActiveFilter(String keyword) {
		return this.schoolRepository.findAllActiveFilter("%"+keyword+"%");
	}

	public Collection<School> findAllByManager(String keyword) {
		Assert.isTrue(actorService.isManager());
		return this.schoolRepository.findAllByManager("%"+keyword+"%", actorService.findByPrincipal().getId());
	}
	
	public void archivar(int id) {
		School school = this.schoolRepository.findOne(id);
		Assert.notNull(school);
		Assert.isTrue(school.getManager().equals(actorService.findByPrincipal()));
		Assert.isTrue(!school.getIsArchive());
		Assert.isTrue(!school.getIsCancel());
		school.setIsArchive(true);
		this.save(school);
	}

	public void desarchivar(int id) {
		School school = this.schoolRepository.findOne(id);
		Assert.notNull(school);
		Assert.isTrue(school.getManager().equals(actorService.findByPrincipal()));
		Assert.isTrue(school.getIsArchive());
		Assert.isTrue(!school.getIsCancel());
		school.setIsArchive(false);
		this.save(school);
	}

	public void cancelar(int id) {
		School school = this.schoolRepository.findOne(id);
		Assert.notNull(school);
		Assert.isTrue(actorService.isAdmin());
		Assert.isTrue(!school.getIsCancel());
		school.setIsCancel(true);
		this.save(school);
	}
	
	public void activar(int id) {
		School school = this.schoolRepository.findOne(id);
		Assert.notNull(school);
		Assert.isTrue(actorService.isAdmin());
		Assert.isTrue(school.getIsCancel());
		school.setIsCancel(false);
		this.save(school);
	}
	
}