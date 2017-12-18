package services;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PetitionRepository;
import domain.Child;
import domain.Petition;
import domain.SchoolClass;
import domain.StatePetition;

@Service
@Transactional
public class PetitionService {
	
	@Autowired
	private PetitionRepository petitionRepository ;

	@Autowired
	private ActorService actorService;
	
	public PetitionService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public Petition create() {
		Petition res = new Petition();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Configuramos la fecha que se recibe
		calendar.add(Calendar.SECOND, -10);
		
		res.setMoment(calendar.getTime());
		res.setState(StatePetition.WAIT);
		
		return res;
	}

	public Petition findOne(int petitionId) {
		return petitionRepository.findOne(petitionId);
	}

	public Collection<Petition> findAll(){
		return petitionRepository.findAll();
	}
		
	public Petition save(Petition petition) {
		Assert.notNull(petition);
		return petitionRepository.save(petition);
	}

	public void delete(Petition petition) {
		Assert.notNull(petition);
		Assert.isTrue(petitionRepository.exists(petition.getId()));
		
		petitionRepository.delete(petition);
		
		Assert.isTrue(!petitionRepository.exists(petition.getId()));
	}

	public void solicitar(SchoolClass schoolClass, Child child) {
		notNull(schoolClass);
		notNull(child);
		isTrue(actorService.isParent());
		isTrue(actorService.findByPrincipal().equals(child.getParent()));
		isTrue(!schoolClass.getIsArchive());
		for(Petition p : child.getPetitions()){
			isTrue(!p.getSchoolClass().equals(schoolClass)
					|| (p.getSchoolClass().equals(schoolClass)&&p.getState().equals(StatePetition.CANCEL)));
		}
		Petition petition = this.create();
		petition.setChild(child);
		petition.setSchoolClass(schoolClass);
		this.petitionRepository.save(petition);
	}

	public void aceptar(Integer id) {
		Petition petition = this.petitionRepository.findOne(id);
		isTrue(petition.getSchoolClass().getTeacher().contains(this.actorService.findByPrincipal()));
		isTrue(petition.getState().equals(StatePetition.WAIT));
		isTrue(!petition.getSchoolClass().getIsArchive());
		petition.setState(StatePetition.ACCEPTE);
		this.petitionRepository.save(petition);
	}
	
	public void cancelar(Integer id) {
		Petition petition = this.petitionRepository.findOne(id);
		isTrue(petition.getSchoolClass().getTeacher().contains(this.actorService.findByPrincipal()));
		isTrue(petition.getState().equals(StatePetition.WAIT));
		isTrue(!petition.getSchoolClass().getIsArchive());
		petition.setState(StatePetition.CANCEL);
		this.petitionRepository.save(petition);
	}
	
	public Object[] desvianzaMediaMaxMinNinoPorClaseActiva(){
		return this.petitionRepository.desvianzaMediaMaxMinNinoPorClaseActiva();
	}
	
	public Double porcentajePadresConNinosRegistradosEnClasesActivas(){
		return this.petitionRepository.porcentajePadresConNinosRegistradosEnClasesActivas();
	}
}