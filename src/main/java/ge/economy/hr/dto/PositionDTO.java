/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class PositionDTO {
    private Integer id;
    private String name;
    private Integer position;
    private String description;
    private String ldapKey;

    public static PositionDTO parse(Position obj) {
        PositionDTO objDTO = new PositionDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        objDTO.setPosition(obj.getPosition());
        objDTO.setDescription(obj.getDescription());
        objDTO.setLdapKey(obj.getLdapKey());
        return objDTO;
    }

    public static List<PositionDTO> parseToList(List<Position> list) {

        List<PositionDTO> dTOs = new ArrayList<PositionDTO>();
        for (Position p : list) {
            dTOs.add(PositionDTO.parse(p));
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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
}
