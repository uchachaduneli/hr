/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.SysGroupPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class SysGroupPermissionDTO {
    private Integer id;
    private Integer groupId;
    private Integer permissionId;

    public static SysGroupPermissionDTO parse(SysGroupPermission obj) {
        SysGroupPermissionDTO objDTO = new SysGroupPermissionDTO();
        objDTO.setId(obj.getId());
        objDTO.setGroupId(obj.getGroupId());
        objDTO.setPermissionId(obj.getPermissionId());
        return objDTO;
    }

    public static List<SysGroupPermissionDTO> parseToList(List<SysGroupPermission> list) {

        List<SysGroupPermissionDTO> dTOs = new ArrayList<SysGroupPermissionDTO>();
        for (SysGroupPermission p : list) {
            dTOs.add(SysGroupPermissionDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
