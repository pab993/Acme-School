package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.School;

@Service
@Transactional
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository ;
	@Autowired
	private ActorService actorService;
	@Autowired
	private SchoolService schoolService;
	
	public CommentService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public Comment create(int id) {
		Assert.isTrue(actorService.isAuthenticated());
		Comment res = new Comment();
		res.setActor(actorService.findByPrincipal());
		res.setCreateMoment(new Date());
		School school = schoolService.findOne(id);
		Assert.isTrue(!school.getIsArchive() && !school.getIsCancel());
		res.setSchool(school);
		return res;
	}

	public Comment findOne(int commentId) {
		return commentRepository.findOne(commentId);
	}

	public Collection<Comment> findAll(){
		return commentRepository.findAll();
	}
		
	public Comment save(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(actorService.isAuthenticated());
		return commentRepository.save(comment);
	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(commentRepository.exists(comment.getId()));
		
		commentRepository.delete(comment);
		
		Assert.isTrue(!commentRepository.exists(comment.getId()));
	}

}