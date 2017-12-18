package controllers.manager;

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
import services.SchoolService;
import controllers.AbstractController;
import domain.School;

@Controller
@RequestMapping("/school/manager")
public class ManagerSchoolController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public ManagerSchoolController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required=false, defaultValue="") String keyword, @RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		result = new ModelAndView("school/list");
		result.addObject("listSchool", schoolService.findAllByManager(keyword));
		result.addObject("requestURI", "school/manager/list.do");
		result.addObject("keyword", keyword);
		result.addObject("messageSuccess", messageSuccess);
		result.addObject("message", messageError);
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {

		ModelAndView result;
		result = this.createEditModelAndViewCreate(schoolService.create(), null,null);

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save")
	public ModelAndView add(@Valid School school, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewCreate(school, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/school/manager/list.do?messageSuccess=child.create.success");
				this.schoolService.saveNewEdit(school);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewCreate(school, "actorRegister.save.error", null);
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {

		ModelAndView result;
		try {
			School school = schoolService.findOne(id);
			Assert.isTrue(school.getManager().equals(actorService.findByPrincipal()));
			result = this.createEditModelAndViewEdit(school, null, null);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid School school, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewEdit(school, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/school/manager/list.do?messageSuccess=child.edit.success");
				this.schoolService.saveNewEdit(school);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewEdit(school, "actorRegister.save.error", null);
		}

		return result;
	}
	
	@RequestMapping(value = "/archivar", method = RequestMethod.GET)
	public ModelAndView archivar(@RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/school/manager/list.do?messageSuccess=child.edit.success");
			this.schoolService.archivar(id);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/school/manager/list.do?messageError=actorRegister.save.error");
		}
		return result;
	}
	
	@RequestMapping(value = "/desarchivar", method = RequestMethod.GET)
	public ModelAndView desarchivar(@RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/school/manager/list.do?messageSuccess=child.edit.success");
			this.schoolService.desarchivar(id);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/school/manager/list.do?messageError=actorRegister.save.error");
		}
		return result;
	}
	
	private ModelAndView createEditModelAndViewCreate(final School school, final String message, String success) {

		final ModelAndView resul = new ModelAndView("school/create");

		resul.addObject("urlSubmit", "school/manager/add.do");
		resul.addObject("school", school);
		resul.addObject("message", message);
		resul.addObject("messageSuccess", success);
		return resul;
	}
	
	private ModelAndView createEditModelAndViewEdit(final School school, final String error, String success) {

		final ModelAndView resul = new ModelAndView("school/create");

		resul.addObject("urlSubmit", "school/manager/edit.do");
		resul.addObject("school", school);
		resul.addObject("message", error);
		resul.addObject("messageSuccess", success);
		return resul;
	}
	
}
