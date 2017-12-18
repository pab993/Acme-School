
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import domain.Manager;
import forms.ActorRegisterForm;

@Controller
@RequestMapping("/managerRegister")
public class ManagerRegisterController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private ManagerService	managerService;


	//Constructors
	// ============================================================================

	public ManagerRegisterController() {
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
		Manager manager;

		try {
			manager = this.managerService.reconstruct(actorRegisterForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(actorRegisterForm, "actorRegister.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				manager = this.managerService.saveNewUser(manager);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(actorRegisterForm, "actorRegister.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final ActorRegisterForm actorRegisterForm, final String message) {

		final ModelAndView resul = new ModelAndView("actorRegister/edit");

		resul.addObject("urlSubmit", "managerRegister/register.do");
		resul.addObject("actorRegisterForm", actorRegisterForm);
		resul.addObject("message", message);
		return resul;
	}

}
