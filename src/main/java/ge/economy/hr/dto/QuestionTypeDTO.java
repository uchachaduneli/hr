/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.QuestionType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class QuestionTypeDTO {
    private Integer id;
    private String name;

    public static QuestionTypeDTO parse(QuestionType obj) {
        QuestionTypeDTO objDTO = new QuestionTypeDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        return objDTO;
    }

    public static List<QuestionTypeDTO> parseToList(List<QuestionType> list) {

        List<QuestionTypeDTO> dTOs = new ArrayList<QuestionTypeDTO>();
        for (QuestionType p : list) {
            dTOs.add(QuestionTypeDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
