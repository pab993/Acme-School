package controllers.anonymous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SchoolClassService;
import services.SchoolService;
import controllers.AbstractController;

@Controller
@RequestMapping("/")
public class AnonymousBoardController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public AnonymousBoardController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/school/board/list")
	public ModelAndView list(@RequestParam final int idSchool,@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;
		try {
			result = new ModelAndView("boardSchool/list");
			result.addObject("idSchool", idSchool);
			result.addObject("listBoard", schoolService.findOne(idSchool).getBoard());
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);
			result.addObject("btnAtras", "/school/detail.do?id="+idSchool);
			if(schoolService.findOne(idSchool).getManager().equals(actorService.findByPrincipal())){
				result.addObject("manager", true);
			}
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}
	
	@RequestMapping(value = "/schoolClass/board/list")
	public ModelAndView listClass(@RequestParam final int idSchoolClass,@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;
		try {
			result = new ModelAndView("boardSchool/list");
			result.addObject("idSchool", idSchoolClass);
			result.addObject("listBoard", schoolClassService.findOne(idSchoolClass).getBoard());
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);
			result.addObject("btnAtras", "/school/classes/detail.do?id="+idSchoolClass);
			if(schoolClassService.findOne(idSchoolClass).getSchool().getTeachers().contains(actorService.findByPrincipal())){
				result.addObject("teacher", true);
			}
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}
	
}
