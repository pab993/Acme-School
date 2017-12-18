package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SchoolService;
import controllers.AbstractController;

@Controller
@RequestMapping("/school/admin")
public class AdminSchoolController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private SchoolService schoolService;
	
	// Constructors -----------------------------------------------------------

	public AdminSchoolController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		result = new ModelAndView("school/list");
		result.addObject("listSchool", schoolService.findAll());
		result.addObject("messageSuccess", messageSuccess);
		result.addObject("message", messageError);
		return result;
	}
	
	@RequestMapping(value = "/cancelar", method = RequestMethod.GET)
	public ModelAndView archivar(@RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/school/admin/list.do?messageSuccess=child.edit.success");
			this.schoolService.cancelar(id);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/school/admin/list.do?messageError=actorRegister.save.error");
		}
		return result;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView desarchivar(@RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/school/admin/list.do?messageSuccess=child.edit.success");
			this.schoolService.activar(id);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/school/admin/list.do?messageError=actorRegister.save.error");
		}
		return result;
	}
	
}
