package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChildRepository;
import domain.Child;
import domain.Observation;
import domain.Parent;
import domain.Petition;
import domain.SchoolClass;

@Service
@Transactional
public class ChildService {
	
	@Autowired
	private ChildRepository childRepository ;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ObservationService observationService;
	
	public ChildService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public Child create() {
		Assert.isTrue(actorService.isParent());
		Child res = new Child();
		res.setParent((Parent) actorService.findByPrincipal());
		return res;
	}

	public Child findOne(int childId) {
		return childRepository.findOne(childId);
	}

	public Collection<Child> findAll(){
		return childRepository.findAll();
	}
		
	public Child save(Child child) {
		Assert.notNull(child);
		Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
		return childRepository.save(child);
	}

	public void delete(Child child) {
		Assert.notNull(child);
		Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
		Assert.isTrue(childRepository.exists(child.getId()));
		
		childRepository.delete(child);
		
		Assert.isTrue(!childRepository.exists(child.getId()));
	}

	public Child saveNew(Child child) {
		Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
		return save(child);
	}

	public Child edit(Child child) {
		Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
		return save(child);
	}

	public List<Child> buscarHijoSinSolicitudEnUnaClase(SchoolClass schoolClass) {
		return this.childRepository.buscarHijoSinSolicitudEnUnaClase(schoolClass.getId(), actorService.findByPrincipal().getId());
	}
	
	public Boolean estaEnLaClase(SchoolClass schoolClass, Child child){
		Boolean res=false;
		for(Petition p : child.getPetitions()){
			if(p.getSchoolClass().equals(schoolClass)){
				res=true;
				break;
			}
		}
		return res;
	}

	public void observacionesVistas(Child child) {
		Assert.notNull(child);
		Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
		long milliseconds = System.currentTimeMillis() - 100;
		Date moment = new Date(milliseconds);
		for(Observation o : child.getObservations()){
			o.setDateVisualization(moment);
			observationService.save(o);
		}
		
	}

}