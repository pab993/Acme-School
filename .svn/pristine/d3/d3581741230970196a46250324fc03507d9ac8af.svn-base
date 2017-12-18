
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
import services.SchoolClassService;
import services.SchoolService;
import services.TeacherService;
import services.YearSchoolService;
import controllers.AbstractController;
import domain.School;
import domain.SchoolClass;
import domain.Teacher;
import domain.YearSchool;

@Controller
@RequestMapping("/school/classes/manager")
public class ManagerSchoolClassController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private SchoolService		schoolService;

	@Autowired
	private SchoolClassService	schoolClassService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private YearSchoolService	yearSchoolService;

	@Autowired
	private TeacherService		teacherService;


	// Constructors -----------------------------------------------------------

	public ManagerSchoolClassController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(int idSchool, @RequestParam(required = false, defaultValue = "") String keyword, @RequestParam(required = false, defaultValue = "0") int yearSchoolId, @RequestParam(required = false) String messageSuccess, @RequestParam(
		required = false) String messageError) {
		ModelAndView result;

		try {
			result = new ModelAndView("schoolClass/list");
			YearSchool yearSchool = this.yearSchoolService.findOne(yearSchoolId);
			if (yearSchool == null)
				yearSchool = this.yearSchoolService.getYearSchoolIsActiveTrue();
			School school = this.schoolService.findOne(idSchool);
			result.addObject("listSchoolClass", this.schoolClassService.findAllByClasse(keyword, yearSchool, school));
			result.addObject("requestURI", "school/classes/manager/list.do");
			result.addObject("keyword", keyword);
			result.addObject("idSchool", idSchool);
			result.addObject("school", school);
			result.addObject("yearSchoolId", yearSchool.getId());
			result.addObject("yearSchoolList", this.yearSchoolService.findAll());
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);

		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam final int idSchool) {
		ModelAndView result;

		try {
			result = this.createEditModelAndViewCreate(this.schoolClassService.create(idSchool), null, null);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save")
	public ModelAndView add(@Valid SchoolClass schoolClass, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewCreate(schoolClass, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/school/classes/manager/list.do?idSchool=" + schoolClass.getSchool().getId() + "&messageSuccess=child.create.success");
				this.schoolClassService.save(schoolClass);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewCreate(schoolClass, "actorRegister.save.error", null);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {

		ModelAndView result;
		try {
			SchoolClass schoolClass = this.schoolClassService.findOne(id);
			Assert.isTrue(schoolClass.getSchool().getManager().equals(this.actorService.findByPrincipal()));
			result = this.createEditModelAndViewEdit(schoolClass, null, null);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid SchoolClass schoolClass, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewEdit(schoolClass, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/school/classes/manager/list.do?idSchool=" + schoolClass.getSchool().getId() + "&messageSuccess=child.edit.success");
				this.schoolClassService.save(schoolClass);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewEdit(schoolClass, "actorRegister.save.error", null);
		}

		return result;
	}

	@RequestMapping(value = "/archivar", method = RequestMethod.GET)
	public ModelAndView archivar(@RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/school/classes/manager/list.do?idSchool=" + this.schoolClassService.findOne(id).getSchool().getId() + "&messageSuccess=child.edit.success");
			this.schoolClassService.archivar(id);
		} catch (final Throwable oops) {
			try {
				result = new ModelAndView("redirect:/school/classes/manager/list.do?idSchool=" + this.schoolClassService.findOne(id).getSchool().getId() + "&messageError=actorRegister.save.error");
			} catch (Exception e) {
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		return result;
	}

	@RequestMapping(value = "/desarchivar", method = RequestMethod.GET)
	public ModelAndView desarchivar(@RequestParam final int id) {
		ModelAndView result;
		try {
			result = new ModelAndView("redirect:/school/classes/manager/list.do?idSchool=" + this.schoolClassService.findOne(id).getSchool().getId() + "&messageSuccess=child.edit.success");
			this.schoolClassService.desarchivar(id);
		} catch (final Throwable oops) {
			try {
				result = new ModelAndView("redirect:/school/classes/manager/list.do?idSchool=" + this.schoolClassService.findOne(id).getSchool().getId() + "&messageError=actorRegister.save.error");
			} catch (Exception e) {
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		return result;
	}

	private ModelAndView createEditModelAndViewCreate(final SchoolClass schoolClass, final String message, String success) {

		final ModelAndView resul = new ModelAndView("schoolClass/create");

		resul.addObject("urlSubmit", "school/classes/manager/add.do");
		resul.addObject("schoolClass", schoolClass);
		resul.addObject("message", message);
		resul.addObject("messageSuccess", success);
		return resul;
	}

	private ModelAndView createEditModelAndViewEdit(final SchoolClass schoolClass, final String error, String success) {

		final ModelAndView resul = new ModelAndView("schoolClass/create");

		resul.addObject("urlSubmit", "school/classes/manager/edit.do");
		resul.addObject("schoolClass", schoolClass);
		resul.addObject("message", error);
		resul.addObject("messageSuccess", success);
		return resul;
	}

	@RequestMapping(value = "/{idClass}/asignar", method = RequestMethod.GET)
	public ModelAndView asignar(@PathVariable final int idClass) {
		ModelAndView result;
		SchoolClass schoolClass = null;
		try {
			schoolClass = this.schoolClassService.findOne(idClass);
			Assert.isTrue(schoolClass.getSchool().getManager().equals(this.actorService.findByPrincipal()));
			Assert.isTrue(schoolClass.getIsArchive() == false && schoolClass.getSchool().getIsCancel() == false);
			result = new ModelAndView("schoolClass/asignar");
			result.addObject("listTeacher", this.teacherService.buscarProfesoresDeUnColegioNoAsignadosAUnaClase(schoolClass));
			result.addObject("teacher", this.teacherService.create());
			result.addObject("idSchool", schoolClass.getSchool().getId());
			result.addObject("urlSubmit", "school/classes/manager/" + idClass + "/asignar.do");

		} catch (final Throwable oops) {
			try {
				Assert.isTrue(schoolClass.getSchool().getManager().equals(this.actorService.findByPrincipal()));
				result = new ModelAndView("redirect:/school/classes/manager/list.do?idSchool=" + schoolClass.getId() + "&messageError=actorRegister.save.error");
			} catch (Exception e) {
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		return result;
	}

	@RequestMapping(value = "/{idClass}/asignar", method = RequestMethod.POST, params = "save")
	public ModelAndView asignar(@PathVariable final int idClass, Teacher idTeacher, final BindingResult binding) {
		ModelAndView result = new ModelAndView("schoolClass/asignar");
		SchoolClass schoolClass = null;
		try {
			schoolClass = this.schoolClassService.findOne(idClass);
			result.addObject("idSchool", schoolClass.getSchool().getId());
			Teacher teacher = this.teacherService.findOne(idTeacher.getId());
			result = new ModelAndView("redirect:/school/classes/detail.do?id=" + idClass + "&messageSuccess=child.edit.success");
			this.schoolClassService.asignar(schoolClass, teacher);
		} catch (final Throwable oops) {
			result = new ModelAndView("schoolClass/asignar");
			result.addObject("message", "actorRegister.save.error");
			try {
				SchoolClass schoolClass2 = this.schoolClassService.findOne(idClass);
				result.addObject("urlSubmit", "school/classes/manager/" + idClass + "/asignar.do");
				result.addObject("listTeacher", this.teacherService.buscarProfesoresDeUnColegioNoAsignadosAUnaClase(schoolClass));
				result.addObject("teacher", this.teacherService.create());
				result.addObject("idSchool", schoolClass2.getSchool().getId());
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
			result = new ModelAndView("redirect:/school/classes/detail.do?id=" + idClass + "&messageSuccess=child.edit.success");
			this.schoolClassService.desasignar(idClass, id);
		} catch (final Throwable oops) {
			try {
				result = new ModelAndView("redirect:/school/classes/detail.do?id=" + idClass + "&messageError=actorRegister.save.error");
			} catch (Exception e) {
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		return result;
	}

}
