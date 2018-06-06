/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.ViewQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ViewQuestionDTO {
    private Integer id;
    private Integer testId;
    private String title;
    private String description;
    private Integer answerGroupId;
    private Integer questionTypeId;
    private String questionTypeName;
    private String answerGroupName;
    private Integer positionId;
    private String positionName;

    public static ViewQuestionDTO parse(ViewQuestion obj) {
        ViewQuestionDTO objDTO = new ViewQuestionDTO();
        objDTO.setId(obj.getId());
        objDTO.setTestId(obj.getTestId());
        objDTO.setTitle(obj.getTitle());
        objDTO.setDescription(obj.getDescription());
        objDTO.setAnswerGroupId(obj.getAnswerGroupId());
        objDTO.setQuestionTypeId(obj.getQuestionTypeId());
        objDTO.setQuestionTypeName(obj.getQuestionTypeName());
        objDTO.setAnswerGroupName(obj.getAnswerGroupName());
        objDTO.setPositionId(obj.getPositionId());
        objDTO.setPositionName(obj.getPositionName());
        return objDTO;
    }

    public static List<ViewQuestionDTO> parseToList(List<ViewQuestion> list) {

        List<ViewQuestionDTO> dTOs = new ArrayList<ViewQuestionDTO>();
        for (ViewQuestion p : list) {
            dTOs.add(ViewQuestionDTO.parse(p));
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

    public String getAnswerGroupName() {
        return answerGroupName;
    }

    public void setAnswerGroupName(String answerGroupName) {
        this.answerGroupName = answerGroupName;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
