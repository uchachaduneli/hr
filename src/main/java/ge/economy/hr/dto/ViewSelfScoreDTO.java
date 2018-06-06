/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.ViewSelfScore;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ViewSelfScoreDTO {
    private Integer testId;
    private Integer questionTypeId;
    private String questionTypeName;
    private Integer candidateId;
    private Integer departmentId;
    private String departmentName;
    private String candidateFirstName;
    private String candidateLastName;
    private BigInteger maxScore;
    private long selfScore;
    private Integer candidateStructureId;

    public static ViewSelfScoreDTO parse(ViewSelfScore obj) {
        ViewSelfScoreDTO objDTO = new ViewSelfScoreDTO();
        objDTO.setTestId(obj.getTestId());
        objDTO.setQuestionTypeId(obj.getQuestionTypeId());
        objDTO.setQuestionTypeName(obj.getQuestionTypeName());
        objDTO.setCandidateId(obj.getCandidateId());
        objDTO.setDepartmentId(obj.getDepartmentId());
        objDTO.setDepartmentName(obj.getDepartmentName());
        objDTO.setCandidateFirstName(obj.getCandidateFirstName());
        objDTO.setCandidateLastName(obj.getCandidateLastName());
        objDTO.setMaxScore(obj.getMaxScore());
        objDTO.setSelfScore(obj.getSelfScore());
        objDTO.setCandidateStructureId(obj.getCandidateStructureId());
        return objDTO;
    }

    public static List<ViewSelfScoreDTO> parseToList(List<ViewSelfScore> list) {

        List<ViewSelfScoreDTO> dTOs = new ArrayList<ViewSelfScoreDTO>();
        for (ViewSelfScore p : list) {
            dTOs.add(ViewSelfScoreDTO.parse(p));
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

    public BigInteger getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(BigInteger maxScore) {
        this.maxScore = maxScore;
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
