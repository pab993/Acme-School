package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ObservationRepository;
import domain.Child;
import domain.Observation;
import domain.SchoolClass;
import domain.Teacher;

@Service
@Transactional
public class ObservationService {
	
	@Autowired
	private ObservationRepository observationRepository ;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ChildService childService;
	
	public ObservationService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public Observation create(Child child, SchoolClass schoolClass) {
		Assert.isTrue(actorService.isTeacher());
		Assert.isTrue(schoolClass.getSchool().getTeachers().contains(actorService.findByPrincipal()));
		Assert.isTrue(this.childService.estaEnLaClase(schoolClass, child));
		Observation res = new Observation();
		res.setChild(child);
		res.setTeacher((Teacher) actorService.findByPrincipal());
		res.setDateCreation(new Date());
		res.setSchoolClass(schoolClass);
		
		return res;
	}

	public Observation findOne(int observationId) {
		return observationRepository.findOne(observationId);
	}

	public Collection<Observation> findAll(){
		return observationRepository.findAll();
	}
		
	public Observation save(Observation observation) {
		Assert.notNull(observation);
		return observationRepository.save(observation);
	}

	public void delete(Observation observation) {
		Assert.notNull(observation);
		Assert.isTrue(observationRepository.exists(observation.getId()));
		
		observationRepository.delete(observation);
		
		Assert.isTrue(!observationRepository.exists(observation.getId()));
	}

	public void add(SchoolClass schoolClass, Observation observation) {
		Assert.isTrue(actorService.isTeacher());
		Assert.notNull(observation);
		Assert.notNull(schoolClass);
		Assert.isTrue(!schoolClass.getIsArchive());
		Assert.isTrue(schoolClass.getSchool().getTeachers().contains(actorService.findByPrincipal()));
		Assert.isTrue(this.childService.estaEnLaClase(schoolClass, observation.getChild()));
		observation.setTeacher((Teacher) actorService.findByPrincipal());
		this.observationRepository.save(observation);
	}

	public Collection<Observation> findAllByIdClassAndIdChild(Child child) {
		Assert.notNull(child);
		Assert.isTrue(actorService.isParent());
		Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
		return this.observationRepository.findAllByIdClassAndIdChild(child.getId());
	}

}