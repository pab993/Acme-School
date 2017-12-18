package controllers.teacher;

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
import services.SchoolClassService;
import controllers.AbstractController;
import domain.Board;
import domain.School;
import domain.SchoolClass;
import domain.Teacher;

@Controller
@RequestMapping("/schoolClass/board/teacher")
public class TeacherBoardController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public TeacherBoardController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam final int idSchoolClass,@RequestParam(required=false) String messageSuccess, @RequestParam(required=false) String messageError) {
		ModelAndView result;
		try {
			result = new ModelAndView("boardSchool/list");
			result.addObject("idSchoolClass", idSchoolClass);
			result.addObject("listBoard", schoolClassService.findOne(idSchoolClass).getBoard());
			result.addObject("messageSuccess", messageSuccess);
			result.addObject("message", messageError);
		} catch (Exception e) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam final int idSchoolClass) {

		ModelAndView result;
		try {
			SchoolClass schoolClass = schoolClassService.findOne(idSchoolClass);
			result = this.createEditModelAndViewCreate(boardService.create(schoolClass), null,null);
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
				result = new ModelAndView("redirect:/schoolClass/board/list.do?idSchoolClass="+board.getEventEntity().getId()+"&messageSuccess=child.create.success");
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
			if(board.getEventEntity() instanceof School){
				Assert.isTrue(((School)board.getEventEntity()).getManager().equals(actorService.findByPrincipal()));
			}
			if(board.getEventEntity() instanceof SchoolClass){
				Assert.isTrue(((Teacher)actorService.findByPrincipal()).getSchoolClass().contains(board.getEventEntity()));
			}
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
				result = new ModelAndView("redirect:/schoolClass/board/list.do?idSchoolClass="+board.getEventEntity().getId()+"&messageSuccess=child.create.success");
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
			idSchool = board.getEventEntity().getId();
			result = new ModelAndView("redirect:/schoolClass/board/list.do?idSchoolClass="+board.getEventEntity().getId()+"&messageSuccess=child.delete.success");
			this.boardService.delete(board);
		} catch (final Throwable oops) {
			if(idSchool!=0){
				result = new ModelAndView("redirect:/schoolClass/board/list.do?idSchoolClass="+idSchool+"&messageError=actorRegister.save.error");
			}else{
				result = new ModelAndView("redirect:/panic/misc.do");
			}
		}
		return result;
	}
	
	private ModelAndView createEditModelAndViewCreate(final Board board, final String message, String success) {

		final ModelAndView resul = new ModelAndView("boardSchool/create");

		resul.addObject("urlSubmit", "schoolClass/board/teacher/add.do");
		resul.addObject("board", board);
		resul.addObject("message", message);
		resul.addObject("messageSuccess", success);
		return resul;
	}
	
	private ModelAndView createEditModelAndViewEdit(final Board board, final String error, String success) {

		final ModelAndView resul = new ModelAndView("school/create");

		resul.addObject("urlSubmit", "schoolClass/board/teacher/edit.do");
		resul.addObject("board", board);
		resul.addObject("message", error);
		resul.addObject("messageSuccess", success);
		return resul;
	}
	
}
