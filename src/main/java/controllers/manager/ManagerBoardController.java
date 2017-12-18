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
import services.BoardService;
import services.SchoolService;
import controllers.AbstractController;
import domain.Board;
import domain.School;

@Controller
@RequestMapping("/school/board/manager")
public class ManagerBoardController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public ManagerBoardController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam final int idSchool,@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;
		try {
			result = new ModelAndView("boardSchool/list");
			result.addObject("idSchool", idSchool);
			School school = schoolService.findOne(idSchool);
			if(school.getManager().equals(actorService.findByPrincipal())){
				result.addObject("manager", true);
			}
			result.addObject("listBoard", school.getBoard());
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);
			result.addObject("btnAtras", "/school/detail.do?id="+idSchool);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam final int idSchool) {

		ModelAndView result;
		try {
			School school = schoolService.findOne(idSchool);
			result = this.createEditModelAndViewCreate(boardService.create(school), null,null);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		
		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save")
	public ModelAndView add(@Valid Board board, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewCreate(board, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/school/board/manager/list.do?idSchool="+board.getEventEntity().getId()+"&messageSuccess=child.create.success");
				this.boardService.save(board);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewCreate(board, "actorRegister.save.error", null);
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {

		ModelAndView result;
		try {
			Board board = boardService.findOne(id);
			Assert.isTrue(((School)board.getEventEntity()).getManager().equals(actorService.findByPrincipal()));
			result = this.createEditModelAndViewEdit(board, null, null);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Board board, final BindingResult binding) {
		ModelAndView result;
		try {
			if (binding.hasErrors())
				result = this.createEditModelAndViewEdit(board, "actorRegister.save.error", null);
			else {
				result = new ModelAndView("redirect:/school/board/manager/list.do?idSchool="+board.getEventEntity().getId()+"&messageSuccess=child.create.success");
				this.boardService.save(board);
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewEdit(board, "actorRegister.save.error", null);
		}

		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam final int id) {
		ModelAndView result;
		Integer idSchool = 0;
		try {
			Board board = boardService.findOne(id);
			Assert.isTrue(((School)board.getEventEntity()).getManager().equals(actorService.findByPrincipal()));
			idSchool = board.getEventEntity().getId();
			result = new ModelAndView("redirect:/school/board/manager/list.do?idSchool="+board.getEventEntity().getId()+"&messageSuccess=child.delete.success");
			this.boardService.delete(board);
		} catch (final Throwable oops) {
			if(idSchool!=0){
				result = new ModelAndView("redirect:/school/board/manager/list.do?idSchool="+idSchool+"&messageError=actorRegister.save.error");
			}else{
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		return result;
	}
	
	private ModelAndView createEditModelAndViewCreate(final Board board, final String message, String success) {

		final ModelAndView resul = new ModelAndView("boardSchool/create");

		resul.addObject("urlSubmit", "school/board/manager/add.do");
		resul.addObject("board", board);
		resul.addObject("message", message);
		resul.addObject("messageSuccess", success);
		resul.addObject("btnAtras", "/school/detail.do?id="+board.getEventEntity().getId());
		return resul;
	}
	
	private ModelAndView createEditModelAndViewEdit(final Board board, final String error, String success) {

		final ModelAndView resul = new ModelAndView("school/create");

		resul.addObject("urlSubmit", "boardSchool/board/manager/edit.do");
		resul.addObject("board", board);
		resul.addObject("message", error);
		resul.addObject("messageSuccess", success);
		resul.addObject("btnAtras", "/school/detail.do?id="+board.getEventEntity().getId());
		return resul;
	}
	
}
