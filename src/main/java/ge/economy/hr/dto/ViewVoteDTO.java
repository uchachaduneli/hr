/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.ViewVote;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ViewVoteDTO {
    private Integer testId;
    private Integer questionTypeId;
    private Integer candidateId;
    private Integer departmentId;
    private Integer questionId;
    private Integer answerId;
    private String candidateFirstName;
    private String candidateLastName;
    private String departmentName;
    private String title;
    private String text;
    private Integer score;
    private Integer voterId;
    private Long sumScore;
    private long countAnswer;

    public static ViewVoteDTO parse(ViewVote obj) {
        ViewVoteDTO objDTO = new ViewVoteDTO();
        objDTO.setTestId(obj.getTestId());
        objDTO.setQuestionTypeId(obj.getQuestionTypeId());
        objDTO.setCandidateId(obj.getCandidateId());
        objDTO.setDepartmentId(obj.getDepartmentId());
        objDTO.setQuestionId(obj.getQuestionId());
        objDTO.setAnswerId(obj.getAnswerId());
        objDTO.setCandidateFirstName(obj.getCandidateFirstName());
        objDTO.setCandidateLastName(obj.getCandidateLastName());
        objDTO.setDepartmentName(obj.getDepartmentName());
        objDTO.setTitle(obj.getTitle());
        objDTO.setText(obj.getText());
        objDTO.setScore(obj.getScore());
        objDTO.setVoterId(obj.getVoterId());
        objDTO.setSumScore(obj.getSumScore());
        objDTO.setCountAnswer(obj.getCountAnswer());
        return objDTO;
    }

    public static List<ViewVoteDTO> parseToList(List<ViewVote> list) {

        List<ViewVoteDTO> dTOs = new ArrayList<ViewVoteDTO>();
        for (ViewVote p : list) {
            dTOs.add(ViewVoteDTO.parse(p));
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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    public Long getSumScore() {
        return sumScore;
    }

    public void setSumScore(Long sumScore) {
        this.sumScore = sumScore;
    }

    public long getCountAnswer() {
        return countAnswer;
    }

    public void setCountAnswer(long countAnswer) {
        this.countAnswer = countAnswer;
    }
}
