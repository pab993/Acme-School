package controllers.teacher;

import javax.validation.Valid;

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
import services.ObservationService;
import services.PetitionService;
import services.SchoolClassService;
import services.YearSchoolService;
import controllers.AbstractController;
import domain.Child;
import domain.Observation;
import domain.SchoolClass;
import domain.Teacher;
import domain.YearSchool;

@Controller
@RequestMapping("/school/classes/teacher")
public class TeacherSchoolClassController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private YearSchoolService yearSchoolService;
	
	@Autowired
	private ObservationService observationService;
	
	@Autowired
	private PetitionService petitionService;
	
	@Autowired
	private ChildService childService;
	
	// Constructors -----------------------------------------------------------

	public TeacherSchoolClassController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required=false, defaultValue="") String keyword, @RequestParam(required=false, defaultValue="0") int yearSchoolId, @RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		try {
			result = new ModelAndView("schoolClass/list");
			YearSchool yearSchool = yearSchoolService.findOne(yearSchoolId);
			if(yearSchool==null){
				yearSchool = yearSchoolService.getYearSchoolIsActiveTrue();
			}
			result.addObject("listSchoolClass", schoolClassService.findAllByTeacherActivas(keyword, yearSchool, (Teacher)actorService.findByPrincipal()));
			result.addObject("requestURI", "school/classes/teacher/list.do");
			result.addObject("keyword", keyword);
			result.addObject("yearSchoolId", yearSchool.getId());
			result.addObject("yearSchoolList", yearSchoolService.findAll());
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);

		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}
	
	@RequestMapping(value = "/{idClass}/addObservacion", method = RequestMethod.GET)
	public ModelAndView asignar(@PathVariable final int idClass, @RequestParam final int id) {
		ModelAndView result;
		SchoolClass schoolClass = null;
		Child child = null;
		try {
			schoolClass = schoolClassService.findOne(idClass);
			child = childService.findOne(id);
			Assert.isTrue(schoolClass.getSchool().getTeachers().contains(actorService.findByPrincipal()));
			Assert.isTrue(this.childService.estaEnLaClase(schoolClass, child));
			result = new ModelAndView("schoolClass/observacion");
			result.addObject("observation", observationService.create(child, schoolClass));
			result.addObject("urlSubmit", "school/classes/teacher/"+idClass+"/addObservacion.do");
			
		} catch (final Throwable oops) {
			try {
				Assert.isTrue(schoolClass.getSchool().getManager().equals(actorService.findByPrincipal()));
				result = new ModelAndView("redirect:/school/classes/detail.do?id="+idClass+"&messageError=actorRegister.save.error");
			} catch (Exception e) {
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/{idClass}/addObservacion", method = RequestMethod.POST, params = "save")
	public ModelAndView asignar(@PathVariable final int idClass, @Valid Observation observation, final BindingResult binding) {
		ModelAndView result = new ModelAndView("schoolClass/observacion");
		SchoolClass schoolClass = null;
		try {
			if (binding.hasErrors()){
				result = new ModelAndView("schoolClass/observacion");
				result.addObject("message", "actorRegister.save.error");
				result.addObject("urlSubmit", "school/classes/teacher/"+idClass+"/addObservacion.do");
				result.addObject("observation", observation);
			} else {
				schoolClass = schoolClassService.findOne(idClass);
				result.addObject("idSchool", schoolClass.getSchool().getId());
				result = new ModelAndView("redirect:/school/classes/detail.do?id="+idClass+"&messageSuccess=child.edit.success");
				this.observationService.add(schoolClass, observation);
			}
		} catch (final Throwable oops) {
			result = new ModelAndView("schoolClass/observacion");
			result.addObject("message", "actorRegister.save.error");
			try {
				result.addObject("urlSubmit", "school/classes/teacher/"+idClass+"/addObservacion.do");
				result.addObject("observation", observation);
			} catch (Exception e) {
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "/{idClass}/aceptar", method = RequestMethod.GET)
	public ModelAndView aceptar(@PathVariable final int idClass, @RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/school/classes/detail.do?id="+idClass+"&messageSuccess=child.edit.success");
			this.petitionService.aceptar(id);
		} catch (final Throwable oops) {
			try {
				result = new ModelAndView("redirect:/school/classes/detail.do?id="+idClass+"&messageError=actorRegister.save.error");
			} catch (Exception e) {
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/{idClass}/cancelar", method = RequestMethod.GET)
	public ModelAndView cancelar(@PathVariable final int idClass, @RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/school/classes/detail.do?id="+idClass+"&messageSuccess=child.edit.success");
			this.petitionService.cancelar(id);
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
