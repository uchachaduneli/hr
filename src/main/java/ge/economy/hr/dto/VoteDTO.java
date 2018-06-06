/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.Vote;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class VoteDTO {

    private Integer id;
    private Integer testId;
    private Integer voterId;
    private Integer questionId;
    private Integer candidateId;
    private Integer departmentId;
    private Integer answerId;

    private Integer transactionId;
    private Integer questionTypeId;
    private Integer candidateStructureId;
    private String orderByClause;

    public static VoteDTO parse(Vote obj) {
        VoteDTO objDTO = new VoteDTO();
        objDTO.setId(obj.getId());
        objDTO.setTestId(obj.getTestId());
        objDTO.setVoterId(obj.getVoterId());
        objDTO.setQuestionId(obj.getQuestionId());
        objDTO.setCandidateId(obj.getCandidateId());
        objDTO.setDepartmentId(obj.getDepartmentId());
        objDTO.setAnswerId(obj.getAnswerId());
        return objDTO;
    }

    public static List<VoteDTO> parseToList(List<Vote> list) {

        List<VoteDTO> dTOs = new ArrayList<VoteDTO>();
        for (Vote p : list) {
            dTOs.add(VoteDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
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

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public Integer getCandidateStructureId() {
        return candidateStructureId;
    }

    public void setCandidateStructureId(Integer candidateStructureId) {
        this.candidateStructureId = candidateStructureId;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
}
