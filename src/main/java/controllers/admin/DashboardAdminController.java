
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.PetitionService;
import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard")
public class DashboardAdminController extends AbstractController {

	//Services--------------------------------------------------------

	@Autowired
	ActorService	actorService;

	@Autowired
	PetitionService petitionService;
	
	//Constructor------------------------------------------------------
	public DashboardAdminController() {
		super();
	}

	//Listing----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		result = new ModelAndView("dashboard/list");
		result.addObject("desvianzaMediaMaxMinNinoPorClaseActiva", petitionService.desvianzaMediaMaxMinNinoPorClaseActiva());
		result.addObject("porcentajePadresConNinosRegistradosEnClasesActivas", petitionService.porcentajePadresConNinosRegistradosEnClasesActivas());
		return result;
	}

}
