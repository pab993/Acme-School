
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class FolderService {

	@Autowired
	private FolderRepository	folderRepository;

	@Autowired
	private ActorService		actorService;


	public FolderService() {
		super();
	}

	// CRUD methods --------------------------------------------------------------------------------
	public Folder create(final Actor a) {
		Assert.notNull(a);
		Assert.isTrue(actorService.isAuthenticated());
		Folder result;

		result = new Folder();
		result.setMessages(new ArrayList<Message>());
		result.setActor(a);
		result.setIsSystem(false);

		return result;
	}

	public Folder createInFolder(final Actor a) {
		Assert.notNull(a);
		Folder result;

		result = this.create(a);
		result.setName("inbox");
		result.setIsSystem(true);

		return result;
	}

	public Folder createOutFolder(final Actor a) {
		Assert.notNull(a);
		Folder result;

		result = this.create(a);
		result.setName("outbox");
		result.setIsSystem(true);

		return result;
	}

	public Folder createTrashFolder(final Actor a) {
		Assert.notNull(a);
		Folder result;

		result = this.create(a);
		result.setName("trashbox");
		result.setIsSystem(true);

		return result;
	}

	public Folder createSpamFolder(final Actor a) {
		Assert.notNull(a);
		Folder result;

		result = this.create(a);
		result.setName("spam");
		result.setIsSystem(true);

		return result;
	}

	public Folder findOne(final int folderId) {
		return this.folderRepository.findOne(folderId);
	}

	public Collection<Folder> findAll() {
		return this.folderRepository.findAll();
	}

	public Folder save(final Folder folder) {
		System.out.println(folder.getId());
		Assert.notNull(folder);
		//		Assert.isTrue(folderRepository.exists(folder.getId()));
		//		Assert.isTrue(!folderRepository.findOne(folder.getId()).getIsSystem());
		Assert.isTrue(this.actorService.isAuthenticated());
		Assert.isTrue(folder.getActor().getId() == this.actorService.findByPrincipal().getId());
		Folder res;
		res = this.folderRepository.save(folder);
		return res;
	}

	public Folder saveCreate(final Folder folder) {
		System.out.println(folder.getId());
		Assert.notNull(folder);
		//		Assert.isTrue(folderRepository.exists(folder.getId()));
		//		Assert.isTrue(!folderRepository.findOne(folder.getId()).getIsSystem());
		//		Assert.isTrue(actorService.isAuthenticated());
		//		Assert.isTrue(folder.getActor().getId() == this.actorService.findByPrincipal().getId());
		Folder res;
		res = this.folderRepository.save(folder);
		return res;
	}

	public void delete(final Folder folder) {
		Assert.notNull(folder);
		Assert.isTrue(this.folderRepository.exists(folder.getId()));
		Assert.isTrue(!this.folderRepository.findOne(folder.getId()).getIsSystem());
		Assert.isTrue(folder.getActor().getId() == this.actorService.findByPrincipal().getId());
		Assert.isTrue(folder.getMessages().isEmpty());

		this.folderRepository.delete(folder);

		Assert.isTrue(!this.folderRepository.exists(folder.getId()));
	}

	public Folder newFolderOutbox(Actor actor){
		return newFolderSystem(actor, "outbox");
	}
	
	public Folder newFolderInbox(Actor actor){
		return newFolderSystem(actor, "inbox");
	}
	
	public Folder newFolderTrashbox(Actor actor){
		return newFolderSystem(actor, "trashbox");
	}
	
	private Folder newFolderSystem(Actor actor, String name){
		Folder folder = new Folder();
		folder.setName(name);
		folder.setDescription("");
		folder.setIsSystem(true);
		folder.setActor(actor);
		
		return folder;
		
	}
	
	// Other business methods --------------------------------------------------------------------------------

	public Folder findActorAndFolder(final int idActor, final String nameFolder) {
		return this.folderRepository.findActorAndFolder(idActor, nameFolder);
	}
	
	public Folder findActorAndFolderAndIsSystem(final int idActor, final String nameFolder, Boolean isSystem) {
		return this.folderRepository.findActorAndFolderAndIsSystem(idActor, nameFolder, isSystem);
	}

	public Collection<Folder> findActor(final int idActor) {
		return this.folderRepository.findActor(idActor);
	}

	//El n�mero medio de mensajes en las carpetas del sistema.
	public Double getAvgForMessagesPerFolder() {
		return this.folderRepository.getAvgForMessagesPerFolder();
	}

}
