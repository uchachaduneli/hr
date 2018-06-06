/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.TestType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class TestTypeDTO {
    private Integer id;
    private String name;

    public static TestTypeDTO parse(TestType obj) {
        TestTypeDTO objDTO = new TestTypeDTO();
        objDTO.setId(obj.getId());
        objDTO.setName(obj.getName());
        return objDTO;
    }

    public static List<TestTypeDTO> parseToList(List<TestType> list) {

        List<TestTypeDTO> dTOs = new ArrayList<TestTypeDTO>();
        for (TestType p : list) {
            dTOs.add(TestTypeDTO.parse(p));
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
