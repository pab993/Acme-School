package controllers.anonymous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SchoolService;
import controllers.AbstractController;
import domain.School;

@Controller
@RequestMapping("/school")
public class AnonymousSchoolController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private SchoolService schoolService;
	
	// Constructors -----------------------------------------------------------

	public AnonymousSchoolController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required=false, defaultValue="") String keyword, @RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		result = new ModelAndView("school/list");
		result.addObject("listSchool", schoolService.findAllActiveFilter(keyword));
		result.addObject("requestURI", "school/list.do");
		result.addObject("keyword", keyword);
		result.addObject("messageSuccess", messageSuccess);
		result.addObject("message", messageError);
		result.addObject("btnAtras", "/");
		
		return result;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam final int id, @RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {

		ModelAndView result;
		try {
			School school = schoolService.findOne(id);
			result = new ModelAndView("school/detail");
			result.addObject("school", school);
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);
			result.addObject("btnAtras", "/school/list.do");
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}
	
}
