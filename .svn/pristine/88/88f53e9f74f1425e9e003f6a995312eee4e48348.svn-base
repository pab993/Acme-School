package controllers.parent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ChildService;
import services.ObservationService;
import controllers.AbstractController;
import domain.Child;

@Controller
@RequestMapping("/child/parent/observation")
public class ParentObservationController extends AbstractController {

	// Services -----------------------------------------------------------
	
	@Autowired
	private ObservationService observationService;
	
	@Autowired
	private ChildService childService;
	
	// Constructors -----------------------------------------------------------

	public ParentObservationController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "{idChild}/list")
	public ModelAndView list(@PathVariable final int idChild) {
		ModelAndView result;

		try {
			result = new ModelAndView("observation/list");
			Child child = childService.findOne(idChild);
			this.childService.observacionesVistas(child);
			result.addObject("listObservation", observationService.findAllByIdClassAndIdChild(child));
			result.addObject("requestURI", "/child/parent/observation/"+idChild+"/list.do");
			result.addObject("btnAtras", "/child/parent/detail.do?id="+idChild);
			
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}
	
}
