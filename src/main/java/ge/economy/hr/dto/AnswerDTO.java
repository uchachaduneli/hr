/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class AnswerDTO {
    private Integer id;
    private String text;
    private Integer score;
    private Integer groupId;

    public static AnswerDTO parse(Answer obj) {
        AnswerDTO objDTO = new AnswerDTO();
        objDTO.setId(obj.getId());
        objDTO.setText(obj.getText());
        objDTO.setScore(obj.getScore());
        objDTO.setGroupId(obj.getGroupId());
        return objDTO;
    }

    public static List<AnswerDTO> parseToList(List<Answer> list) {

        List<AnswerDTO> dTOs = new ArrayList<AnswerDTO>();
        for (Answer p : list) {
            dTOs.add(AnswerDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
