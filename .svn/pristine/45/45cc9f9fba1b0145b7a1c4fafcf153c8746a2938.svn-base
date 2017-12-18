package controllers.manager;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.PlaceService;
import controllers.AbstractController;
import domain.Manager;
import domain.Place;

@Controller
@RequestMapping("/place/manager")
public class ManagerPlacesController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public ManagerPlacesController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;

		result = new ModelAndView("place/list");
		result.addObject("listPlace", ((Manager)actorService.findByPrincipal()).getPlaces());
		result.addObject("messageSuccess", messageSuccess);
		result.addObject("message", messageError);
		result.addObject("btnAtras", "/");
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {

		ModelAndView result;
		result = this.createEditModelAndViewCreate(placeService.create(), null,null);

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save")
	public ModelAndView add(@Valid Place place, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewCreate(place, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/place/manager/list.do?messageSuccess=child.create.success");
				this.placeService.save(place);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewCreate(place, "actorRegister.save.error", null);
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {

		ModelAndView result;
		try {
			Place place = placeService.findOne(id);
			Assert.isTrue(place.getManager().equals(actorService.findByPrincipal()));
			result = this.createEditModelAndViewEdit(place, null, null);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Place place, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewEdit(place, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/place/manager/list.do?messageSuccess=child.edit.success");
				this.placeService.save(place);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewEdit(place, "actorRegister.save.error", null);
		}

		return result;
	}
	
//	@RequestMapping(value = "/archivar", method = RequestMethod.GET)
//	public ModelAndView archivar(@RequestParam final int id) {
//		ModelAndView result;
//		try {
//			result = new ModelAndView("redirect:/school/manager/list.do?messageSuccess=child.edit.success");
//			this.schoolService.archivar(id);
//		} catch (final Throwable oops) {
//			result = new ModelAndView("redirect:/school/manager/list.do?messageError=actorRegister.save.error");
//		}
//		return result;
//	}
//	
//	@RequestMapping(value = "/desarchivar", method = RequestMethod.GET)
//	public ModelAndView desarchivar(@RequestParam final int id) {
//		ModelAndView result;
//		try {
//			result = new ModelAndView("redirect:/school/manager/list.do?messageSuccess=child.edit.success");
//			this.schoolService.desarchivar(id);
//		} catch (final Throwable oops) {
//			result = new ModelAndView("redirect:/school/manager/list.do?messageError=actorRegister.save.error");
//		}
//		return result;
//	}
	
	private ModelAndView createEditModelAndViewCreate(final Place place, final String message, String success) {

		final ModelAndView resul = new ModelAndView("place/create");

		resul.addObject("urlSubmit", "place/manager/add.do");
		resul.addObject("place", place);
		resul.addObject("create", true);
		resul.addObject("message", message);
		resul.addObject("messageSuccess", success);
		resul.addObject("btnAtras", "/place/manager/list.do");
		return resul;
	}
	
	private ModelAndView createEditModelAndViewEdit(final Place place, final String error, String success) {

		final ModelAndView resul = new ModelAndView("place/create");

		resul.addObject("urlSubmit", "place/manager/edit.do");
		resul.addObject("place", place);
		resul.addObject("edit", true);
		resul.addObject("message", error);
		resul.addObject("messageSuccess", success);
		resul.addObject("btnAtras", "/place/manager/list.do");
		return resul;
	}
	
}
