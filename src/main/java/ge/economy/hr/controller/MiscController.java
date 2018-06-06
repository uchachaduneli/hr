package ge.economy.hr.controller;

import ge.economy.hr.controller.auth.LoginInterceptor;
import ge.economy.hr.dto.*;
import ge.economy.hr.error.InvalidDataExeption;
import ge.economy.hr.error.OperationNotPermittedExeption;
import ge.economy.hr.model.*;
import ge.economy.hr.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author nino lomineishvili
 */
@Controller
@RequestMapping("/misc")
public class MiscController {

    @Autowired
    private BaseService baseServices;

    @RequestMapping("/get-current-user")
    @ResponseBody
    public PersonalDTO getUser(HttpSession session) {
        return (PersonalDTO) session.getAttribute(LoginInterceptor.AUTHENTICATED_USER);
    }

    @RequestMapping("/get-answer-groups")
    @ResponseBody
    public List<ViewAnswerGroupDTO> getAnswerGroup() {
        return baseServices.getAnswerGroups();
    }

    @RequestMapping("/get-answer-types")
    @ResponseBody
    public List<AnswerTypeDTO> getAnswerType() {
        return baseServices.getAnswerTypes();
    }

    @RequestMapping("/get-question-types")
    @ResponseBody
    public List<QuestionTypeDTO> getQuestionType() {
        return baseServices.getQuestionTypes();
    }

    @RequestMapping("/get-answers")
    @ResponseBody
    public List<AnswerDTO> getAnswer() {
        return baseServices.getAnswers();
    }

    @RequestMapping("/get-answers-by-answer-group")
    @ResponseBody
    public List<AnswerDTO> getAnswersByAnswerGroup(@RequestParam int answerGroupId) {
        return baseServices.getAnswersByAnswerGroup(answerGroupId);
    }

    @RequestMapping("/get-questions")
    @ResponseBody
    public List<ViewQuestionDTO> getQuestions() {
        return baseServices.getQuestion();
    }

    @RequestMapping("/get-question-positions")
    @ResponseBody
    public List<QuestionPositionDTO> getQuestionPositons() {
        return baseServices.getQuestionPositions();
    }

    @RequestMapping("/get-question-by-test")
    @ResponseBody
    public List<ViewQuestionDTO> getQuestionByTest(@RequestParam int testId) {
        return baseServices.getQuestionByTest(testId);
    }

    @RequestMapping("/get-test")
    @ResponseBody
    public List<ViewTestDTO> getTest(HttpSession session) {
        PersonalDTO p = (PersonalDTO) session.getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        return baseServices.getTest(p.getOrganisationId());
    }

    @RequestMapping("/get-test-status")
    @ResponseBody
    public List<TestActiveStatusDTO> getTestStatus() {
        return baseServices.getTestActiveStatus();
    }

    @RequestMapping("/get-test-type")
    @ResponseBody
    public List<TestTypeDTO> getTestType() {
        return baseServices.getTestTypes();
    }

    @RequestMapping("/save-test")
    @ResponseBody
    public TestDTO saveTest(HttpSession session, @RequestBody Test o) {
        PersonalDTO p = (PersonalDTO) session.getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        o.setOrganisationId(p.getOrganisationId());
        return TestDTO.parse(baseServices.mergeTest(o));
    }

    @RequestMapping("/save-question")
    @ResponseBody
    public QuestionDTO saveQuestion(@RequestBody Question o) throws InvalidDataExeption {
        return QuestionDTO.parse(baseServices.mergeQuestion(o));
    }

    @RequestMapping("/save-answer")
    @ResponseBody
    public AnswerDTO saveAnswer(@RequestBody Answer o) {
        return AnswerDTO.parse(baseServices.mergeAnswer(o));
    }

    @RequestMapping("/save-answerGroup")
    @ResponseBody
    public AnswerGroupDTO saveAnswerGroup(@RequestBody AnswerGroup o) {
        return AnswerGroupDTO.parse(baseServices.mergeAnswerGroup(o));
    }

    @RequestMapping("/save-answerType")
    @ResponseBody
    public AnswerTypeDTO saveAnswerType(@RequestBody AnswerType o) {
        return AnswerTypeDTO.parse(baseServices.mergeAnswerType(o));
    }

    @RequestMapping("/remove-test")
    @ResponseBody
    public void removeTest(@RequestParam int testId) throws OperationNotPermittedExeption {
        baseServices.removeTest(testId);
    }

    @RequestMapping("/remove-question")
    @ResponseBody
    public void removeQuestion(@RequestParam int questionId) throws OperationNotPermittedExeption {
        baseServices.removeQuestion(questionId);
    }

    @RequestMapping("/remove-answerType")
    @ResponseBody
    public void removeAnswerType(@RequestParam int answerTypeId) throws OperationNotPermittedExeption {
        baseServices.removeAnswerType(answerTypeId);
    }

    @RequestMapping("/remove-answerGroup")
    @ResponseBody
    public void removeAnswerGroup(@RequestParam int answerGroupId) throws OperationNotPermittedExeption {
        baseServices.removeAnswerGroup(answerGroupId);
    }

    @RequestMapping("/remove-answer")
    @ResponseBody
    public void removeAnswer(@RequestParam int answerId) throws OperationNotPermittedExeption {
        baseServices.removeAnswer(answerId);
    }

}
