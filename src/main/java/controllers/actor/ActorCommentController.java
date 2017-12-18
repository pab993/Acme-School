package controllers.actor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/actor")
public class ActorCommentController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private CommentService commentService;
	
	// Constructors -----------------------------------------------------------

	public ActorCommentController() {
		super();
	}

	// Index ------------------------------------------------------------------		
	
	@RequestMapping(value = "/comentar", method = RequestMethod.GET)
	public ModelAndView comentar(@RequestParam final int id) {

		ModelAndView result;
		result = this.createEditModelAndViewCreate(commentService.create(id), null,null);

		return result;
	}

	@RequestMapping(value = "/comentar", method = RequestMethod.POST, params = "save")
	public ModelAndView comentar(@Valid Comment comment, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewCreate(comment, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/school/detail.do?id="+comment.getSchool().getId()+"&messageSuccess=child.create.success");
				this.commentService.save(comment);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewCreate(comment, "actorRegister.save.error", null);
		}

		return result;
	}

	private ModelAndView createEditModelAndViewCreate(final Comment comment, final String message, String success) {
		final ModelAndView resul = new ModelAndView("comment/create");

		resul.addObject("urlSubmit", "comment/actor/comentar.do");
		resul.addObject("comment", comment);
		resul.addObject("message", message);
		resul.addObject("messageSuccess", success);
		return resul;
	}
	
}
