/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.SysPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class SysPermissionDTO {
    private Integer id;
    private String name;

    public static final String HR_MENU_TEST = "hr.test";
    public static final String HR_MENU_STRUCTURE = "hr.structure";
    public static final String HR_MENU_REPORT = "hr.report";

    public static SysPermissionDTO parse(SysPermission obj) {
        SysPermissionDTO objDTO = new SysPermissionDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        return objDTO;
    }

    public static List<SysPermissionDTO> parseToList(List<SysPermission> list) {

        List<SysPermissionDTO> dTOs = new ArrayList<SysPermissionDTO>();
        for (SysPermission p : list) {
            dTOs.add(SysPermissionDTO.parse(p));
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
