/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.AnswerGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class AnswerGroupDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer typeId;

    private String typeName;

    public static AnswerGroupDTO parse(AnswerGroup obj) {
        AnswerGroupDTO objDTO = new AnswerGroupDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        objDTO.setDescription(obj.getDescription());
        objDTO.setTypeId(obj.getTypeId());
        return objDTO;
    }

    public static List<AnswerGroupDTO> parseToList(List<AnswerGroup> list) {

        List<AnswerGroupDTO> dTOs = new ArrayList<AnswerGroupDTO>();
        for (AnswerGroup p : list) {
            dTOs.add(AnswerGroupDTO.parse(p));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
