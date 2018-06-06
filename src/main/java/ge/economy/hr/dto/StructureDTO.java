/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.Structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class StructureDTO {
    private Integer id;
    private Integer parentId;
    private Integer organisationId;
    private String name;
    private String description;
    private String ldapKey;
    private Integer visible;
    private LinkedList<Object> nodes;
    public static int ALL_NODE = 0;
    public static int ROOT_NODE = 1;
    public static int ECONOMY_TREE = 1;
    public static int ENTERPRISE_TREE = 2;

    public static StructureDTO parse(Structure obj) {
        StructureDTO objDTO = new StructureDTO();
        objDTO.setId(obj.getId());
        objDTO.setParentId(obj.getParentId());
        objDTO.setOrganisationId(obj.getOrganisationId());
        objDTO.setName(obj.getName());
        objDTO.setDescription(obj.getDescription());
        objDTO.setLdapKey(obj.getLdapKey());
        objDTO.setVisible(obj.getVisible());
        return objDTO;
    }

    public static List<StructureDTO> parseToList(List<Structure> list) {

        List<StructureDTO> dTOs = new ArrayList<StructureDTO>();
        for (Structure p : list) {
            dTOs.add(StructureDTO.parse(p));
        }
        return dTOs;
    }

    public LinkedList<Object> getNodes() {
        return nodes;
    }

    public void setNodes(LinkedList<Object> nodes) {
        this.nodes = nodes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public String getLdapKey() {
        return ldapKey;
    }

    public void setLdapKey(String ldapKey) {
        this.ldapKey = ldapKey;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}
