/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.Organisation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class OrganisationDTO {
    private Integer id;
    private String name;
    private String mail;
    private String domain;
    private String dcName;
    private Integer parentTreeId;
    private String ldapServerUrl;
    private String username;
    private String password;

    public static OrganisationDTO parse(Organisation obj) {
        OrganisationDTO objDTO = new OrganisationDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        objDTO.setMail(obj.getMail());
        objDTO.setDomain(obj.getDomain());
        objDTO.setDcName(obj.getDcName());
        objDTO.setParentTreeId(obj.getParentTreeId());
        objDTO.setLdapServerUrl(obj.getLdapServerUrl());
        objDTO.setUsername(obj.getUsername());
        objDTO.setPassword(obj.getPassword());
        return objDTO;
    }

    public static List<OrganisationDTO> parseToList(List<Organisation> list) {

        List<OrganisationDTO> dTOs = new ArrayList<OrganisationDTO>();
        for (Organisation p : list) {
            dTOs.add(OrganisationDTO.parse(p));
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDcName() {
        return dcName;
    }

    public void setDcName(String dcName) {
        this.dcName = dcName;
    }

    public Integer getParentTreeId() {
        return parentTreeId;
    }

    public void setParentTreeId(Integer parentTreeId) {
        this.parentTreeId = parentTreeId;
    }

    public String getLdapServerUrl() {
        return ldapServerUrl;
    }

    public void setLdapServerUrl(String ldapServerUrl) {
        this.ldapServerUrl = ldapServerUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
