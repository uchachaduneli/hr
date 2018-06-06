/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.services;

import ge.economy.hr.dao.*;
import ge.economy.hr.dto.*;
import ge.economy.hr.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nino lomineishvili
 */
@Service
public class VoteService {

    @Autowired
    protected ViewTestDao viewTestDao;
    @Autowired
    protected QuestionDao questionDao;
    @Autowired
    protected AnswerDao answerDao;
    @Autowired
    protected VotePairDao votePairDao;
    @Autowired
    protected VoteDao voteDao;
    @Autowired
    protected PersonalDao personalDao;
    @Autowired
    protected TestDao testDao;
    @Autowired
    protected StructureDao structureDao;

    public List<ViewTestDTO> getTestByStatus(int statusId, int organisationId) {
        return ViewTestDTO.parseToList(viewTestDao.getTestByStatus(statusId, organisationId));
    }

    public List<QuestionDTO> getQuestionsByTest(int testId) {
        List<QuestionDTO> questions = QuestionDTO.parseToList(questionDao.getQuestionByTest(testId));
        for (QuestionDTO q : questions) {
            q.setAnswers(AnswerDTO.parseToList(answerDao.getAnswersByAnswerGroupId(q.getAnswerGroupId())));
        }
        return questions;
    }

    public boolean checkPersonalHasTest(int userId, int organistaionId) {
        List<ViewTestDTO> _tList = getTestByStatus(TestDTO.ACTIVE, organistaionId);
        List<ParamValuePair> criterias;
        if (!_tList.isEmpty()) {
            for (ViewTestDTO _test : _tList) {
                if (_test.getTypeId() == TestDTO.BEST_PERSONAL_TEST) {
                    if (voteDao.checkPersonalHasTest(userId, _test.getId())) {
                        return true;
                    }
                }

                criterias = new ArrayList<>();
                criterias.add(new ParamValuePair("voterId", userId));
                criterias.add(new ParamValuePair("testId", _test.getId()));

                if (!votePairDao.getAllByParamValue(VotePair.class, criterias).isEmpty()) {
                    if (voteDao.checkPersonalHasTest(userId, _test.getId())) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    public List<ViewTestDTO> getPersonalActiveTest(int userId, int organisationId) {
        List<ViewTestDTO> _tList = getTestByStatus(TestDTO.ACTIVE, organisationId);
        List<ViewTestDTO> _resultList = new ArrayList<>();
        if (!_tList.isEmpty()) {
            for (ViewTestDTO _test : _tList) {
                if (_test.getTypeId() == TestDTO.BEST_PERSONAL_TEST) {
                    if (voteDao.checkPersonalHasTest(userId, _test.getId())) {
                        _resultList.add(_test);
                    }
                    continue;
                }
                List<VotePair> _vp = votePairDao.getVotePairByVoterAndTestId(userId, _test.getId());
                if (!_vp.isEmpty()) {
                    for (VotePair v : _vp) {
                        if (voteDao.checkByVoterTestCandidate(userId, _test.getId(), v.getCandidateId())) {
                            _resultList.add(_test);
                            break;
                        }
                    }
                }
            }
        }
        return _resultList;

    }

    public List<CandidateDTO> getVoteCandidateByUserId(int userId, int testId) {
        List<VotePair> list = votePairDao.getVotePairByVoterAndTestId(userId, testId);
        List<VotePair> resultList = new ArrayList<>();
        for (VotePair p : list) {
            if (voteDao.checkByVoterTestCandidate(p.getVoterId(), p.getId(), p.getCandidateId())) {
                resultList.add(p);
            }
        }
        Test _selected = testDao.find(Test.class, testId);
        List<CandidateDTO> candidats = new ArrayList<>();
        for (VotePair v : resultList) {
            CandidateDTO _c = new CandidateDTO();
            if (_selected.getTypeId() == TestDTO.PERSONAL_TEST || _selected.getTypeId() == TestDTO.RANKING_TEST
                    || _selected.getTypeId() == TestDTO.SALARY_TEST) {
                Personal p = personalDao.find(Personal.class, v.getCandidateId());
                _c.setId(p.getId());
                _c.setName(p.getFirstName() + " " + p.getLastName());
                _c.setPositionId(p.getPositionId());
            } else if (_selected.getTypeId() == TestDTO.DEPARTMENT_TEST) {
                Structure s = structureDao.find(Structure.class, v.getDepartmentId());
                _c.setId(s.getId());
                _c.setName(s.getName());
            }
            candidats.add(_c);
        }
        return candidats;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveUserVote(List<Vote> votes, int userId) {
        for (Vote v : votes) {
            v.setVoterId(userId);
            if (v.getId() == null) {
                if (v.getAnswerId() != 0) {
                    voteDao.create(v);
                }
            } else {
                voteDao.update(v);
            }
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveVoterCandidate(List<VotePair> votePairs) {

        //merge items
        List<VotePairDTO> dbItems = null;

        for (VotePair v : votePairs) {
            if (dbItems == null) {
                dbItems = getVotePair(v.getTestId());
            }
            if (v.getId() == null) {
                VotePair _votePair = votePairDao.getVotePairByParams(v);
                if (_votePair == null) {
                    votePairDao.create(v);
                } else {
                    dbItems.remove(_votePair);
                }
            } else {
                dbItems.remove(v);
            }
        }
        VotePair tmp;
        for (VotePairDTO _i : dbItems) {
            tmp = votePairDao.find(VotePair.class, _i.getId());
            votePairDao.delete(tmp);
        }

    }

    public List<VotePairDTO> getVotePair(int testId) {
        return VotePairDTO.parseToList(votePairDao.getVotePairByTestId(testId));
    }

//    private VotePair getVotePairByParameter(VotePair v) {
//        VotePairExample ex = new VotePairExample();
//        VotePairExample.Criteria criteria = new VotePairExample().createCriteria();
//        criteria.andTestIdEqualTo(v.getTestId()).andVoterIdEqualTo(v.getVoterId()).andTransactionIdEqualTo(v.getTransactionId());
//        if (v.getCandidateId() != null) {
//            criteria.andCandidateIdEqualTo(v.getCandidateId());
//        } else if (v.getDepartmentId() != null) {
//            criteria.andDepartmentIdEqualTo(v.getDepartmentId());
//        }
//        ex.getOredCriteria().add(criteria);
//        return votePairDao.selectOneByExample(ex);
//    }

    public List<VoteDTO> getVoteAnswerByTest(int testId) {
        List<ParamValuePair> criterias = new ArrayList<>();
        criterias.add(new ParamValuePair("testId", testId));
        List<Vote> pList = voteDao.getAllByParamValue(Vote.class, criterias);
        if (!pList.isEmpty()) {
            return VoteDTO.parseToList(voteDao.getAllByParamValue(Vote.class, criterias));
        }
        return null;
    }
}
