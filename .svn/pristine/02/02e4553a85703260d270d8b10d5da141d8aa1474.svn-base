package controllers.parent;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ChildService;
import controllers.AbstractController;
import domain.Child;
import domain.Parent;

@Controller
@RequestMapping("/child/parent")
public class ParentChildController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private ChildService childService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public ParentChildController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		result = new ModelAndView("child/list");
		result.addObject("listChild", ((Parent)actorService.findByPrincipal()).getChilds());
		result.addObject("messageSuccess", messageSuccess);
		result.addObject("message", messageError);
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {

		ModelAndView result;
		result = this.createEditModelAndViewCreate(childService.create(), null,null);

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save")
	public ModelAndView add(@Valid Child child, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewCreate(child, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/child/parent/list.do?messageSuccess=child.create.success");
				this.childService.saveNew(child);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewCreate(child, "actorRegister.save.error", null);
		}

		return result;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam final int id) {

		ModelAndView result;
		try {
			Child child = childService.findOne(id);
			Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
			result = new ModelAndView("child/detail");
			result.addObject("child", child);
			result.addObject("btnAtras", "/child/parent/list.do");
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleted(@RequestParam final int id) {

		ModelAndView result;
		try {
			Child child = childService.findOne(id);
			Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
			result = new ModelAndView("redirect:/child/parent/list.do?messageSuccess=child.delete.success");
			childService.delete(child);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {

		ModelAndView result;
		try {
			Child child = childService.findOne(id);
			Assert.isTrue(child.getParent().equals(actorService.findByPrincipal()));
			result = this.createEditModelAndViewEdit(child, null, null);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Child child, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewEdit(child, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/child/parent/list.do?messageSuccess=child.edit.success");
				this.childService.edit(child);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewEdit(child, "actorRegister.save.error", null);
		}

		return result;
	}
	
	private ModelAndView createEditModelAndViewCreate(final Child child, final String message, String success) {

		final ModelAndView resul = new ModelAndView("child/create");

		resul.addObject("urlSubmit", "child/parent/add.do");
		resul.addObject("child", child);
		resul.addObject("message", message);
		resul.addObject("messageSuccess", success);
		return resul;
	}
	
	private ModelAndView createEditModelAndViewEdit(final Child child, final String error, String success) {

		final ModelAndView resul = new ModelAndView("child/create");

		resul.addObject("urlSubmit", "child/parent/edit.do");
		resul.addObject("child", child);
		resul.addObject("message", error);
		resul.addObject("messageSuccess", success);
		return resul;
	}
	
}
