package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EventEntityRepository;
import domain.EventEntity;

@Service
@Transactional
public class EventEntityService {
	
	@Autowired
	private EventEntityRepository evententityRepository ;
	
	public EventEntityService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
//	public EventEntity create() {
//		EventEntity res = new EventEntity();
//		
//		//TODO
//		
//		return res;
//	}

	public EventEntity findOne(int evententityId) {
		return evententityRepository.findOne(evententityId);
	}

	public Collection<EventEntity> findAll(){
		return evententityRepository.findAll();
	}
		
	public EventEntity save(EventEntity evententity) {
		Assert.notNull(evententity);
		return evententityRepository.save(evententity);
	}

	public void delete(EventEntity evententity) {
		Assert.notNull(evententity);
		Assert.isTrue(evententityRepository.exists(evententity.getId()));
		
		evententityRepository.delete(evententity);
		
		Assert.isTrue(!evententityRepository.exists(evententity.getId()));
	}

}