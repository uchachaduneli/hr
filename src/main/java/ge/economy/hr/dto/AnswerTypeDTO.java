/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.AnswerType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class AnswerTypeDTO {
    private Integer id;
    private String name;

    public static AnswerTypeDTO parse(AnswerType obj) {
        AnswerTypeDTO objDTO = new AnswerTypeDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        return objDTO;
    }

    public static List<AnswerTypeDTO> parseToList(List<AnswerType> list) {

        List<AnswerTypeDTO> dTOs = new ArrayList<AnswerTypeDTO>();
        for (AnswerType p : list) {
            dTOs.add(AnswerTypeDTO.parse(p));
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
