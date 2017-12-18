package services;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.BoardRepository;
import domain.Board;
import domain.EventEntity;
import domain.School;
import domain.SchoolClass;
import domain.Teacher;

@Service
@Transactional
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository ;
	@Autowired
	private ActorService actorService;
	
	public BoardService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public Board create(EventEntity eventEntity) {
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
		Board res = new Board();
		res.setEventEntity(eventEntity);
		
		return res;
	}

	public Board findOne(int boardId) {
		return boardRepository.findOne(boardId);
	}

	public Collection<Board> findAll(){
		return boardRepository.findAll();
	}
		
	public Board save(Board board) {
		notNull(board);
		if(board.getEventEntity() instanceof School){
			School school = ((School)board.getEventEntity());
			isTrue(actorService.isManager());
			isTrue(!school.getIsCancel());
			isTrue(actorService.findByPrincipal().equals(school.getManager()));
		}
		if(board.getEventEntity() instanceof SchoolClass){
			SchoolClass schoolClass = ((SchoolClass)board.getEventEntity());
			isTrue(actorService.isTeacher());
			isTrue(!schoolClass.getIsArchive());
			isTrue(!schoolClass.getSchool().getIsCancel());
			isTrue(((Teacher)actorService.findByPrincipal()).getSchoolClass().contains(schoolClass));
		}
		
		return boardRepository.save(board);
	}

	public void delete(Board board) {
		notNull(board);
		if(board.getEventEntity() instanceof School){
			School school = ((School)board.getEventEntity());
			isTrue(actorService.isManager());
			isTrue(!school.getIsCancel());
			isTrue(actorService.findByPrincipal().equals(school.getManager()));
		}
		if(board.getEventEntity() instanceof SchoolClass){
			SchoolClass schoolClass = ((SchoolClass)board.getEventEntity());
			isTrue(actorService.isTeacher());
			isTrue(!schoolClass.getIsArchive());
			isTrue(!schoolClass.getSchool().getIsCancel());
			isTrue(((Teacher)actorService.findByPrincipal()).getSchoolClass().contains(schoolClass));
		}
		
		isTrue(boardRepository.exists(board.getId()));
		
		boardRepository.delete(board);
		
		isTrue(!boardRepository.exists(board.getId()));
	}

}