package controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.YearSchoolService;
import controllers.AbstractController;
import domain.YearSchool;

@Controller
@RequestMapping("/yearSchool/admin")
public class AdminYearSchoolController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private YearSchoolService yearSchoolService;
	
	// Constructors -----------------------------------------------------------

	public AdminYearSchoolController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		result = new ModelAndView("yearSchool/list");
		result.addObject("listYearSchool", yearSchoolService.findAll());
		result.addObject("messageSuccess", messageSuccess);
		result.addObject("message", messageError);
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {

		ModelAndView result;
		result = this.createEditModelAndViewCreate(yearSchoolService.create(), null,null);

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save")
	public ModelAndView add(@Valid YearSchool yearSchool, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewCreate(yearSchool, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/yearSchool/admin/list.do?messageSuccess=child.create.success");
				this.yearSchoolService.save(yearSchool);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewCreate(yearSchool, "actorRegister.save.error", null);
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {

		ModelAndView result;
		try {
			YearSchool yearSchool = yearSchoolService.findOne(id);
			result = this.createEditModelAndViewEdit(yearSchool, null, null);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid YearSchool yearSchool, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewEdit(yearSchool, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/yearSchool/admin/list.do?messageSuccess=child.edit.success");
				this.yearSchoolService.save(yearSchool);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewEdit(yearSchool, "actorRegister.save.error", null);
		}

		return result;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView archivar(@RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/yearSchool/admin/list.do?messageSuccess=child.edit.success");
			this.yearSchoolService.activar(id);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/yearSchool/admin/list.do?messageError=actorRegister.save.error");
		}
		return result;
	}
	
	private ModelAndView createEditModelAndViewCreate(final YearSchool yearSchool, final String message, String success) {

		final ModelAndView resul = new ModelAndView("yearSchool/create");

		resul.addObject("urlSubmit", "yearSchool/admin/add.do");
		resul.addObject("yearSchool", yearSchool);
		resul.addObject("message", message);
		resul.addObject("messageSuccess", success);
		return resul;
	}
	
	private ModelAndView createEditModelAndViewEdit(final YearSchool yearSchool, final String error, String success) {

		final ModelAndView resul = new ModelAndView("yearSchool/create");

		resul.addObject("urlSubmit", "yearSchool/admin/edit.do");
		resul.addObject("yearSchool", yearSchool);
		resul.addObject("message", error);
		resul.addObject("messageSuccess", success);
		return resul;
	}
	
}
