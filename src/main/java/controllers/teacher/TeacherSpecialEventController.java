
package controllers.teacher;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SchoolClassService;
import services.SpecialEventService;
import controllers.AbstractController;
import domain.PriorityEvent;
import domain.School;
import domain.SchoolClass;
import domain.SpecialEvent;

@Controller
@RequestMapping("/schoolClass/specialEvent/teacher")
public class TeacherSpecialEventController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private SpecialEventService	specialEventService;

	@Autowired
	private SchoolClassService	schoolClassService;

	@Autowired
	private ActorService		actorService;


	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new Validator() {

			@Override
			public boolean supports(Class<?> clazz) {
				return SpecialEvent.class.equals(clazz);
			}

			@Override
			public void validate(Object target, Errors errors) {
				SpecialEvent specialEvent = (SpecialEvent) target;
				if (specialEvent.getDateInit() != null && specialEvent.getDateFin() != null && specialEvent.getDateInit().after(specialEvent.getDateFin()))
					errors.rejectValue("dateInit", "validation.fechaAnterior");
				if (specialEvent.getDateInit() != null && specialEvent.getDateFin() != null)
					for (SpecialEvent e : specialEvent.getPlace().getSpecialEvents())
						if (!e.equals(specialEvent) && !e.getIsCancel()
							&& (specialEvent.getDateInit().after(e.getDateInit()) && specialEvent.getDateInit().before(e.getDateFin()) || specialEvent.getDateInit().equals(e.getDateInit()) || specialEvent.getDateInit().equals(e.getDateFin())
								|| specialEvent.getDateFin().equals(e.getDateInit()) || specialEvent.getDateFin().equals(e.getDateFin()) || specialEvent.getDateFin().after(e.getDateInit()) && specialEvent.getDateFin().before(e.getDateFin())))
							errors.rejectValue("place", "validation.espacioNoDisponible");
				if (specialEvent.getSpace() != null && specialEvent.getSpace() > specialEvent.getPlace().getSpaceMax())
					errors.rejectValue("space", "validation.spacioMaximoSuperado", new Object[] {
						specialEvent.getPlace().getSpaceMax()
					}, "");
			}
		});
	}

	// Constructors -----------------------------------------------------------

	public TeacherSpecialEventController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	//	@RequestMapping(value = "/list")
	//	public ModelAndView list(@RequestParam final int idSchool,@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
	//		ModelAndView result;
	//		try {
	//			result = new ModelAndView("specialEvent/list");
	//			result.addObject("idSchool", idSchool);
	//			result.addObject("listSpecialEvent", schoolService.findOne(idSchool).getSpecialEvent());
	//			result.addObject("messageSuccess", messageSuccess);
	//			result.addObject("message", messageError);
	//		} catch (Exception e) {
	//			result = new ModelAndView("redirect:/panic/misc.do");
	//		}
	//		return result;
	//	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam final int idSchoolClass) {

		ModelAndView result;
		try {
			SchoolClass schoolClass = this.schoolClassService.findOne(idSchoolClass);
			Assert.isTrue(schoolClass.getTeacher().contains(this.actorService.findByPrincipal()));
			result = this.createEditModelAndViewCreate(this.specialEventService.create(schoolClass), null, null);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save")
	public ModelAndView add(@Valid SpecialEvent specialEvent, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewCreate(specialEvent, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/schoolClass/specialEvent/list.do?idSchoolClass=" + specialEvent.getEventEntity().getId() + "&messageSuccess=child.create.success");
				Assert.isTrue(((SchoolClass) specialEvent.getEventEntity()).getTeacher().contains(this.actorService.findByPrincipal()));
				this.specialEventService.save(specialEvent);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewCreate(specialEvent, "actorRegister.save.error", null);
		}

		return result;
	}

	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam final int id) {
	//
	//		ModelAndView result;
	//		try {
	//			SpecialEvent specialEvent = specialEventService.findOne(id);
	//			Assert.isTrue(((School)specialEvent.getEventEntity()).getManager().equals(actorService.findByPrincipal()));
	//			result = this.createEditModelAndViewEdit(specialEvent, null, null);
	//		} catch (Exception e) {
	//			result = new ModelAndView("redirect:/panic/misc.do");
	//		}
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView edit(@Valid SpecialEvent specialEvent, final BindingResult binding) {
	//		ModelAndView result;
	//		try {
	//			if (binding.hasErrors())
	//				result = this.createEditModelAndViewEdit(specialEvent, "actorRegister.save.error", null);
	//			else {
	//				result = new ModelAndView("redirect:/school/specialEvent/list.do?idSchool="+specialEvent.getEventEntity().getId()+"&messageSuccess=child.create.success");
	//				this.specialEventService.save(specialEvent);
	//			}
	//		} catch (final Throwable oops) {
	//			result = this.createEditModelAndViewEdit(specialEvent, "actorRegister.save.error", null);
	//		}
	//
	//		return result;
	//	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam final int id) {
		ModelAndView result;
		Integer idSchool = 0;
		try {
			SpecialEvent specialEvent = this.specialEventService.findOne(id);
			Assert.isTrue(((SchoolClass) specialEvent.getEventEntity()).getTeacher().contains(this.actorService.findByPrincipal()));
			idSchool = specialEvent.getEventEntity().getId();
			result = new ModelAndView("redirect:/schoolClass/specialEvent/list.do?idSchoolClass=" + specialEvent.getEventEntity().getId() + "&messageSuccess=child.delete.success");
			this.specialEventService.delete(specialEvent);
		} catch (final Throwable oops) {
			if (idSchool != 0)
				result = new ModelAndView("redirect:/schoolClass/specialEvent/list.do?idSchoolClass=" + idSchool + "&messageError=actorRegister.save.error");
			else
				result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}

	private ModelAndView createEditModelAndViewCreate(final SpecialEvent specialEvent, final String message, String success) {

		final ModelAndView resul = new ModelAndView("specialEvent/create");

		resul.addObject("urlSubmit", "schoolClass/specialEvent/teacher/add.do");
		resul.addObject("specialEvent", specialEvent);
		resul.addObject("message", message);
		resul.addObject("idSchoolClass", specialEvent.getEventEntity().getId());
		resul.addObject("messageSuccess", success);
		resul.addObject("listPriority", Arrays.asList(PriorityEvent.values()));

		if (specialEvent.getEventEntity() instanceof School) {
			School school = ((School) specialEvent.getEventEntity());
			resul.addObject("listPlace", school.getManager().getPlaces());
		}
		if (specialEvent.getEventEntity() instanceof SchoolClass) {
			SchoolClass schoolClass = ((SchoolClass) specialEvent.getEventEntity());
			resul.addObject("listPlace", schoolClass.getSchool().getManager().getPlaces());
		}

		return resul;
	}

	//	private ModelAndView createEditModelAndViewEdit(final SpecialEvent specialEvent, final String error, String success) {
	//
	//		final ModelAndView resul = new ModelAndView("specialEvent/create");
	//
	//		resul.addObject("urlSubmit", "school/specialEvent/manager/edit.do");
	//		resul.addObject("specialEvent", specialEvent);
	//		resul.addObject("message", error);
	//		resul.addObject("messageSuccess", success);
	//		resul.addObject("listPriority",Arrays.asList(PriorityEvent.values()));
	//		
	//		if(specialEvent.getEventEntity() instanceof School){
	//			School school = ((School)specialEvent.getEventEntity());
	//			resul.addObject("listPlace",school.getManager().getPlaces());
	//		}
	//		if(specialEvent.getEventEntity() instanceof SchoolClass){
	//			SchoolClass schoolClass = ((SchoolClass)specialEvent.getEventEntity());
	//			resul.addObject("listPlace",schoolClass.getSchool().getManager().getPlaces());
	//		}
	//		
	//		return resul;
	//	}

}
