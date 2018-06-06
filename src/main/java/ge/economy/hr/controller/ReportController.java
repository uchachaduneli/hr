package ge.economy.hr.controller;

import ge.economy.hr.dto.ViewSelfScoreDTO;
import ge.economy.hr.dto.ViewTestScoreDTO;
import ge.economy.hr.dto.ViewVoteDTO;
import ge.economy.hr.dto.VoteDTO;
import ge.economy.hr.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author nino lomineishvili
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    protected ReportService reportService;

    @RequestMapping("/get-vote-by-personal")
    @ResponseBody
    public List<ViewVoteDTO> getVoteByPersonal(@RequestBody VoteDTO vote) {
        return reportService.getVoteByPersonal(vote);
    }

    @RequestMapping("/get-test-score-by-personal")
    @ResponseBody
    public List<ViewTestScoreDTO> getTestScoreByPersonal(@RequestBody VoteDTO vote) {
        return reportService.getTestScoreByPersonal(vote);
    }

    @RequestMapping("/get-self-score")
    @ResponseBody
    public List<ViewSelfScoreDTO> getSelfScore(@RequestBody VoteDTO vote) {
        return reportService.getSelfScore(vote);
    }

}
