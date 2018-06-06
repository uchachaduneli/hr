/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.services;

import ge.economy.hr.dao.ViewSelfScoreDao;
import ge.economy.hr.dao.ViewTestScoreDao;
import ge.economy.hr.dao.ViewVoteDao;
import ge.economy.hr.dto.ViewSelfScoreDTO;
import ge.economy.hr.dto.ViewTestScoreDTO;
import ge.economy.hr.dto.ViewVoteDTO;
import ge.economy.hr.dto.VoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nino lomineishvili
 */
@Service
public class ReportService {

    @Autowired
    protected ViewVoteDao viewVoteDao;
    @Autowired
    protected ViewTestScoreDao viewTestScoreDao;
    @Autowired
    protected ViewSelfScoreDao viewSelfScoreDao;

    public List<ViewVoteDTO> getVoteByPersonal(VoteDTO vote) {
        return ViewVoteDTO.parseToList(viewVoteDao.getVoteByPersonal(vote));
    }

    public List<ViewTestScoreDTO> getTestScoreByPersonal(VoteDTO vote) {
        return ViewTestScoreDTO.parseToList(viewTestScoreDao.getTestScoreByPersonal(vote));
    }

    public List<ViewSelfScoreDTO> getSelfScore(VoteDTO vote) {
        return ViewSelfScoreDTO.parseToList(viewSelfScoreDao.getSelfScore(vote));
    }

}
