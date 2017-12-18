
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ParentService;
import domain.Parent;
import forms.ActorRegisterForm;

@Controller
@RequestMapping("/parentRegister")
public class ParentRegisterController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private ParentService	parentService;


	//Constructors
	// ============================================================================

	public ParentRegisterController() {
		super();
	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("actorRegister/edit");

		result.addObject("actorRegisterForm", new ActorRegisterForm());

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorRegisterForm actorRegisterForm, final BindingResult binding) {
		ModelAndView result;
		Parent parent;

		try {
			parent = this.parentService.reconstruct(actorRegisterForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(actorRegisterForm, "actorRegister.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				parent = this.parentService.saveNewUser(parent);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(actorRegisterForm, "actorRegister.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final ActorRegisterForm actorRegisterForm) {

		return this.createEditModelAndView(actorRegisterForm, null);
	}

	private ModelAndView createEditModelAndView(final ActorRegisterForm actorRegisterForm, final String message) {

		final ModelAndView resul = new ModelAndView("actorRegister/edit");

		resul.addObject("urlSubmit", "parentRegister/register.do");
		resul.addObject("actorRegisterForm", actorRegisterForm);
		resul.addObject("message", message);
		return resul;
	}

}
