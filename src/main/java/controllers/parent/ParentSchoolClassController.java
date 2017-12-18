package controllers.parent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ChildService;
import services.PetitionService;
import services.SchoolClassService;
import services.YearSchoolService;
import controllers.AbstractController;
import domain.Child;
import domain.SchoolClass;
import domain.YearSchool;

@Controller
@RequestMapping("/school/classes/parent")
public class ParentSchoolClassController extends AbstractController {

	// Services -----------------------------------------------------------
	
	@Autowired
	private YearSchoolService yearSchoolService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private PetitionService petitionService;
	
	@Autowired
	private ChildService childService;
	
	// Constructors -----------------------------------------------------------

	public ParentSchoolClassController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/{idChild}/list")
	public ModelAndView list(@PathVariable final int idChild, @RequestParam(required=false, defaultValue="") String keyword, @RequestParam(required=false, defaultValue="0") int yearSchoolId, @RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		try {
			result = new ModelAndView("schoolClass/list");
			YearSchool yearSchool = yearSchoolService.findOne(yearSchoolId);
			if(yearSchool==null){
				yearSchool = yearSchoolService.getYearSchoolIsActiveTrue();
			}
			Child child = childService.findOne(idChild);
			result.addObject("listSchoolClass", schoolClassService.buscarAllByChildActivas(keyword, yearSchool, child));
			result.addObject("requestURI", "school/classes/parent/"+idChild+"/list.do");
			result.addObject("keyword", keyword);
			result.addObject("yearSchoolId", yearSchool.getId());
			result.addObject("yearSchoolList", yearSchoolService.findAll());
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);
			result.addObject("solicitado", true);
			result.addObject("btnAtras", "/child/parent/detail.do?id="+idChild);

		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}
	
	@RequestMapping(value = "/{idClass}/solicitar", method = RequestMethod.GET)
	public ModelAndView solicitar(@PathVariable final int idClass) {
		ModelAndView result;
		SchoolClass schoolClass = null;
		try {
			schoolClass = schoolClassService.findOne(idClass);
			Assert.isTrue(!schoolClass.getIsArchive()&&!schoolClass.getSchool().getIsArchive()&&!schoolClass.getSchool().getIsCancel());
			result = new ModelAndView("schoolClass/solicitar");
			result.addObject("listChild", childService.buscarHijoSinSolicitudEnUnaClase(schoolClass));
			result.addObject("child", childService.create());
			result.addObject("urlSubmit", "school/classes/parent/"+idClass+"/solicitar.do");
			result.addObject("idSchool", schoolClass.getSchool().getId());
		} catch (final Throwable oops) {
			try {
				Assert.isTrue(schoolClass.getSchool().getManager().equals(actorService.findByPrincipal()));
				result = new ModelAndView("redirect:/school/classes/list.do?idSchool="+schoolClass.getId()+"&messageError=actorRegister.save.error");
				result.addObject("idSchool", schoolClass.getSchool().getId());
			} catch (Exception e) {
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/{idClass}/solicitar", method = RequestMethod.POST, params = "save")
	public ModelAndView solicitar(@PathVariable final int idClass, Child idChild, final BindingResult binding) {
		ModelAndView result = new ModelAndView("schoolClass/asignar");
		SchoolClass schoolClass = null;
		try {
			schoolClass = schoolClassService.findOne(idClass);
			Assert.isTrue(!schoolClass.getIsArchive()&&!schoolClass.getSchool().getIsArchive()&&!schoolClass.getSchool().getIsCancel());
			result.addObject("idSchool", schoolClass.getSchool().getId());
			Child child = childService.findOne(idChild.getId());
			result = new ModelAndView("redirect:/school/classes/list.do?idSchool="+schoolClass.getSchool().getId()+"&messageSuccess=child.edit.success");
			this.petitionService.solicitar(schoolClass, child);
		} catch (final Throwable oops) {
			result = new ModelAndView("schoolClass/solicitar");
			result.addObject("message", "actorRegister.save.error");
			try {
				result.addObject("urlSubmit", "school/classes/parent/"+idClass+"/solicitar.do");
				result.addObject("listChild", childService.buscarHijoSinSolicitudEnUnaClase(schoolClass));
				result.addObject("child", childService.create());
				result.addObject("idSchool", schoolClass.getSchool().getId());
			} catch (Exception e) {
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "/{idClass}/desasignar", method = RequestMethod.GET)
	public ModelAndView desasignar(@PathVariable final int idClass, @RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/school/classes/detail.do?id="+idClass+"&messageSuccess=child.edit.success");
			this.schoolClassService.desasignar(idClass, id);
		} catch (final Throwable oops) {
			try {
				result = new ModelAndView("redirect:/school/classes/detail.do?id="+idClass+"&messageError=actorRegister.save.error");
			} catch (Exception e) {
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		return result;
	}	
	
}
