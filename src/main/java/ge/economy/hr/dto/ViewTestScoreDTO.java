/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.ViewTestScore;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ViewTestScoreDTO {
    private Integer testId;
    private Integer questionTypeId;
    private String questionTypeName;
    private Integer candidateId;
    private Integer departmentId;
    private String departmentName;
    private String candidateFirstName;
    private String candidateLastName;
    private Long voterFullCount;
    private Long voterCurrentCount;
    private BigInteger maxScore;
    private long realScore;
    private long selfScore;
    private Integer candidateStructureId;

    public static ViewTestScoreDTO parse(ViewTestScore obj) {
        ViewTestScoreDTO objDTO = new ViewTestScoreDTO();
        objDTO.setTestId(obj.getTestId());
        objDTO.setQuestionTypeId(obj.getQuestionTypeId());
        objDTO.setQuestionTypeName(obj.getQuestionTypeName());
        objDTO.setCandidateId(obj.getCandidateId());
        objDTO.setDepartmentName(obj.getDepartmentName());
        objDTO.setDepartmentId(obj.getDepartmentId());
        objDTO.setCandidateFirstName(obj.getCandidateFirstName());
        objDTO.setCandidateLastName(obj.getCandidateLastName());
        objDTO.setVoterFullCount(obj.getVoterFullCount());
        objDTO.setVoterCurrentCount(obj.getVoterCurrentCount());
        objDTO.setMaxScore(obj.getMaxScore());
        objDTO.setRealScore(obj.getRealScore());
        objDTO.setSelfScore(obj.getSelfScore());
        objDTO.setCandidateStructureId(obj.getCandidateStructureId());
        return objDTO;
    }

    public static List<ViewTestScoreDTO> parseToList(List<ViewTestScore> list) {

        List<ViewTestScoreDTO> dTOs = new ArrayList<ViewTestScoreDTO>();
        for (ViewTestScore p : list) {
            dTOs.add(ViewTestScoreDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCandidateFirstName() {
        return candidateFirstName;
    }

    public void setCandidateFirstName(String candidateFirstName) {
        this.candidateFirstName = candidateFirstName;
    }

    public String getCandidateLastName() {
        return candidateLastName;
    }

    public void setCandidateLastName(String candidateLastName) {
        this.candidateLastName = candidateLastName;
    }

    public Long getVoterFullCount() {
        return voterFullCount;
    }

    public void setVoterFullCount(Long voterFullCount) {
        this.voterFullCount = voterFullCount;
    }

    public Long getVoterCurrentCount() {
        return voterCurrentCount;
    }

    public void setVoterCurrentCount(Long voterCurrentCount) {
        this.voterCurrentCount = voterCurrentCount;
    }

    public BigInteger getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(BigInteger maxScore) {
        this.maxScore = maxScore;
    }

    public long getRealScore() {
        return realScore;
    }

    public void setRealScore(long realScore) {
        this.realScore = realScore;
    }

    public long getSelfScore() {
        return selfScore;
    }

    public void setSelfScore(long selfScore) {
        this.selfScore = selfScore;
    }

    public Integer getCandidateStructureId() {
        return candidateStructureId;
    }

    public void setCandidateStructureId(Integer candidateStructureId) {
        this.candidateStructureId = candidateStructureId;
    }
}
