/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.PersonalDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class PersonalDocumentDTO {
    private Integer id;
    private Integer personalId;
    private Integer documentTypeId;
    private String name;
    private String title;

    public static PersonalDocumentDTO parse(PersonalDocument obj) {
        PersonalDocumentDTO objDTO = new PersonalDocumentDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        objDTO.setPersonalId(obj.getPersonalId());
        objDTO.setDocumentTypeId(obj.getDocumentTypeId());
        objDTO.setName(obj.getName());
        objDTO.setTitle(obj.getTitle());
        return objDTO;
    }

    public static List<PersonalDocumentDTO> parseToList(List<PersonalDocument> list) {

        List<PersonalDocumentDTO> dTOs = new ArrayList<PersonalDocumentDTO>();
        for (PersonalDocument p : list) {
            dTOs.add(PersonalDocumentDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Integer personalId) {
        this.personalId = personalId;
    }

    public Integer getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Integer documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
