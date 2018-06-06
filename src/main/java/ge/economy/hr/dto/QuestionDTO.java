/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class QuestionDTO {
    private Integer id;
    private Integer testId;
    private String title;
    private Integer questionTypeId;
    private String description;
    private Integer answerGroupId;
    private Integer positionId;

    private List<AnswerDTO> answers;
    private int answerId;
    private String answerGroupName;
    private String positionName;
    private String questionTypeName;

    public static QuestionDTO parse(Question obj) {
        QuestionDTO objDTO = new QuestionDTO();
        objDTO.setId(obj.getId());
        objDTO.setTestId(obj.getTestId());
        objDTO.setTitle(obj.getTitle());
        objDTO.setQuestionTypeId(obj.getQuestionTypeId());
        objDTO.setDescription(obj.getDescription());
        objDTO.setAnswerGroupId(obj.getAnswerGroupId());
        objDTO.setPositionId(obj.getPositionId());
        return objDTO;
    }

    public static List<QuestionDTO> parseToList(List<Question> list) {

        List<QuestionDTO> dTOs = new ArrayList<QuestionDTO>();
        for (Question p : list) {
            dTOs.add(QuestionDTO.parse(p));
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAnswerGroupId() {
        return answerGroupId;
    }

    public void setAnswerGroupId(Integer answerGroupId) {
        this.answerGroupId = answerGroupId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerGroupName() {
        return answerGroupName;
    }

    public void setAnswerGroupName(String answerGroupName) {
        this.answerGroupName = answerGroupName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }
}
