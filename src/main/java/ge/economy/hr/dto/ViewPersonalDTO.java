/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.hr.misc.JsonDateSerializeSupport;
import ge.economy.hr.model.ViewPersonal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ViewPersonalDTO {
    private Integer id;
    private Integer structureId;
    private Integer positionId;
    private String firstName;
    private String lastName;
    private String mail;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date birthDate;
    private String pidNumber;
    private String fullName;
    private Integer position;
    private Integer statusId;
    private String positionName;
    private String structureName;
    private boolean leaf = true;

    public static final int ACTIVE_STATUS = 1;
    public static final int INACTIVE_STATUS = 2;

    public static ViewPersonalDTO parse(ViewPersonal obj) {
        ViewPersonalDTO objDTO = new ViewPersonalDTO();
        objDTO.setId(obj.getId());
        objDTO.setStructureId(obj.getStructureId());
        objDTO.setPositionId(obj.getPositionId());
        objDTO.setFirstName(obj.getFirstName());
        objDTO.setLastName(obj.getLastName());
        objDTO.setMail(obj.getMail());
        objDTO.setBirthDate(obj.getBirthDate());
        objDTO.setPidNumber(obj.getPidNumber());
        objDTO.setFullName(obj.getFullName());
        objDTO.setPositionName(obj.getPositionName());
        objDTO.setPosition(obj.getPosition());
        objDTO.setStatusId(obj.getStatusId());
        return objDTO;
    }

    public static List<ViewPersonalDTO> parseToList(List<ViewPersonal> list) {

        List<ViewPersonalDTO> dTOs = new ArrayList<ViewPersonalDTO>();
        for (ViewPersonal p : list) {
            dTOs.add(ViewPersonalDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStructureId() {
        return structureId;
    }

    public void setStructureId(Integer structureId) {
        this.structureId = structureId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPidNumber() {
        return pidNumber;
    }

    public void setPidNumber(String pidNumber) {
        this.pidNumber = pidNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
}
