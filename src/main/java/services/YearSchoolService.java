package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.util.Assert.*;

import repositories.YearSchoolRepository;
import domain.YearSchool;

@Service
@Transactional
public class YearSchoolService {
	
	@Autowired
	private YearSchoolRepository yearschoolRepository ;
	
	@Autowired
	private ActorService actorService;
	
	public YearSchoolService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public YearSchool create() {
		isTrue(actorService.isAdmin());
		YearSchool res = new YearSchool();
		return res;
	}

	public YearSchool findOne(int yearschoolId) {
		return yearschoolRepository.findOne(yearschoolId);
	}

	public Collection<YearSchool> findAll(){
		return yearschoolRepository.findAll();
	}
		
	public YearSchool save(YearSchool yearschool) {
		notNull(yearschool);
		isTrue(actorService.isAdmin());
		YearSchool yearSchoolActive = yearschoolRepository.getYearSchoolIsActiveTrue();
		isTrue(!yearschool.getIsActive() || yearSchoolActive==null || yearSchoolActive.equals(yearschool));
		return yearschoolRepository.save(yearschool);
	}

	public void delete(YearSchool yearschool) {
		notNull(yearschool);
		isTrue(yearschoolRepository.exists(yearschool.getId()));
		
		yearschoolRepository.delete(yearschool);
		
		isTrue(!yearschoolRepository.exists(yearschool.getId()));
	}

	public void activar(int id) {
		YearSchool yearSchool = this.findOne(id);
		notNull(yearSchool);
		isTrue(actorService.isAdmin());
		isTrue(!yearSchool.getIsActive());
		YearSchool yearSchoolActive = yearschoolRepository.getYearSchoolIsActiveTrue();
		if(yearSchoolActive!=null){
			yearSchoolActive.setIsActive(false);
			this.save(yearSchoolActive);
		}
		yearSchool.setIsActive(true);
		this.save(yearSchool);
	}

	public YearSchool getYearSchoolIsActiveTrue(){
		return this.yearschoolRepository.getYearSchoolIsActiveTrue();
	}
	
}