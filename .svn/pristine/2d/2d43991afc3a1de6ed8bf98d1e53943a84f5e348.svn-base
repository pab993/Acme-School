package controllers.anonymous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SchoolClassService;
import services.SchoolService;
import services.YearSchoolService;
import controllers.AbstractController;
import domain.School;
import domain.SchoolClass;
import domain.YearSchool;

@Controller
@RequestMapping("/school/classes")
public class AnonymousSchoolClassController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private YearSchoolService yearSchoolService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public AnonymousSchoolClassController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(int idSchool, @RequestParam(required=false, defaultValue="") String keyword, @RequestParam(required=false, defaultValue="0") int yearSchoolId, @RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		try {
			result = new ModelAndView("schoolClass/list");
			YearSchool yearSchool = yearSchoolService.findOne(yearSchoolId);
			if(yearSchool==null){
				yearSchool = yearSchoolService.getYearSchoolIsActiveTrue();
			}
			School school = schoolService.findOne(idSchool);
			result.addObject("listSchoolClass", schoolClassService.findAllByClasseActive(keyword, yearSchool, school));
			result.addObject("requestURI", "school/classes/list.do");
			result.addObject("keyword", keyword);
			result.addObject("idSchool", idSchool);
			result.addObject("school",school);
			result.addObject("yearSchoolId", yearSchool.getId());
			result.addObject("yearSchoolList", yearSchoolService.findAll());
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);
			result.addObject("btnAtras", "/school/detail.do?id="+idSchool);

		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}

	@RequestMapping(value = "/detail")
	public ModelAndView detail(int id, @RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		try {
			result = new ModelAndView("schoolClass/detail");
			SchoolClass schoolClass = schoolClassService.findOne(id);
			Assert.isTrue(!schoolClass.getIsArchive() || actorService.isAdmin() || schoolClass.getSchool().getManager().equals(actorService.findByPrincipal()));
			Assert.isTrue(!schoolClass.getSchool().getIsArchive() || actorService.isAdmin() || schoolClass.getSchool().getManager().equals(actorService.findByPrincipal()));
			Assert.isTrue(!schoolClass.getSchool().getIsCancel() || actorService.isAdmin() || schoolClass.getSchool().getManager().equals(actorService.findByPrincipal()));
			result.addObject("schoolClass", schoolClass);
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);
			result.addObject("btnAtras", "/school/classes/list.do?idSchool="+schoolClass.getSchool().getId());
			if(schoolClass.getTeacher().contains(actorService.findByPrincipal())){
				result.addObject("teacher", true);
			}
			if(schoolClass.getSchool().getManager().equals(actorService.findByPrincipal())){
				result.addObject("manager", true);
			}
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}
	
}
