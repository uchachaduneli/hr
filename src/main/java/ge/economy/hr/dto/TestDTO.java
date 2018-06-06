/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class TestDTO {
    private Integer id;
    private Integer organisationId;
    private String name;
    private String description;
    private Integer activeStatusId;
    private Integer typeId;

    private String activeStatusName;
    private String typeName;

    public static final int ACTIVE = 2;
    public static final int CLOSED = 3;
    public static final int NEW = 1;
    public static final int ALL = -1;
    public static final int DEPARTMENT_TEST = 2;
    public static final int PERSONAL_TEST = 1;
    public static final int RANKING_TEST = 3;
    public static final int BEST_PERSONAL_TEST = 4;
    public static final int SALARY_TEST = 5;

    public static TestDTO parse(Test obj) {
        TestDTO objDTO = new TestDTO();
        objDTO.setId(obj.getId());
        objDTO.setOrganisationId(obj.getOrganisationId());
        objDTO.setName(obj.getName());
        objDTO.setDescription(obj.getDescription());
        objDTO.setActiveStatusId(obj.getActiveStatusId());
        objDTO.setTypeId(obj.getTypeId());
        return objDTO;
    }

    public static List<TestDTO> parseToList(List<Test> list) {

        List<TestDTO> dTOs = new ArrayList<TestDTO>();
        for (Test p : list) {
            dTOs.add(TestDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getActiveStatusName() {
        return activeStatusName;
    }

    public void setActiveStatusName(String activeStatusName) {
        this.activeStatusName = activeStatusName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
