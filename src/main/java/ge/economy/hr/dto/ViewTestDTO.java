/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.ViewTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ViewTestDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer activeStatusId;
    private String activeStatusName;
    private Integer typeId;
    private String typeName;
    private Integer organisationId;

    public static ViewTestDTO parse(ViewTest obj) {
        ViewTestDTO objDTO = new ViewTestDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        objDTO.setDescription(obj.getDescription());
        objDTO.setActiveStatusId(obj.getActiveStatusId());
        objDTO.setActiveStatusName(obj.getActiveStatusName());
        objDTO.setTypeId(obj.getTypeId());
        objDTO.setTypeName(obj.getTypeName());
        objDTO.setOrganisationId(obj.getOrganisationId());
        return objDTO;
    }

    public static List<ViewTestDTO> parseToList(List<ViewTest> list) {

        List<ViewTestDTO> dTOs = new ArrayList<ViewTestDTO>();
        for (ViewTest p : list) {
            dTOs.add(ViewTestDTO.parse(p));
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

    public Integer getActiveStatusId() {
        return activeStatusId;
    }

    public void setActiveStatusId(Integer activeStatusId) {
        this.activeStatusId = activeStatusId;
    }

    public String getActiveStatusName() {
        return activeStatusName;
    }

    public void setActiveStatusName(String activeStatusName) {
        this.activeStatusName = activeStatusName;
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

    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }
}
