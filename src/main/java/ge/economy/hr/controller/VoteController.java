package ge.economy.hr.controller;

import ge.economy.hr.controller.auth.LoginInterceptor;
import ge.economy.hr.dto.*;
import ge.economy.hr.model.Vote;
import ge.economy.hr.model.VotePair;
import ge.economy.hr.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author nino lomineishvili
 */
@Controller
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @RequestMapping("/get-active-test")
    @ResponseBody
    public List<ViewTestDTO> getActiveTest(HttpServletRequest request) {
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        return voteService.getTestByStatus(TestDTO.ACTIVE, p.getOrganisationId());
    }

    @RequestMapping("/get-active-test-by-user")
    @ResponseBody
    public List<ViewTestDTO> getActiveTestByUser(HttpServletRequest request) {
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        return voteService.getPersonalActiveTest(p.getId(), p.getOrganisationId());
    }

    @RequestMapping("/get-all-test")
    @ResponseBody
    public List<ViewTestDTO> getAllTest(HttpServletRequest request) {
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        return voteService.getTestByStatus(TestDTO.ALL, p.getOrganisationId());
    }

    @RequestMapping("/get-question-by-test")
    @ResponseBody
    public List<QuestionDTO> getQuestionByTest(@RequestParam int testId) {
        return voteService.getQuestionsByTest(testId);
    }

    @RequestMapping("/get-vote-candidate")
    @ResponseBody
    public List<CandidateDTO> getVoteCandidate(HttpServletRequest request) {
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        int testId = Integer.parseInt(request.getParameter("testId"));
        return voteService.getVoteCandidateByUserId(p.getId(), testId);
    }

    @RequestMapping("/save-user-vote")
    @ResponseBody
    public void saveUserVote(HttpServletRequest request, @RequestBody List<Vote> votes) {
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        voteService.saveUserVote(votes, p.getId());
    }

    @RequestMapping("/save-voter-candidate")
    @ResponseBody
    public void saveVoterCandidate(@RequestBody List<VotePair> votePairs) {
        voteService.saveVoterCandidate(votePairs);
    }

    @RequestMapping("/get-vote-pair")
    @ResponseBody
    public List<VotePairDTO> getVotePair(@RequestParam int testId) {
        return voteService.getVotePair(testId);
    }

    @RequestMapping("/get-vote-answer")
    @ResponseBody
    public List<VoteDTO> getVoteAnswerByTest(@RequestParam int testId) {
        return voteService.getVoteAnswerByTest(testId);
    }

}
