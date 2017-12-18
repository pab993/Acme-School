
package controllers.actor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Controller
@RequestMapping("/message/actor")
public class MessageActorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private MessageService	messageService;

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public MessageActorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int folderId) {
		ModelAndView result;
		Folder folder;
		Collection<Message> messages;

		try {
			folder = this.folderService.findOne(folderId);
			messages = folder.getMessages();
			//messages = this.messageService.findMessagesByFolder(folderId);
			Assert.isTrue(folder.getActor().getId() == this.actorService.findByPrincipal().getId());

			for (Message message : messages)
				for (Actor actor : message.getReceives())
					System.out.print(actor.getName());

			result = new ModelAndView("message/list");
			result.addObject("requestURI", "message/actor/list.do");
			result.addObject("messages", messages);

		} catch (final Exception e) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message message;

		message = this.messageService.create();
		message.setFolder(this.folderService.findActorAndFolderAndIsSystem(this.actorService.findByPrincipal().getId(), "inbox", true));
		result = this.createEditModelAndView(message);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "send")
	public ModelAndView save(@ModelAttribute(value = "sendMessage") @Valid final Message message, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(message);
		else
			try {
				try {
					Assert.isTrue(message.getSend().getId() == this.actorService.findByPrincipal().getId());
				} catch (final Exception e) {
					result = new ModelAndView("redirect:/folder/actor/list.do");
					result.addObject("message", "folder.commit.error");
				}
				this.messageService.sendMessage(message);
				result = new ModelAndView("redirect:/folder/actor/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(message, "message.commit.error");
			}

		return result;
	}

	// Trash ----------------------------------------------------------------

	@RequestMapping(value = "/trash", method = RequestMethod.GET)
	public ModelAndView trash(@RequestParam final int messageId) {

		ModelAndView result;
		Message message;
		message = this.messageService.findOne(messageId);

		try {
			Assert.isTrue(message.getFolder().getActor().getId() == this.actorService.findByPrincipal().getId());
			this.messageService.delete(message);

			result = new ModelAndView("redirect:/folder/actor/list.do");
		} catch (final Exception e) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;
	}

	@RequestMapping(value = "/mover", method = RequestMethod.GET)
	public ModelAndView mover(@RequestParam final int messageId) {

		ModelAndView result;
		Message message;
		message = this.messageService.findOne(messageId);

		try {
			Assert.isTrue(message.getFolder().getActor().getId() == this.actorService.findByPrincipal().getId());
			final Collection<Folder> destinyFolders = this.actorService.findByPrincipal().getFolders();

			result = new ModelAndView("message/mover");
			result.addObject("msg", message);
			result.addObject("destinyFolders", destinyFolders);
			result.addObject("formAction", "message/actor/create.do");
		} catch (final Exception e) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;
	}

	@RequestMapping(value = "/mover", method = RequestMethod.POST)
	public ModelAndView mover(@ModelAttribute(value = "msg") final Message message, final BindingResult binding) {

		ModelAndView result;

		try {
			Assert.isTrue(message.getFolder().getActor().getId() == this.actorService.findByPrincipal().getId());
			final Message msg = this.messageService.findOne(message.getId());
			this.messageService.moveMessage(message.getFolder(), msg);

			result = new ModelAndView("redirect:/folder/actor/list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/folder/actor/list.do");
			result.addObject("message", "folder.commit.error");
		}

		return result;
	}

	// Details ----------------------------------------------------------------

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView details(@RequestParam final int messageId) {
		ModelAndView result;
		Message message;

		message = this.messageService.findOne(messageId);

		try {
			Assert.isTrue(message.getFolder().getActor().getId() == this.actorService.findByPrincipal().getId());
			result = new ModelAndView("message/details");
			result.addObject("msg", message);
		} catch (final Exception e) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Message message) {
		ModelAndView result;

		result = this.createEditModelAndView(message, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Message msg, final String text) {
		ModelAndView result;
		Collection<Actor> recipients;

		recipients = this.actorService.findAll();
		recipients.remove(actorService.findByPrincipal());
		result = new ModelAndView("message/create");
		result.addObject("formAction", "message/actor/create.do");
		result.addObject("sendMessage", msg);
		result.addObject("recipients", recipients);
		result.addObject("message", text);

		return result;
	}
}
