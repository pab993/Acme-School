package services;

import static org.springframework.util.Assert.isTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SpecialEventRepository;
import domain.Actor;
import domain.Child;
import domain.EventEntity;
import domain.Message;
import domain.School;
import domain.SchoolClass;
import domain.SpecialEvent;
import domain.Teacher;

@Service
@Transactional
public class SpecialEventService {
	
	@Autowired
	private SpecialEventRepository specialeventRepository ;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ChildService childService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	public SpecialEventService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public SpecialEvent create(EventEntity eventEntity) {
		if(eventEntity instanceof School){
			School school = ((School)eventEntity);
			isTrue(actorService.isManager());
			isTrue(!school.getIsCancel());
			isTrue(actorService.findByPrincipal().equals(school.getManager()));
		}
		if(eventEntity instanceof SchoolClass){
			SchoolClass schoolClass = ((SchoolClass)eventEntity);
			isTrue(actorService.isTeacher());
			isTrue(!schoolClass.getIsArchive());
			isTrue(!schoolClass.getSchool().getIsCancel());
			isTrue(((Teacher)actorService.findByPrincipal()).getSchoolClass().contains(schoolClass));
		}
		SpecialEvent res = new SpecialEvent();
		res.setEventEntity(eventEntity);
		
		return res;
	}

	public SpecialEvent findOne(int specialeventId) {
		SpecialEvent specialEvent = specialeventRepository.findOne(specialeventId);
		Assert.isTrue(!specialEvent.getIsCancel());
		return specialEvent;
	}

	public Collection<SpecialEvent> findAll(){
		return specialeventRepository.findAll();
	}
		
	public SpecialEvent save(SpecialEvent specialevent) {
		Assert.notNull(specialevent);
		if(specialevent.getEventEntity() instanceof School){
			School school = ((School)specialevent.getEventEntity());
			isTrue(actorService.isManager());
			isTrue(!school.getIsCancel());
			isTrue(actorService.findByPrincipal().equals(school.getManager()));
		}
		if(specialevent.getEventEntity() instanceof SchoolClass){
			SchoolClass schoolClass = ((SchoolClass)specialevent.getEventEntity());
			isTrue(actorService.isTeacher());
			isTrue(!schoolClass.getIsArchive());
			isTrue(!schoolClass.getSchool().getIsCancel());
			isTrue(((Teacher)actorService.findByPrincipal()).getSchoolClass().contains(schoolClass));
		}
		
		return specialeventRepository.save(specialevent);
	}

	public void delete(SpecialEvent specialevent) {
		Assert.notNull(specialevent);
		Assert.isTrue(specialeventRepository.exists(specialevent.getId()));
		Assert.isTrue(!specialevent.getIsCancel());
		specialevent.setIsCancel(true);
		
		this.save(specialevent);
		for(Child c : specialevent.getChild()){
			this.enviarMensajeCancelacionEvento(specialevent, c);
		}
	}

	private void enviarMensajeCancelacionEvento(SpecialEvent specialevent, Child c) {
		Message msg=messageService.create();
		msg.setSubject("Cancelación");
		msg.setBody("El evento "+specialevent.getTitle()+" ha sido cancelado.");
		msg.setReceives(Arrays.asList((Actor)c.getParent()));
		msg.setSend(actorService.findByPrincipal());
		msg.setFolder(folderService.findActorAndFolderAndIsSystem(c.getParent().getId(), "inbox", true));
		messageService.save(msg);
	}

	public Collection<SpecialEvent> getMisPosiblesSpecialEvent(int idChild) {
		Child child = childService.findOne(idChild);
		Assert.notNull(child);
		Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
		
		Set<SpecialEvent> res = new HashSet<>();
		for(SchoolClass s : schoolClassService.findAllByChildActivas(child)){
			for(SpecialEvent e :s.getSpecialEvent()){
				if(!e.getIsCancel()){
					res.add(e);
				}
			}
			for(SpecialEvent e :s.getSchool().getSpecialEvent()){
				if(!e.getIsCancel()){
					res.add(e);
				}
			}
		}
		
		return res;
	}

	public void inscribirse(int idChild, int id) {
		Child child = childService.findOne(idChild);
		SpecialEvent event = specialeventRepository.findOne(id);
		Assert.notNull(child);
		Assert.notNull(event);
		Assert.isTrue(!event.getChild().contains(child));
		Assert.isTrue(event.getSpace()>event.getChild().size());
		Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
		event.getChild().add(child);
		if (event.getEventEntity() instanceof School) {
			Assert.isTrue(!((School)event.getEventEntity()).getIsArchive());
			Assert.isTrue(!((School)event.getEventEntity()).getIsCancel());
			Boolean aux = false;
			for(SchoolClass c : ((School)event.getEventEntity()).getSchoolClasses()){
				if(childService.estaEnLaClase(c, child)){
					aux=true;
					break;
				}
			}
			Assert.isTrue(aux);
		}
		if(event.getEventEntity() instanceof SchoolClass){
			Assert.isTrue(!((SchoolClass)event.getEventEntity()).getIsArchive());
			Assert.isTrue(childService.estaEnLaClase((SchoolClass) event.getEventEntity(), child));
		}
		specialeventRepository.save(event);
		
	}

}