/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.SysUserGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class SysUserGroupDTO {
    private Integer id;
    private Integer userId;
    private Integer groupId;

    public static SysUserGroupDTO parse(SysUserGroup obj) {
        SysUserGroupDTO objDTO = new SysUserGroupDTO();
        objDTO.setId(obj.getId());
        objDTO.setUserId(obj.getUserId());
        objDTO.setGroupId(obj.getGroupId());
        return objDTO;
    }

    public static List<SysUserGroupDTO> parseToList(List<SysUserGroup> list) {

        List<SysUserGroupDTO> dTOs = new ArrayList<SysUserGroupDTO>();
        for (SysUserGroup p : list) {
            dTOs.add(SysUserGroupDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
