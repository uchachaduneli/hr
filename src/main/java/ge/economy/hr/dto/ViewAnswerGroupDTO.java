/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.ViewAnswerGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ViewAnswerGroupDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer typeId;
    private String typeName;

    public static ViewAnswerGroupDTO parse(ViewAnswerGroup obj) {
        ViewAnswerGroupDTO objDTO = new ViewAnswerGroupDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        objDTO.setDescription(obj.getDescription());
        objDTO.setTypeId(obj.getTypeId());
        objDTO.setTypeName(obj.getTypeName());
        return objDTO;
    }

    public static List<ViewAnswerGroupDTO> parseToList(List<ViewAnswerGroup> list) {

        List<ViewAnswerGroupDTO> dTOs = new ArrayList<ViewAnswerGroupDTO>();
        for (ViewAnswerGroup p : list) {
            dTOs.add(ViewAnswerGroupDTO.parse(p));
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
