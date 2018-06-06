/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.SysGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class SysGroupDTO {
    private Integer id;
    private String name;

    public static SysGroupDTO parse(SysGroup obj) {
        SysGroupDTO objDTO = new SysGroupDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        return objDTO;
    }

    public static List<SysGroupDTO> parseToList(List<SysGroup> list) {

        List<SysGroupDTO> dTOs = new ArrayList<SysGroupDTO>();
        for (SysGroup p : list) {
            dTOs.add(SysGroupDTO.parse(p));
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
