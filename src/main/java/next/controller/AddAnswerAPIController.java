package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AddAnswerAPIController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(AddAnswerAPIController.class);

	private QuestionDao questionDao = new QuestionDao();
	private AnswerDao answerDao = new AnswerDao();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		String writer = ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");
		logger.debug("questionId : {}", questionId);
		logger.debug("writer : {}", writer);
		logger.debug("contents : {}", contents);

		Answer answer = new Answer(writer, contents, questionId);
		answerDao.insert(answer);
		questionDao.increaseCountOfComment(questionId);

		ModelAndView mav = jstlView("redirect:/list.next");
		return mav;
	}
}
