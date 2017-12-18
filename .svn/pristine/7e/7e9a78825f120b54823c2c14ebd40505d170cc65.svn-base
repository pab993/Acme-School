
package controllers.manager;

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
import services.SchoolService;
import services.TeacherService;
import controllers.AbstractController;
import domain.School;
import domain.Teacher;
import forms.ActorRegisterForm;

@Controller
@RequestMapping("/teacher/manager")
public class ManagerTeacherController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private ActorService actorService;
	
	//Constructors
	// ============================================================================

	public ManagerTeacherController() {
		super();
	}

	@RequestMapping(value = "/{idSchool}/list")
	public ModelAndView list(@PathVariable int idSchool, @RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		try {
			School school = schoolService.findOne(idSchool);
			Assert.isTrue(school.getManager().equals(actorService.findByPrincipal()));
			result = new ModelAndView("teacher/list");
			result.addObject("listTeacher", school.getTeachers());
			result.addObject("requestURI", "teacher/manager/"+idSchool+"/list.do");
			result.addObject("idSchool", idSchool);
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);

		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}
	
	//Edition
	//=============================================================================

	@RequestMapping(value = "/{idSchool}/register", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable  final int idSchool) {
		ModelAndView result;
		
		try {
			School school = schoolService.findOne(idSchool);
			Assert.isTrue(school.getManager().equals(actorService.findByPrincipal()));
			result = this.createEditModelAndView(new ActorRegisterForm(), null, null, idSchool);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		
		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/{idSchool}/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@PathVariable  final int idSchool, @Valid final ActorRegisterForm actorRegisterForm, final BindingResult binding) {
		ModelAndView result;
		Teacher teacher;
		School school;
		try {
			school = schoolService.findOne(idSchool);
			Assert.isTrue(school.getManager().equals(actorService.findByPrincipal()));
		} catch (Exception e) {
			return new ModelAndView("redirect:/panic/misc.do");
		}
		
		
		try {
			teacher = this.teacherService.reconstruct(actorRegisterForm, binding, school);
			if (binding.hasErrors())
				result = this.createEditModelAndView(actorRegisterForm, "actorRegister.save.error", null, idSchool);
			else {
				result = new ModelAndView("redirect:/teacher/manager/"+idSchool+"/list.do?messageSuccess=child.edit.success");
				teacher = this.teacherService.saveNewUser(teacher);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(actorRegisterForm, "actorRegister.save.error", null, idSchool);
		}

		return result;
	}

	@RequestMapping(value = "/{idSchool}/archivar", method = RequestMethod.GET)
	public ModelAndView archivar(@PathVariable int idSchool, @RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/teacher/manager/"+idSchool+"/list.do?messageSuccess=child.edit.success");
			this.teacherService.archivar(id);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/teacher/manager/"+idSchool+"/list.do?messageError=actorRegister.save.error");
		}
		return result;
	}
	
	@RequestMapping(value = "/{idSchool}/desarchivar", method = RequestMethod.GET)
	public ModelAndView desarchivar(@PathVariable int idSchool, @RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/teacher/manager/"+idSchool+"/list.do?messageSuccess=child.edit.success");
			this.teacherService.desarchivar(id);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/teacher/manager/"+idSchool+"/list.doredirect:/teacher/manager/"+idSchool+"/list.do?messageError=actorRegister.save.error");
		}
		return result;
	}
	
	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final ActorRegisterForm actorRegisterForm, final String message, final String success, int idSchool) {

		final ModelAndView resul = new ModelAndView("actorRegister/edit");

		resul.addObject("urlSubmit", "teacher/manager/"+idSchool+"/register.do");
		resul.addObject("actorRegisterForm", actorRegisterForm);
		resul.addObject("message", message);
		resul.addObject("messageSuccess", success);
		return resul;
	}

}
