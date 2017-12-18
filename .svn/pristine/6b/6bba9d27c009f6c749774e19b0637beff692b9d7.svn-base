package controllers.anonymous;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SchoolClassService;
import services.SchoolService;
import controllers.AbstractController;
import domain.SpecialEvent;

@Controller
@RequestMapping("/")
public class AnonymousSpecialEventController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public AnonymousSpecialEventController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/school/specialEvent/list")
	public ModelAndView list(@RequestParam final int idSchool,@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;
		try {
			result = new ModelAndView("specialEvent/list");
			result.addObject("idSchool", idSchool);
			Collection<SpecialEvent> res = new ArrayList<>();
			for(SpecialEvent s : schoolService.findOne(idSchool).getSpecialEvent()){
				if(!s.getIsCancel()){
					res.add(s);
				}
			}
			result.addObject("listBoard", res);
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
	
	@RequestMapping(value = "/schoolClass/specialEvent/list")
	public ModelAndView listClass(@RequestParam final int idSchoolClass,@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;
		try {
			result = new ModelAndView("specialEvent/list");
			result.addObject("idSchool", idSchoolClass);
			Collection<SpecialEvent> res = new ArrayList<>();
			for(SpecialEvent s : schoolClassService.findOne(idSchoolClass).getSpecialEvent()){
				if(!s.getIsCancel()){
					res.add(s);
				}
			}
			result.addObject("listBoard", res);
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
