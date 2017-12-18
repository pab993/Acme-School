package controllers.anonymous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PlaceService;
import controllers.AbstractController;
import domain.Place;

@Controller
@RequestMapping("/place")
public class AnonymousPlaceController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private PlaceService placeService;
	
	// Constructors -----------------------------------------------------------

	public AnonymousPlaceController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam final int id, @RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {

		ModelAndView result;
		try {
			Place place = placeService.findOne(id);
			result = new ModelAndView("school/detail");
			result.addObject("place", place);
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}
	
}
