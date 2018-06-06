/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.QuestionPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class QuestionPositionDTO {
    private Integer id;
    private String name;

    private String typeName;

    public static QuestionPositionDTO parse(QuestionPosition obj) {
        QuestionPositionDTO objDTO = new QuestionPositionDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        return objDTO;
    }

    public static List<QuestionPositionDTO> parseToList(List<QuestionPosition> list) {

        List<QuestionPositionDTO> dTOs = new ArrayList<QuestionPositionDTO>();
        for (QuestionPosition p : list) {
            dTOs.add(QuestionPositionDTO.parse(p));
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
